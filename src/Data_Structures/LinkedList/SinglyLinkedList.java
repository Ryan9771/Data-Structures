package Data_Structures.LinkedList;

public class SinglyLinkedList<E> {

  // TODO: Constructor

  private final LinkedNode<E> head;
  private int size;

  public SinglyLinkedList() {
    LinkedNode<E> tail = new LinkedNode<>(null, Integer.MAX_VALUE, null);
    this.head = new LinkedNode<>(null, Integer.MIN_VALUE, tail);
    this.size = 0;
  }

  // TODO: Add

  public void add(E item) {
    int hash = item.hashCode();
    Pair<LinkedNode<E>, LinkedNode<E>> pair = find(hash);
    if (pair.getSecond().key != hash) {
      pair.getFirst().next = new LinkedNode<>(item, pair.getSecond());
      size++;
    }
  }

  // TODO: Remove

  public void remove(E item) {
    int hash = item.hashCode();
    Pair<LinkedNode<E>, LinkedNode<E>> pair = find(hash);
    if (pair.getSecond().key == hash) {
      pair.getFirst().next = pair.getSecond().next;
      size--;
    }
  }

  // TODO: Find

  private Pair<LinkedNode<E>, LinkedNode<E>> find(int hash) {
    LinkedNode<E> pred = head;
    LinkedNode<E> curr = head.next;

    while (curr.key < hash) {
      pred = curr;
      curr = curr.next;
    }

    return new Pair<>(pred, curr);
  }

  // TODO: CONTAINS

  public boolean contains(E item) {
    int hash = item.hashCode();
    Pair<LinkedNode<E>, LinkedNode<E>> pair = find(hash);
    return pair.getSecond().key == hash;
  }

  // TODO: IsEmpty

  public boolean isEmpty() {
    return size == 0;
  }

  // TODO: SIZE

  public int size() {
    return size;
  }

  public static void main(String[] args) {
    SinglyLinkedList<Integer> llist = new SinglyLinkedList<>();
    System.out.println("Stage 1");
    llist.add(69);
    System.out.println("Stage 2");
    llist.add(80);
    System.out.println("Stage 3");
    llist.add(80);
    System.out.println("Stage 4");
    llist.add(20);
    System.out.println("Stage 5");
    System.out.println(llist.contains(80));
    System.out.println(llist.contains(0));
    llist.remove(80);
    System.out.println("Done");
  }

  // ********** INNER CLASS LINKED NODE ************

  private static class LinkedNode<E> {

    private final E item;
    private LinkedNode<E> next;
    private final int key;

    private LinkedNode(E item, LinkedNode<E> next) {
      this(item, item.hashCode(), next);
    }

    private LinkedNode(E item, int key, LinkedNode<E> next) {
      this.item = item;
      this.key = key;
      this.next = next;
    }

    @Override
    public String toString() {
      if (item == null) {
        return "nullItem";
      }
      if (next == null) {
        return "null";
      }
      return "(" + item + "-> " + next + ")";
    }

  }
}
