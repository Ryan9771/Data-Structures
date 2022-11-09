package Data_Structures.StacksAndQueues;

public class SQnode<E> {
  private final E item;
  private SQnode<E> next;

  public SQnode(E item) {
    this.item = item;
    this.next = null;
  }

  public SQnode(E item, SQnode<E> next) {
    this.item = item;
    this.next = next;
  }

  public void setNext(SQnode<E> newNext) {
    next = newNext;
  }

  public SQnode<E> getNext() {
    return next;
  }

  public E getItem() {
    return item;
  }
}
