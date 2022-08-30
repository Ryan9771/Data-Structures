package StacksAndQueues;

/** Stacks are first in last out. So push elements further in as you add more elements. */
public class Stacks<E> {

  private SQnode<E> head;
  private SQnode<E> tail;
  private int size;

  public Stacks() {
    this.size = 0;
    this.head = null;
    this.tail = null;
  }

  public void push(E item) {
    if (isEmpty()) {
      tail = new SQnode<>(null);
      head = new SQnode<>(item, tail);
    } else {
      head = new SQnode<>(item, head);
    }
    size++;
  }

  public E pop() {
    if (isEmpty()) {
      return null;
    }
    E res = head.getItem();
    if (head.getNext() == tail) {
      head = null;
      tail = null;
    } else {
      head = head.getNext();
    }
    size--;
    return res;
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }
}
