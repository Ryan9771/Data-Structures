package StacksAndQueues;

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
        if (isEmpty()) {
            DNode<E> newNode = new DNode<E>(head, item, tail);
            head.next = newNode;
            tail.prev = newNode;
        } else {
            DNode<E> newNode = new DNode<E>(head, item, head.next);
            head.next.prev = newNode;
            head.next = newNode;
        }
        size++;
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
        System.out.println(deque);
    }
    
}
