package Heaps;

public class MaxHeap<E extends Comparable<E>> {
    
    // TODO: Constructor
    private final E[] heap;
    private int size;
    private int nextIndex;
    private static final int MAX_SIZE = 100;

    public MaxHeap() {
        this(MAX_SIZE);
    }

    public MaxHeap(int size) {
        heap = (E[]) new Comparable[size];
        nextIndex = 0;
        size = 0;
    }

    // Push
    public void push(E elem) {
        if (nextIndex == MAX_SIZE) {
           return; 
        }
        if (isEmpty()) {
            heap[0] = elem;
        } else {
            heap[nextIndex] = elem;
            int curr = nextIndex;
            while (curr != 0) {
                if (heap[curr].compareTo(heap[parent(curr)]) > 0) {
                   swap(curr, parent(curr));
                   curr = parent(curr); 
                } else {
                    break;
                }
            }
        }
        nextIndex++;

    }

    // Poll
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        nextIndex--;
        swap(0, nextIndex);
        if (nextIndex != 0) {
            heapify();
        }
        E res = heap[nextIndex];
        heap[nextIndex] = null;
        return res;
    }
    
    // Returns the first value of the heap without polling it
    public E peek() {
        return heap[0];
    }

    // Returns if an element is in the heap
    public boolean contains(E elem) {
        for (int i = 0; i < nextIndex; i++) {
            if (heap[i].compareTo(elem) == 0) {
                return true;
            }
        }
        return false;
    }

    // Returns the size of the heap
    public int getSize() {
        return nextIndex;
    }
    // Helper functions

    // Heapifies the heap
    private void heapify() {
        int curr = 0;
        while (!isLeaf(curr)) {
            int maxChild = leftChild(curr);
            if (maxChild + 1 < nextIndex && heap[maxChild].compareTo(heap[maxChild + 1]) < 0) {
                maxChild++;
            }
            if (heap[maxChild].compareTo(heap[curr]) < 0) {
                return;
            }
            swap(maxChild, curr);
            curr = maxChild;
        }
    }
    // Returns whether the heap is empty
    public boolean isEmpty() {
        return nextIndex == 0;
    }

    // Returns the parent index of the given index 
    private int parent(int index) {
        return (index - 1) / 2; 
    }

    // Returns the index of the leftchild of the given index
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // Returns whether the given index is a leaf index
    private boolean isLeaf(int index) {
        return index < MAX_SIZE && (index >= nextIndex / 2);
    }

    // Swaps 2 elements of given indexes in the heap
    private void swap(int index1, int index2) {
        E temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    // To test the MaxHeap
    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.push(1);
        maxHeap.push(2);
        maxHeap.push(3);
        maxHeap.push(5);
        maxHeap.push(4);
        int size = maxHeap.getSize();
        for (int i = 0; i < size; i++) {
            System.out.println(maxHeap.poll());
        }
    }
}
