import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

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
    private List<Board> closedSet;
    private int moves = 0;

    /**
     * Find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new java.lang.NullPointerException("Board cannot be null");
        }

        closedSet = new ArrayList<Board>();
        MinPQ<Board> openSet = new MinPQ<Board>(initial);
        openSet.insert(initial);

        while (!openSet.isEmpty()) {
            Board current = openSet.delMin();

            closedSet.add(current);

            if (current.isGoal()) {
                break;
            }

            Iterable<Board> neighbors = current.neighbors();
            for (Board board : neighbors) {
                if (this.previousSearchNode == null || !this.previousSearchNode.equals(board)) {
                    openSet..insert(board);
                }
            }
            this.moves++;
            this.previousSearchNode = current;
        }
    }

    /**
     * Is the initial board solvable?
     */
    public boolean isSolvable() {
        return true;
    }

    /**
     * Min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        return this.moves;
    }

    /**
     * Sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        return this.closedSet;
    }

    /**
     * Solve a slider puzzle (given below)
     */
    public static void main(String[] args) {
    }
}