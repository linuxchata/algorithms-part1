/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       15/01/2017
 *  Last updated:  18/01/2017
 *
 *  Compilation:  javac Board.java
 *  Execution:    java Board
 *  Dependencies: None
 *
 *  Board data type
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private int[][] blocks;
    private int n;

    private int manhattanField = -1;
    private int hammingField = -1;

    /**
     * Construct a board from an n-by-n array of blocks
     * (where blocks[i][j] = block in row i, column j)
     */
    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new java.lang.NullPointerException("Blocks cannot be null");
        }

        this.n = blocks.length;
        this.blocks = this.cloneBoardArray(blocks);
    }

    /**
     * Board dimension n
     */
    public int dimension() {
        return this.n;
    }

    /**
     * N umber of blocks out of place
     */
    public int hamming() {
        if (this.hammingField != -1) {
            return this.hammingField;
        }

        int hamming = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                int expectedBlock = this.n * i + j + 1;
                if (this.blocks[i][j] != 0 && this.blocks[i][j] != expectedBlock) {
                    hamming++;
                }
            }
        }

        this.hammingField = hamming;
        return hamming;
    }

    /**
     * Sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        if (this.manhattanField != -1) {
            return this.manhattanField;
        }

        int manhattan = 0;

        // Expected value
        int e = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                // Expected value
                e++;

                // Current value
                int value = this.blocks[i][j];
                if (value != 0 && value != e) {
                    // Calculate destination coordinates for current value that is not in place
                    int c;
                    int r;
                    if (value % this.n == 0) {
                        r = value / this.n;
                        c = this.n;
                    } else {
                        r = (value / this.n) + 1;
                        c = (value % this.n);
                    }

                    // Calculate distance to the destination coordinates
                    int dc = (j + 1) - c;
                    int dr = (i + 1) - r;
                    manhattan = manhattan + Math.abs(dc) + Math.abs(dr);
                }
            }
        }

        this.manhattanField = manhattan;
        return manhattan;
    }

    /**
     * Is this board the goal board?
     */
    public boolean isGoal() {
        // Check that last block is not equal to zero
        if (this.blocks[this.n - 1][this.n - 1] != 0) {
            return false;
        }

        int lastBlock = this.n * this.n;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                int expectedBlock = this.n * i + j + 1;
                // Skip last block from check
                if (expectedBlock == lastBlock) {
                    continue;
                }
                if (this.blocks[i][j] != 0 && this.blocks[i][j] != expectedBlock) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * A board that is obtained by exchanging any pair of blocks
     */
    public Board twin() {
        int[][] copy = this.cloneBoardArray(this.blocks);

        // Get random row
        int i;
        int j = -1;
        int j2 = -1;
        while (true) {
            i = StdRandom.uniform(0, this.n);

            // Try to find two non-zero items in the row
            for (int c = 0; c < this.n; c++) {
                int nextColumn = c + 1;
                if (nextColumn < this.n && copy[i][c] != 0 && copy[i][c + 1] != 0) {
                    j = c;
                    j2 = nextColumn;
                    break;
                }
            }

            // None-zero blocks were found.
            if (j != -1 && j2 != -1) {
                break;
            }
        }

        // Swap elements in twin array
        int temp = copy[i][j];
        copy[i][j] = copy[i][j2];
        copy[i][j2] = temp;

        return new Board(copy);
    }

    /**
     * Does this board equal y?
     */
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }

        if (y == null) {
            return false;
        }

        if (this.getClass() != y.getClass()) {
            return false;
        }

        Board that = (Board) y;
        boolean result = Arrays.deepEquals(this.blocks, that.blocks);
        return result;
    }

    /**
     * All neighboring boards
     */
    public Iterable<Board> neighbors() {
        // Find zero-value block
        int c = 0;
        int r = 0;
        mainLoop:
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (this.blocks[i][j] == 0) {
                    r = i;
                    c = j;
                    break mainLoop;
                }
            }
        }

        List<Board> results = new ArrayList<Board>();

        // Swap zero-value block with neighbors
        int[][] array;
        for (int i = -1; i < 2; i = i + 2) {
            int nr = r + i;
            int nc = c + i;

            // Swap blocks only is block is not in place.
            if (nr >= 0 && nr < this.n) {
                array = this.cloneBoardArray(this.blocks);
                this.swapZeroBlock(array, r, nr, c, c);
                results.add(new Board(array));
            }
            if (nc >= 0 && nc < this.n) {
                array = this.cloneBoardArray(this.blocks);
                this.swapZeroBlock(array, r, r, c, nc);
                results.add(new Board(array));
            }
        }

        return results;
    }

    /**
     * String representation of this board (in the output format specified below)
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.n + "\n");

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                sb.append(String.format("%4d", this.blocks[i][j]));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Clone board
     */
    private int[][] cloneBoardArray(int[][] array) {
        // Deep copy of the array.
        int[][] copy = new int[this.n][];
        for (int i = 0; i < this.n; i++) {
            copy[i] = new int[this.n];
            System.arraycopy(array[i], 0, copy[i], 0, this.n);
        }

        return copy;
    }

    /**
     * Swap zero value in the blocks array
     */
    private void swapZeroBlock(int[][] array, int r0, int r, int c0, int c) {
        array[r0][c0] = array[r][c];
        array[r][c] = 0;
    }

    /**
     * Unit tests (not graded)
     */
    public static void main(String[] args) {
    }
}