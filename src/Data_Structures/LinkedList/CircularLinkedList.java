package Data_Structures.LinkedList;

public class CircularLinkedList<E> {

  private final CircNode<E> head;
  private int size;

  public CircularLinkedList() {
    head = new CircNode<>(null, Integer.MAX_VALUE, null);
    this.size = 0;
  }

  // TODO: Add

  public boolean add(E item) {
    if (isEmpty()) {
      head.next = new CircNode<>(item, head);
      size++;
      return true;
    }
    Pair<CircNode<E>, CircNode<E>> pair = find(item.hashCode());
    if (pair.getSecond().key == item.hashCode()) {
      return false; // Item already exists
    }
    pair.getFirst().next = new CircNode<>(item, pair.getSecond());
    size++;
    return false;
  }

  // TODO: Remove

  public boolean remove(E item) {
    if (isEmpty()) {
      return false;
    }
    Pair<CircNode<E>, CircNode<E>> pair = find(item.hashCode());
    if (pair.getSecond().key == item.hashCode()) { // the item does exist
      if (pair.getSecond().next == head) { // the next item is head
        pair.getFirst().next = null;
      } else { // otherwise, set the first node's next to the second node's next
        pair.getFirst().next = pair.getSecond().next;
      }
      size--;
      return true;
    } else { // the item doesn't exist
      return false;
    }
  }

  // TODO: IsEmpty

  public boolean isEmpty() {
    return size == 0;
  }

  // TODO: Contains

  public boolean contains(E item) {
    int hash = item.hashCode();
    Pair<CircNode<E>, CircNode<E>> pair = find(hash);
    return pair.getSecond().key == hash;
  }

  // TODO: Size

  public int size() {
    return size;
  }

  // TODO: Find Helper

  private Pair<CircNode<E>, CircNode<E>> find(int hash) {
    CircNode<E> pred = head;
    CircNode<E> curr = head.next;

    while (curr.key < hash) {
      pred = curr;
      curr = curr.next;
    }

    return new Pair<>(pred, curr);
  }

  private static class CircNode<E> {
    private final E item;
    private final int key;
    private CircNode<E> next;

    private CircNode(E item, CircNode<E> next) {
      this.item = item;
      this.key = item.hashCode();
      this.next = next;
    }

    private CircNode(E item, int key, CircNode<E> next) {
      this.item = item;
      this.key = key;
      this.next = next;
    }
  }
}
