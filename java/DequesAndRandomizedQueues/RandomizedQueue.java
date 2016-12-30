
/*----------------------------------------------------------------
 *  Author:        Pylyp Lebediev
 *  Written:       30/12/2016
 *  Last updated:  31/12/2016
 *
 *  Compilation:   javac RandomizedQueue.java
 *  Execution:     java RandomizedQueue
 *  
 *  Randomized queue
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Randomized queue
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

  // Array of items
  private Item[] a;

  // Number of elements on stack
  private int n;

  /**
   * Construct an empty randomized queue
   */
  @SuppressWarnings("unchecked")
  public RandomizedQueue() {
    this.a = (Item[]) new Object[2];
    this.n = 0;
  }

  /**
   * Is the queue empty?
   */
  public boolean isEmpty() {
    return this.n == 0;
  }

  /**
   * Return the number of items on the queue
   */
  public int size() {
    return this.n;
  }

  /**
   * Add the item
   */
  public void enqueue(Item item) {
    if (item == null) {
      String errorMessage = "Item that is equal to null cannot be added to Randomized Queue";
      throw new java.lang.NullPointerException(errorMessage);
    }

    if (this.a.length == this.n) {
      this.resize(this.n * 2);
    }

    this.a[n++] = item;
  }

  /**
   * Remove and return a random item
   */
  public Item dequeue() {
    if (this.isEmpty()) {
      String errorMessage = "Item cannot be removed from an empty Randomized Queue";
      throw new java.util.NoSuchElementException(errorMessage);
    }

    int index = this.getRandomItem();
    final Item item = this.a[index];
    this.a[index] = null;
    this.n--;

    int quarterSize = (this.a.length / 4);
    if (quarterSize >= this.n) {
      this.resize(quarterSize);
    }

    return item;
  }

  private int getRandomItem() {
    int index = 0;
    Item item = null;
    while (item == null) {
      index = StdRandom.uniform(0, this.a.length);
      item = this.a[index];
    }
    return index;
  }

  /**
   * Return (but do not remove) a random item
   */
  public Item sample() {
    if (this.isEmpty()) {
      String errorMessage = "Cannot sample on empty Randomized Queue";
      throw new java.util.NoSuchElementException(errorMessage);
    }

    int index = StdRandom.uniform(0, this.n + 1);
    Item item = this.a[index];

    return item;
  }

  /**
   * Return an independent iterator over items in random order
   */
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }

  /**
   * Unit testing
   */
  public static void main(String[] args) {
    RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    queue.enqueue(5);
    queue.enqueue(6);
    queue.enqueue(7);
    queue.enqueue(8);
    queue.dequeue();

    for (int item : queue) {
      System.out.println(item);
    }
  }

  private void resize(int size) {
    Item[] oldA = this.a;

    this.a = (Item[]) new Object[size];
    int j = 0;
    for (int i = 0; i < oldA.length; i++) {
      if (oldA[i] != null) {
        // Include shuffling, since items can be dequeued in random order.
        this.a[j++] = oldA[i];
      }
    }
  }

  // An iterator, doesn't implement remove() since it's optional
  private class RandomizedQueueIterator implements Iterator<Item> {

    private Item[] itA;

    private int current = 0;

    public RandomizedQueueIterator() {
      this.itA = (Item[]) new Object[n];

      int j = 0;
      for (int i = 0; i < a.length; i++) {
        if (a[i] != null) {
          this.itA[j++] = a[i];
        }
      }
    }

    public void remove() {
      throw new java.lang.UnsupportedOperationException("Remove operation is not supported");
    }

    public boolean hasNext() {
      return this.current < this.itA.length && this.itA[this.current] != null;
    }

    public Item next() {
      if (!this.hasNext()) {
        throw new java.util.NoSuchElementException("No nodes to iterate");
      }

      return this.itA[this.current++];
    }
  }
}