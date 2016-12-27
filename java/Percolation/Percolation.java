
/*----------------------------------------------------------------
 *  Author:        Pylyp Lebediev
 *  Written:       26/12/2016
 *  Last updated:  27/12/2016
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     java Percolation
 *  
 *  Percolation data type
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private int size;
  private int[][] grid;
  private int topNode = 0;
  private int bottomNode;
  private WeightedQuickUnionUF unionfind;

  /**
   * Create n-by-n grid, with all sites blocked
   * 
   * @param n
   *          size of the grid
   */
  public Percolation(int n) {
    if (n <= 0) {
      throw new java.lang.IllegalArgumentException("Size cannot be negative");
    }

    this.size = n;
    this.grid = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        this.grid[i][j] = 0;
      }
    }

    // Size of the grid plus virtual-top and virtual-bottom nodes.
    this.unionfind = new WeightedQuickUnionUF(n * n + 2);
    this.bottomNode = (n * n + 1);
  }

  /**
   * Open site (row, col) if it is not open already
   * 
   * @param row
   *          row index
   * 
   * @param col
   *          column index
   */
  public void open(int row, int col) {
    validate(row);
    validate(col);

    int gridRow = row - 1;
    int gridColumn = col - 1;

    this.grid[gridRow][gridColumn] = 1;

    if (gridRow == 0) {
      this.unionfind.union(this.topNode, this.xyTo1D(gridRow, gridColumn));
    }

    if (gridRow == this.size - 1) {
      this.unionfind.union(this.bottomNode, this.xyTo1D(gridRow, gridColumn));
    }

    int currentNode = this.xyTo1D(gridRow, gridColumn);
    int topNeighborIndex = gridRow - 1;
    if (topNeighborIndex >= 0 && this.grid[topNeighborIndex][gridColumn] == 1) {
      this.unionfind.union(currentNode, this.xyTo1D(topNeighborIndex, gridColumn));
    }

    int bottomNeighborIndex = gridRow + 1;
    if (bottomNeighborIndex < this.size && this.grid[bottomNeighborIndex][gridColumn] == 1) {
      this.unionfind.union(currentNode, this.xyTo1D(bottomNeighborIndex, gridColumn));
    }

    int leftNeighborIndex = gridColumn - 1;
    if (leftNeighborIndex >= 0 && this.grid[gridRow][leftNeighborIndex] == 1) {
      this.unionfind.union(currentNode, this.xyTo1D(gridRow, leftNeighborIndex));
    }

    int rightNeighborIndex = gridColumn + 1;
    if (rightNeighborIndex < this.size && this.grid[gridRow][rightNeighborIndex] == 1) {
      this.unionfind.union(currentNode, this.xyTo1D(gridRow, rightNeighborIndex));
    }
  }

  /**
   * Is site (row, col) open?
   * 
   * @param row
   *          row index
   * 
   * @param col
   *          column index
   * @return is site (row, col) open?
   */
  public boolean isOpen(int row, int col) {
    validate(row);
    validate(col);

    return this.grid[row - 1][col - 1] == 1 ? true : false;
  }

  /**
   * Is site (row, col) full?
   * 
   * @param row
   *          row index
   * 
   * @param col
   *          column index
   * @return is site (row, col) full?
   */
  public boolean isFull(int row, int col) {
    validate(row);
    validate(col);

    return this.unionfind.connected(this.xyTo1D(row - 1, col - 1), 0);
  }

  /**
   * Number of open sites
   * 
   * @return number of open sites
   */
  public int numberOfOpenSites() {
    int numberOfOpenSites = 0;

    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (this.grid[i][j] == 1) {
          numberOfOpenSites++;
        }
      }
    }

    return numberOfOpenSites;
  }

  /**
   * Does the system percolate?
   * 
   * @return does the system percolate?
   */
  public boolean percolates() {
    return this.unionfind.connected(this.topNode, this.bottomNode);
  }

  /**
   * Test client (optional)
   */
  public static void main(String[] args) {
  }

  private void validate(int param) {
    if (param <= 0 || param > this.size) {
      throw new IndexOutOfBoundsException("Param value " + param + " is out of bounds");
    }
  }

  private int xyTo1D(int x, int y) {
    int result = this.size * x + y + 1;
    return result;
  }
}