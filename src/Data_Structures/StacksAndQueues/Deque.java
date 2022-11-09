package Data_Structures.StacksAndQueues;

// Deque
// [ First item | .. | .. | ... | .. | .. | Last item ]
// Deques are double ended queues, and hence an item can be removed or added from:
//  The front (left) of the deque
//  The end (right) of the deque
public class Deque<E extends Comparable<E>> {

    private final DNode<E> head;
    private final DNode<E> tail;
    private int size;

    public Deque() {
        DNode<E> tempHead = new DNode<E>(null);
        DNode<E> tempTail = new DNode<E>(null);
        tempHead.next = tempTail;
        tempTail.prev = tempHead;
        this.head = tempHead;
        this.tail = tempTail;
        this.size = 0;
    }

    // Returns whether the deque is empty
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Adds an item to the front (left) of the deque
    public void addFront(E item) {
        DNode<E> newNode = new DNode<E>(head, item, head.next);
        head.next.prev = newNode;
        head.next = newNode;
        size++;
    }

    // Adds an item to the back (right) of the deque
    public void addBack(E item) {
        DNode<E> newNode = new DNode<E>(tail.prev, item, tail);
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
    }

    // Removes an item from the front (left) of the deque
    public E removeFront() {
        if (!isEmpty()) {
            DNode<E> resNode = head.next;
            head.next.next.prev = head;
            head.next = resNode.next;
            size--;
            return resNode.item;
        }
        return null;
    }

    // Removes an item from the back (right) of the deque
    public E removeBack() {
        if (!isEmpty()) {
            DNode<E> resNode = tail.prev;
            tail.prev.prev.next = tail;
            tail.prev = resNode.prev;
            size--;
            return resNode.item;
        }
        return null;
    }

    // Returns the size of the deque
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return head.toString();
    }

    private static class DNode<E extends Comparable<E>> {
        private DNode<E> prev;
        private DNode<E> next;
        private final E item;

        public DNode(E item) {
            this(null, item, null);
        }

        public DNode(DNode<E> prev, E item, DNode<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        public <T> String nullToString(T value) {
            return value == null ? "null" : value.toString();
        }

        @Override
        public String toString() {
            // We only print item and next to avoid infinite recursion
            return nullToString(item) + " | " + nullToString(next);
        }

    }

    // MAIN
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFront(1);
        deque.addFront(2);
        deque.addFront(3);
        deque.addBack(4);
        deque.addBack(5);
        System.out.println(deque.removeFront());
        System.out.println(deque.removeBack());
        System.out.println(deque);
    }

}
