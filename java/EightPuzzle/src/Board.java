/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       15/01/2017
 *  Last updated:  16/01/2017
 *
 *  Compilation:  javac Board.java
 *  Execution:    java Board
 *  Dependencies: None
 *
 *  Board data type
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Board {
    private int[][] blocks;
    private int n;
    private int moves;

    /**
     * Construct a board from an n-by-n array of blocks
     * (where blocks[i][j] = block in row i, column j)
     */
    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new java.lang.NullPointerException("Blocks cannot be null");
        }

        this.n = blocks.length;
        this.blocks = blocks;
        this.moves = 0;
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
        int hamming = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                int expectedBlock = 3 * i + j + 1;
                if (this.blocks[i][j] != 0 && this.blocks[i][j] != expectedBlock) {
                    hamming++;
                }
            }
        }

        return hamming + this.moves;
    }

    /**
     * Sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
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

        return manhattan + this.moves;
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
                int expectedBlock = 3 * i + j + 1;
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
        // Deep copy of the array.
        int[][] twin = new int[this.n][];
        for (int i = 0; i < this.n; i++) {
            twin[i] = new int[this.n];
            System.arraycopy(this.blocks[i], 0, twin[i], 0, this.n);
        }

        // Get random row
        int i;
        int j = -1;
        int j2 = -1;
        while (true) {
            i = StdRandom.uniform(0, this.n);

            // Try to find two non-zero items in the row
            for (int c = 0; c < this.n; c++) {
                int nextColumn = c + 1;
                if (nextColumn < this.n && twin[i][c] != 0 && twin[i][c + 1] != 0) {
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
        int temp = twin[i][j];
        twin[i][j] = twin[i][j2];
        twin[i][j2] = temp;

        return new Board(twin);
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
        return null;
    }

    /**
     * String representation of this board (in the output format specified below)
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.n + System.lineSeparator());

        for (int i = 0; i < this.blocks.length; i++) {
            for (int j = 0; j < this.blocks.length; j++) {
                sb.append(String.format("%2d", this.blocks[i][j]));
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    /**
     * Unit tests (not graded)
     */
    public static void main(String[] args) {
        int[][] blocks = new int[3][];
        blocks[0] = new int[3];
        blocks[1] = new int[3];
        blocks[2] = new int[3];
        blocks[0][0] = 0;
        blocks[0][1] = 1;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 2;
        blocks[1][2] = 5;
        blocks[2][0] = 7;
        blocks[2][1] = 8;
        blocks[2][2] = 6;

        Board init = new Board(blocks);
        Board init2 = new Board(blocks);

        // Test equals.
        init.equals(init);

        init.equals(init2);

        Board twin = init.twin();
        init.equals(twin);

        init.hamming();

        int[][] solvedBlocks = new int[3][];
        solvedBlocks[0] = new int[3];
        solvedBlocks[1] = new int[3];
        solvedBlocks[2] = new int[3];
        solvedBlocks[0][0] = 1;
        solvedBlocks[0][1] = 2;
        solvedBlocks[0][2] = 3;
        solvedBlocks[1][0] = 4;
        solvedBlocks[1][1] = 5;
        solvedBlocks[1][2] = 6;
        solvedBlocks[2][0] = 7;
        solvedBlocks[2][1] = 8;
        solvedBlocks[2][2] = 0;

        Board solvedBoard = new Board(solvedBlocks);
        solvedBoard.isGoal();

        int[][] manhattanBlocks = new int[3][];
        manhattanBlocks[0] = new int[3];
        manhattanBlocks[1] = new int[3];
        manhattanBlocks[2] = new int[3];
        manhattanBlocks[0][0] = 8;
        manhattanBlocks[0][1] = 1;
        manhattanBlocks[0][2] = 3;
        manhattanBlocks[1][0] = 4;
        manhattanBlocks[1][1] = 0;
        manhattanBlocks[1][2] = 2;
        manhattanBlocks[2][0] = 7;
        manhattanBlocks[2][1] = 6;
        manhattanBlocks[2][2] = 5;

        Board manhattanBoard = new Board(manhattanBlocks);
        manhattanBoard.manhattan();

        int[][] manhattanBlocks2 = new int[4][];
        manhattanBlocks2[0] = new int[4];
        manhattanBlocks2[1] = new int[4];
        manhattanBlocks2[2] = new int[4];
        manhattanBlocks2[3] = new int[4];
        manhattanBlocks2[0][0] = 0;
        manhattanBlocks2[0][1] = 9;
        manhattanBlocks2[0][2] = 1;
        manhattanBlocks2[0][3] = 10;
        manhattanBlocks2[1][0] = 3;
        manhattanBlocks2[1][1] = 5;
        manhattanBlocks2[1][2] = 4;
        manhattanBlocks2[1][3] = 2;
        manhattanBlocks2[2][0] = 14;
        manhattanBlocks2[2][1] = 6;
        manhattanBlocks2[2][2] = 11;
        manhattanBlocks2[2][3] = 7;
        manhattanBlocks2[3][0] = 12;
        manhattanBlocks2[3][1] = 13;
        manhattanBlocks2[3][2] = 8;
        manhattanBlocks2[3][3] = 15;

        Board manhattanBoard2 = new Board(manhattanBlocks2);
        manhattanBoard2.manhattan();
    }
}