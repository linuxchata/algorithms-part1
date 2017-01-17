/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       15/01/2017
 *  Last updated:  17/01/2017
 *
 *  Compilation:  javac Solver.java
 *  Execution:    java Solver
 *  Dependencies: Board
 *
 *  Solver data type
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;

public class Solver {
    private SearchNode goalSearchNode;
    private int moves = 0;

    /**
     * Find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new java.lang.NullPointerException("Board cannot be null");
        }

        this.solve(initial);
    }

    /**
     * Is the initial board solvable?
     */
    public boolean isSolvable() {
        return this.goalSearchNode != null;
    }

    /**
     * Min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        return this.isSolvable() ? this.moves : -1;
    }

    /**
     * Sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        Stack<Board> result = new Stack<Board>();

        SearchNode current = this.goalSearchNode;
        result.push(current.board());

        while (current.previousNode() != null) {
            current = current.previousNode();
            result.push(current.board());
        }

        return result;
    }

    /**
     * Solve board.
     */
    private void solve(Board initial) {
        SearchNode initialSearchNode = new SearchNode(initial, null, this.moves);

        MinPQ<SearchNode> openSet = new MinPQ<SearchNode>(initialSearchNode);
        openSet.insert(initialSearchNode);

        while (!openSet.isEmpty()) {
            SearchNode currentSearchNode = openSet.delMin();
            Board current = currentSearchNode.board();

            if (current.isGoal()) {
                this.goalSearchNode = currentSearchNode;
                break;
            }

            this.moves++;

            Iterable<Board> neighbors = current.neighbors();
            for (Board board : neighbors) {
                if (currentSearchNode.previousNode() == null || !currentSearchNode.previousBoard().equals(board)) {
                    openSet.insert(new SearchNode(board, currentSearchNode, this.moves));
                }
            }
        }
    }

    /**
     * Represents search node
     */
    private class SearchNode implements Comparator {
        private Board board;
        private SearchNode previousNode;
        private int moves;

        /**
         * Construct search node
         */
        public SearchNode(Board board, SearchNode previousNode, int moves) {
            this.board = board;
            this.previousNode = previousNode;
            this.moves = moves;
        }

        /**
         * The board
         */
        public Board board() {
            return this.board;
        }

        /**
         * Previous board
         */
        public Board previousBoard() {
            return this.previousNode.board;
        }

        /**
         * Previous search node
         */
        public SearchNode previousNode() {
            return this.previousNode;
        }

        /**
         * Comparator implementation
         */
        @Override
        public int compare(Object o1, Object o2) {
            Board b1 = ((SearchNode) o1).board();
            Board b2 = ((SearchNode) o2).board();

            int b1m = b1.manhattan() + this.moves;
            int b2m = b2.manhattan() + this.moves;

            if (b1m > b2m) {
                return 1;
            }
            if (b1m < b2m) {
                return -1;
            }

            return 0;
        }

        /**
         * String representation of this search node (equals to the board)
         */
        public String toString() {
            return this.board.toString();
        }
    }

    /**
     * Solve a slider puzzle (given below)
     */
    public static void main(String[] args) {
    }
}