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
import edu.princeton.cs.algs4.StdRandom;

/**
 * Client program.
 */
public class Permutation {

    /**
     * Client program entry point.
     */
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        String[] s = StdIn.readAllStrings();

        String[] r = new String[k];
        for (int i = 0; i < k; i++) {
            r[i] = s[i];
        }

        for (int i = k; i < s.length; i++) {
            int j = StdRandom.uniform(0, i + 1);
            if (j < k) {
                r[j] = s[i];
            }
        }

        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        for (int i = 0; i < k; i++) {
            rq.enqueue(r[i]);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }
    }
}
