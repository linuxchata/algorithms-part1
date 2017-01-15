/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       15/01/2017
 *  Last updated:  15/01/2017
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
        return 0;
    }

    /**
     * Sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        return 0;
    }

    /**
     * Is this board the goal board?
     */
    public boolean isGoal() {
        return false;
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
        int[][] blocks = new int[2][];
        blocks[0] = new int[2];
        blocks[1] = new int[2];
        blocks[0][0] = 1;
        blocks[0][1] = 0;
        blocks[1][0] = 2;
        blocks[0][1] = 3;

        Board init = new Board(blocks);
        Board init2 = new Board(blocks);

        // Test equals.
        init.equals(init);

        init.equals(init2);

        Board twin = init.twin();
        init.equals(twin);
    }
}