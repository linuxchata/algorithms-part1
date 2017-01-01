
/*----------------------------------------------------------------
 *  Author:        Pylyp Lebediev
 *  Written:       01/01/2017
 *  Last updated:  01/01/2017
 *
 *  Compilation:   javac RandomizedQueueTest.java
 *  Execution:     java RandomizedQueueTest
 *  
 *  Randomized queue
 *
 *----------------------------------------------------------------*/

/**
 * Randomized queue unit tests.
 */
public class RandomizedQueueTest {

  public static void main(String[] args) {
    // Test 1
    RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
    Assert.IsTrue(randomizedQueue.isEmpty());
    randomizedQueue.enqueue(1);
    randomizedQueue.enqueue(2);
    randomizedQueue.enqueue(3);
    randomizedQueue.enqueue(4);
    randomizedQueue.enqueue(5);
    randomizedQueue.enqueue(6);
    randomizedQueue.enqueue(7);
    randomizedQueue.enqueue(8);
    randomizedQueue.dequeue();
    randomizedQueue.enqueue(9);
    randomizedQueue.dequeue();
    randomizedQueue.enqueue(10);
    randomizedQueue.enqueue(11);
    Assert.IsFalse(randomizedQueue.isEmpty());
    Assert.AreSame(9, randomizedQueue.size());

    // Test 2
    randomizedQueue = new RandomizedQueue<Integer>();
    Assert.IsTrue(randomizedQueue.isEmpty());
    randomizedQueue.enqueue(0);
    Assert.AreSame(0, randomizedQueue.dequeue());
    randomizedQueue.enqueue(3);
    Assert.AreSame(1, randomizedQueue.size());

    // Test 3
    randomizedQueue = new RandomizedQueue<Integer>();
    Assert.AreSame(0, randomizedQueue.size());
    randomizedQueue.enqueue(17);
    randomizedQueue.dequeue();
    Assert.AreSame(0, randomizedQueue.size());
    randomizedQueue.enqueue(37);
    Assert.AreSame(1, randomizedQueue.size());

    // Test 4
    randomizedQueue = new RandomizedQueue<Integer>();
    Assert.IsTrue(randomizedQueue.isEmpty());
    Assert.IsTrue(randomizedQueue.isEmpty());
    Assert.AreSame(0, randomizedQueue.size());
    Assert.AreSame(0, randomizedQueue.size());
    Assert.IsTrue(randomizedQueue.isEmpty());
    Assert.AreSame(0, randomizedQueue.size());
    randomizedQueue.enqueue(660);
    randomizedQueue.dequeue();
    randomizedQueue.enqueue(295);
    Assert.AreSame(1, randomizedQueue.size());

    // Test 5
    randomizedQueue = new RandomizedQueue<Integer>();
    Assert.IsTrue(randomizedQueue.isEmpty());
    randomizedQueue.enqueue(0);
    randomizedQueue.dequeue();
    randomizedQueue.enqueue(3);
    Assert.AreSame(1, randomizedQueue.size());

    // Test 6
    randomizedQueue = new RandomizedQueue<Integer>();
    Assert.AreSame(0, randomizedQueue.size());
    randomizedQueue.enqueue(17);
    randomizedQueue.dequeue();
    Assert.AreSame(0, randomizedQueue.size());
    randomizedQueue.enqueue(37);
    Assert.AreSame(1, randomizedQueue.size());

    // Test 7
    randomizedQueue = new RandomizedQueue<Integer>();
    randomizedQueue.enqueue(0);
    randomizedQueue.enqueue(3);
    randomizedQueue.dequeue();
    randomizedQueue.enqueue(3);
    Assert.AreSame(2, randomizedQueue.size());    

    // Test 7
    randomizedQueue = new RandomizedQueue<Integer>();
    randomizedQueue.enqueue(0);
    randomizedQueue.enqueue(1);
    randomizedQueue.enqueue(2);
    randomizedQueue.enqueue(3);
    randomizedQueue.enqueue(4);
    randomizedQueue.enqueue(5);
    randomizedQueue.enqueue(6);
    randomizedQueue.enqueue(7);
    randomizedQueue.dequeue();
    randomizedQueue.dequeue();
    randomizedQueue.dequeue();
    randomizedQueue.dequeue();
    randomizedQueue.dequeue();
    randomizedQueue.dequeue();
    Assert.AreSame(2, randomizedQueue.size());
  }
}