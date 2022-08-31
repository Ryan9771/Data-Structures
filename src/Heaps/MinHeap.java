package Heaps;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<E extends Comparable<E>> {

  // TODO: Constructor

  private final E[] heap;
  private final int size;
  private int nextIndex;
  private static final int MAX_SIZE = 100;

  public MinHeap() {
    this(MAX_SIZE);
  }

  public MinHeap(int size) {
    this.nextIndex = 0;
    this.size = size;
    this.heap = (E[]) new Comparable[size];
  }

  // TODO: Push

  public void push(E elem) {
    if (nextIndex == size) {
      return;
    }
    if (isEmpty()) {
      heap[0] = elem;
    } else {
      heap[nextIndex] = elem;
      int curr = nextIndex;
      while (curr != 0 && heap[curr].compareTo(heap[parent(curr)]) < 0) {
        swap(curr, parent(curr));
        curr = parent(curr);
      }
    }
    nextIndex++;
  }

  // TODO: Is Empty

  public boolean isEmpty() {
    return size == 0;
  }

  // TODO: Poll

  public E poll() {
    if (isEmpty()) {
      return null;
    }
    nextIndex--;
    swap(0, nextIndex);
    if (nextIndex != 0) { // need to do this, otherwise, look at comment at heapify
      heapify();
    }
    E res = heap[nextIndex];
    heap[nextIndex] = null;
    return res;
  }

  private void heapify() {
    int curr = 0;
    while (!isLeaf(curr)) {
      int minChild = leftChild(curr);
      if ((minChild + 1 < nextIndex) && (heap[minChild].compareTo(heap[minChild + 1]) > 0)) {
        minChild++;
      }
      if (heap[minChild].compareTo(heap[curr]) >= 0) {
        // if don't assert that nextIndex != 0 in poll, then this will give NPE for heap[minChild]
        // since minChild would be 1 even if nextIndex is 0
        return;
      }
      swap(minChild, curr);
      curr = minChild;
    }
  }

  // TODO: Size

  public int getSize() {
    return nextIndex;
  }

  // TODO: Peek

  public E peek() {
    return heap[0];
  }

  // TODO: Contains

  public boolean contains(E elem) {
    for (int i = 0; i < nextIndex; i++) {
      if (heap[i].compareTo(elem) == 0) {
        return true;
      }
    }
    return false;
  }

  // TODO: Index Helpers

  private int parent(int index) {
    return (index - 1) / 2;
  }

  private int leftChild(int index) {
    return 2 * index + 1;
  }

  private boolean isLeaf(int index) {
    return (index >= nextIndex / 2) && index < nextIndex;
  }

  private void swap(int ind1, int ind2) {
    E temp = heap[ind1];
    heap[ind1] = heap[ind2];
    heap[ind2] = temp;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (isEmpty()) {
      return "Heap is Empty";
    }
    for (int i = 0; i < nextIndex; i++) {
      sb.append(heap[i]).append(", ");
    }
    // sb.append(heap[nextIndex - 1]);
    return sb.toString();
  }

  public static void main(String[] args) {
    MinHeap<Integer> minHeap = new MinHeap<>();
    minHeap.push(5);
    minHeap.push(4);
    minHeap.push(6);
    minHeap.push(3);
    System.out.println(minHeap.peek());
    System.out.println(minHeap.contains(6));
    System.out.println(minHeap.contains(7));

    int s = minHeap.getSize();
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < s; i++) {
      list.add(minHeap.poll());
    }
    System.out.println(list);
  }
}
