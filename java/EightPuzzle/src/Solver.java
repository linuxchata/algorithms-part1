/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       15/01/2017
 *  Last updated:  15/01/2017
 *
 *  Compilation:  javac Solver.java
 *  Execution:    java Solver
 *  Dependencies: Board
 *
 *  Solver data type
 *
 ******************************************************************************/

public class Solver {
    /**
     * Find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new java.lang.NullPointerException("Board cannot be null");
        }

    }

    /**
     * Is the initial board solvable?
     */
    public boolean isSolvable() {
        return false;
    }

    /**
     * Min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        return 0;
    }

    /**
     * Sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        return null;
    }

    /**
     * Solve a slider puzzle (given below)
     */
    public static void main(String[] args) {
    }
}