
/*----------------------------------------------------------------
 *  Author:        Pylyp Lebediev
 *  Written:       27/12/2016
 *  Last updated:  27/12/2016
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats
 *  
 *  Percolation statistics data type
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private double[] fractions;
  private int trials;

  /**
   * Perform trials independent experiments on an n-by-n grid
   * 
   * @param n
   *          size of the grid
   * @param trials
   *          number of trials
   */
  public PercolationStats(int n, int trials) {
    if (n <= 0) {
      throw new java.lang.IllegalArgumentException("N cannot be negative");
    }

    if (trials <= 0) {
      throw new java.lang.IllegalArgumentException("Trials cannot be negative");
    }

    this.trials = trials;
    this.fractions = new double[this.trials];

    Percolation percolation;

    for (int t = 0; t <= trials - 1; t++) {
      percolation = new Percolation(n);
      int openSitesCount = 0;
      while (!percolation.percolates()) {
        int row = StdRandom.uniform(1, n + 1);
        int col = StdRandom.uniform(1, n + 1);
        if (!percolation.isOpen(row, col)) {
          percolation.open(row, col);
          openSitesCount++;
        }
      }
      this.fractions[t] = (double) openSitesCount / (n * n);
    }
  }

  /**
   * Sample mean of percolation threshold
   * 
   * @return sample mean of percolation threshold
   */
  public double mean() {
    return StdStats.mean(this.fractions);
  }

  /**
   * Sample standard deviation of percolation threshold
   * 
   * @return sample standard deviation of percolation threshold
   */
  public double stddev() {
    return StdStats.stddev(this.fractions);
  }

  /**
   * Low endpoint of 95% confidence interval
   * 
   * @return low endpoint of 95% confidence interval
   */
  public double confidenceLo() {
    double mean = this.mean();
    double stddev = this.stddev();

    return mean - this.confidenceSuffix(stddev);
  }

  /**
   * High endpoint of 95% confidence interval
   * 
   * @return high endpoint of 95% confidence interval
   */
  public double confidenceHi() {
    double mean = this.mean();
    double stddev = this.stddev();

    return mean + this.confidenceSuffix(stddev);
  }

  /**
   * Test client (described below)
   */
  public static void main(String[] args) {
  }

  /**
   * Confidence suffix
   * 
   * @param stddev
   *          sample standard deviation of percolation threshold
   * @return confidence suffix
   */
  private double confidenceSuffix(double stddev) {
    return ((1.96 * stddev) / Math.sqrt(this.trials));
  }
}
