/*----------------------------------------------------------------
 *  Author:        Pylyp Lebediev
 *  Written:       30/12/2016
 *  Last updated:  01/01/2017
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
 * Randomized queue.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    // Array of items.
    private Item[] a;

    // Number of elements on queue.
    private int n;

    /**
     * Construct an empty randomized queue.
     */
    public RandomizedQueue() {
        this.a = (Item[]) new Object[2];
        this.n = 0;
    }

    /**
     * Is the queue empty.
     */
    public boolean isEmpty() {
        return this.n == 0;
    }

    /**
     * Return the number of items on the queue.
     */
    public int size() {
        return this.n;
    }

    /**
     * Add the item.
     */
    public void enqueue(Item item) {
        if (item == null) {
            String errorMessage = "Item that is equal to null cannot be added to Randomized Queue";
            throw new java.lang.NullPointerException(errorMessage);
        }

        if (this.a.length == this.n) {
            this.resize(this.n * 2);
        }

        this.a[this.n++] = item;
    }

    /**
     * Remove and return a random item.
     */
    public Item dequeue() {
        if (this.isEmpty()) {
            String errorMessage = "Item cannot be removed from an empty Randomized Queue";
            throw new java.util.NoSuchElementException(errorMessage);
        }

        int index = StdRandom.uniform(0, this.n);
        Item item = this.a[index];
        this.a[index] = this.a[this.n - 1];
        this.a[this.n - 1] = null;
        this.n--;

        int quarterSize = (this.a.length / 4);
        if (this.n >= 2 && quarterSize >= this.n) {
            int halfSize = (this.a.length / 2);
            this.resize(halfSize);
        }

        return item;
    }

    /**
     * Return (but do not remove) a random item.
     */
    public Item sample() {
        if (this.isEmpty()) {
            String errorMessage = "Cannot sample on empty Randomized Queue";
            throw new java.util.NoSuchElementException(errorMessage);
        }

        int index = StdRandom.uniform(0, this.n);
        Item item = this.a[index];

        return item;
    }

    /**
     * Return an independent iterator over items in random order.
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    /**
     * Unit testing.
     */
    public static void main(String[] args) {
    }

    private void resize(int newSize) {
        Item[] oldA = this.a;

        this.a = (Item[]) new Object[newSize];

        int length = 0;
        if (newSize > oldA.length) {
            length = oldA.length;
        } else {
            length = newSize;
        }

        for (int i = 0; i < length; i++) {
            this.a[i] = oldA[i];
        }
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomizedQueueIterator implements Iterator<Item> {

        private RandomizedQueue<Item> iteratorQueue;

        public RandomizedQueueIterator() {
            iteratorQueue = new RandomizedQueue<Item>();

            for (int i = 0; i < a.length; i++) {
                if (a[i] != null) {
                    iteratorQueue.enqueue(a[i]);
                }
            }
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("Remove operation is not supported");
        }

        public boolean hasNext() {
            return this.iteratorQueue.size() > 0;
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new java.util.NoSuchElementException("No nodes to iterate");
            }

            return this.iteratorQueue.dequeue();
        }
    }
}