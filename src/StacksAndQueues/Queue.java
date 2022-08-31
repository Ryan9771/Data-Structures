package StacksAndQueues;

/**
 * Queue are first in first out, so essentially add each element to the end.
 *
 * <p>Enqueue: Inserts an item at the rear of the queue. Dequeue: Removes the object from the front
 * of the queue and returns it, thereby decrementing queue size by one.
 */
public class Queue<E> {

  private SQnode<E> first;
  private SQnode<E> last;
  private int size;

  public Queue() {
    this.first = null;
    this.last = null;
    this.size = 0;
  }

  /**
   * Adds an item from the end of the list.
   *
   * @param item -> Item to be added.
   */
  public void enqueue(E item) {
    if (isEmpty()) {
      last = new SQnode<>(item);
      first = new SQnode<>(null, last);
    } else {
      last.setNext(new SQnode<>(item));
      last = last.getNext();
    }
    size++;
  }

  public E dequeue() {
    if (isEmpty()) {
      return null;
    }
    E res = first.getNext().getItem();
    if (first.getNext() == last) {
      first = null;
      last = null;
    } else {
      first.setNext(first.getNext().getNext());
    }
    size--;
    return res;
  }

  public boolean isEmpty() {
    return last == null;
  }
}
