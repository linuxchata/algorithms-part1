
/*----------------------------------------------------------------
 *  Author:        Pylyp Lebediev
 *  Written:       31/12/2016
 *  Last updated:  01/01/2017
 *
 *  Compilation:   javac Permutation.java
 *  Execution:     java Permutation
 *  
 *  Client program
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Client program.
 */
public class Permutation {

  /**
   * Client program entry point.
   */
  public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);

    RandomizedQueue<String> rq = new RandomizedQueue<String>();
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      rq.enqueue(item);
    }

    for (int i = 0; i < k; i++) {
      StdOut.println(rq.dequeue());
    }
  }
}
