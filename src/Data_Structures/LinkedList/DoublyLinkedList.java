package Data_Structures.LinkedList;

public class DoublyLinkedList<E> {

  private int size;
  private DoubleNode<E> head;
  private DoubleNode<E> tail;

  public DoublyLinkedList() {
    this.size = 0;
    this.head = new DoubleNode<>(null, Integer.MIN_VALUE, null, null);
    this.tail = new DoubleNode<>(null, Integer.MAX_VALUE, head, null);
    head.next = tail;
  }

  // TODO: Add

  public boolean add(E item) {
    int hash = item.hashCode();
    if (isEmpty()) {
      DoubleNode<E> newNode = new DoubleNode<>(item, head, tail);
      head.next = newNode;
      tail.prev = newNode;
      size++;
      return true;
    }
    Pair<DoubleNode<E>, DoubleNode<E>> pair = find(hash);
    if (pair.getSecond().key == hash) {
      return false; // item already exists
    }
    DoubleNode<E> newNode = new DoubleNode<>(item, pair.getFirst(), pair.getSecond());
    pair.getFirst().next = newNode;
    pair.getSecond().prev = newNode;
    size++;
    return true;
  }

  // TODO: Remove

  public boolean remove(E item) {
    if (isEmpty()) {
      return false;
    }
    int hash = item.hashCode();
    Pair<DoubleNode<E>, DoubleNode<E>> pair = find(hash);
    if (pair.getSecond().key == hash) {
      size--;
      pair.getFirst().next = pair.getSecond().next;
      pair.getSecond().next.prev = pair.getFirst();
      return true;
    }
    return false;
  }

  // TODO: Contains

  public boolean contains(E item) {
    int hash = item.hashCode();
    Pair<DoubleNode<E>, DoubleNode<E>> pair = find(hash);
    return pair.getSecond().key == hash;
  }

  // TODO: Size

  public int size() {
    return size;
  }

  // TODO: IsEmpty

  public boolean isEmpty() {
    return size == 0;
  }

  // TODO: Find

  public Pair<DoubleNode<E>, DoubleNode<E>> find(int hash) {
    DoubleNode<E> pred = head;
    DoubleNode<E> curr = head.next;

    while (curr.key < hash) {
      pred = curr;
      curr = curr.next;
    }

    return new Pair<>(pred, curr);
  }

  public static void main(String[] args) {
    DoublyLinkedList<Integer> llist = new DoublyLinkedList<>();
    llist.add(5);
    llist.add(7);
    llist.add(4);
    llist.add(6);
    llist.remove(6);
    llist.remove(4);
    llist.remove(5);
    System.out.println("Done");
  }

  private static class DoubleNode<E> {
    private final E item;
    private final int key;
    private DoubleNode<E> prev;
    private DoubleNode<E> next;

    public DoubleNode(E item, DoubleNode<E> prev, DoubleNode<E> next) {
      this.item = item;
      this.prev = prev;
      this.next = next;
      this.key = item.hashCode();
    }

    public DoubleNode(E item, int key, DoubleNode<E> prev, DoubleNode<E> next) {
      this.item = item;
      this.prev = prev;
      this.next = next;
      this.key = key;
    }
  }
}
