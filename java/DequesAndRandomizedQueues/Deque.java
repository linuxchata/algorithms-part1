
/*----------------------------------------------------------------
 *  Author:        Pylyp Lebediev
 *  Written:       30/12/2016
 *  Last updated:  30/12/2016
 *
 *  Compilation:   javac Deque.java
 *  Execution:     java Deque
 *  
 *  Double-ended queue or deque 
 *
 *----------------------------------------------------------------*/

import java.util.Iterator;

/**
 * A double-ended queue or deque
 */
public class Deque<Item> implements Iterable<Item> {

  // First node.
  private Node first;

  // Last node.
  private Node last;

  // Size of the Deque
  private int n;

  /**
   * Construct an empty deque
   */
  public Deque() {
    this.first = null;
    this.last = null;
    this.n = 0;
  }

  /**
   * Is the deque empty?
   */
  public boolean isEmpty() {
    return this.n == 0;
  }

  /**
   * Return the number of items on the deque
   */
  public int size() {
    return this.n;
  }

  /**
   * Add the item to the front
   */
  public void addFirst(Item item) {
    if (item == null) {
      String errorMessage = "Item that is equal to null cannot be added to Deque";
      throw new java.lang.NullPointerException(errorMessage);
    }

    // Save old first node.
    Node oldFirstNode = this.first;
    // Create a new first node.
    Node node = new Node(item);
    node.next = oldFirstNode;
    this.first = node;

    // Update previous node for old first node, if old first node.
    if (oldFirstNode != null) {
      oldFirstNode.previous = this.first;
    }

    this.n++;

    if (this.last == null) {
      this.last = node;
    }
  }

  /**
   * Add the item to the end
   */
  public void addLast(Item item) {
    if (item == null) {
      String errorMessage = "Item that is equal to null cannot be added to Deque";
      throw new java.lang.NullPointerException(errorMessage);
    }

    if (this.last == null) {
      this.addFirst(item);
      return;
    }

    // Create a new last node.
    Node node = new Node(item);
    node.previous = this.last;
    this.last.next = node;
    this.last = this.last.next;
    this.n++;
  }

  /**
   * Remove and return the item from the front
   */
  public Item removeFirst() {
    if (this.isEmpty()) {
      String errorMessage = "Item cannot be removed from an empty Deque";
      throw new java.util.NoSuchElementException(errorMessage);
    }

    final Item item = this.first.item;
    this.first = this.first.next;
    this.first.previous = null;

    this.n--;

    return item;
  }

  /**
   * Remove and return the item from the end
   */
  public Item removeLast() {
    if (this.isEmpty()) {
      String errorMessage = "Item cannot be removed from an empty Deque";
      throw new java.util.NoSuchElementException(errorMessage);
    }

    final Item item = this.last.item;
    this.last = this.last.previous;
    this.last.next = null;

    this.n--;

    return item;
  }

  /**
   * Return an iterator over items in order from front to end
   */
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  /**
   * Unit testing
   */
  public static void main(String[] args) {
    Deque<Integer> deque1 = new Deque<Integer>();
    System.out.println(deque1.isEmpty());
    deque1.addFirst(4);
    deque1.addFirst(3);
    deque1.addFirst(2);
    deque1.addFirst(1);
    deque1.addLast(5);
    System.out.println("Size " + deque1.size());
    deque1.removeFirst();
    deque1.removeLast();
    System.out.println("Size " + deque1.size());
    System.out.println(deque1.isEmpty());
    for (Integer item : deque1) {
      System.out.println(item);
    }
  }

  // Helper linked list class
  private class Node {

    private Node previous;

    private Node next;

    private Item item;

    public Node(Item value) {
      this.item = value;
    }
  }

  // An iterator, doesn't implement remove() since it's optional
  private class DequeIterator implements Iterator<Item> {

    private Node current = first;

    public boolean hasNext() {
      return this.current != null;
    }

    public void remove() {
      throw new java.lang.UnsupportedOperationException("Remove operation is not supported");
    }

    public Item next() {
      if (!this.hasNext()) {
        throw new java.util.NoSuchElementException("No nodes to iterate");
      }

      Item item = this.current.item;
      this.current = this.current.next;
      return item;
    }
  }
}