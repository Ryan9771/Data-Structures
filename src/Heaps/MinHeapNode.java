package Heaps;

import java.util.ArrayList;
import java.util.List;

public class MinHeapNode<E extends Comparable<E>> {

  // TODO: Constructor

  private int nextIndex;
  private MinNode<E> root;
  private static final int MAX_SIZE = 100;

  public MinHeapNode() {
    this.root = null;
    this.nextIndex = 0;
  }

  // TODO: Push

  public void push(E item) {
    if (isEmpty()) {
      root = new MinNode<>(item);
      nextIndex++;
    } else {
      if (nextIndex < MAX_SIZE) {
        String bin = Integer.toBinaryString(nextIndex + 1);
        root = add(item, root, bin.substring(1));
        siftUp(nextIndex);
        nextIndex++;
      }
    }
  }

  private MinNode<E> add(E item, MinNode<E> curr, String bin) {
    if (bin.isEmpty()) { // Traversal has reached the end
      return new MinNode<>(item);
    }
    if (bin.charAt(0) == '1') {
      curr.right = add(item, curr.right, bin.substring(1));
    } else {
      curr.left = add(item, curr.left, bin.substring(1));
    }
    return curr;
  }

  private void siftUp(int index) {
    while (index != 0 && find(index).item.compareTo(find((index - 1) / 2).item) < 0) {
      // While index != 0 and the current item is lesser than the parent, we need to swap
      swap((index - 1) / 2, index);
      index = (index - 1) / 2;
    }
  }

  // TODO: Swap

  /**
   * Swaps nodes of 2 indexes
   */
  private void swap(int parent, int curr) {
    MinNode<E> currNode = find(curr);
    E temp = find(parent).item;
    find(parent).item = currNode.item;
    currNode.item = temp;
  }

  // TODO: Find

  private MinNode<E> find(int index) {
    String bin =
        Integer.toBinaryString(
            index
                + 1); // Have to watch out for the +1s because the binary representation works with
    // 1 as the first node, not 0 as index 0 would be named!
    bin = bin.substring(1);
    MinNode<E> curr = root;
    while (!bin.isEmpty()) {
      if (bin.charAt(0) == '1') {
        curr = curr.right;
      } else {
        curr = curr.left;
      }
      bin = bin.substring(1);
    }
    return curr;
  }

  // TODO: Poll

  public E poll() {
    if (isEmpty()) {
      return null;
    }
    E res = root.item;
    nextIndex--;
    swap(0, nextIndex);
    if (nextIndex != 0) {
      siftDown();
    }
    find(nextIndex).item = null;
    return res;
  }

  // TODO: SiftDown

  private void siftDown() {
    int curr = 0;
    while (!isLeaf(curr)) {
      int minChild = 2 * curr + 1;
      if (minChild + 1 < nextIndex && find(minChild).item.compareTo(find(minChild + 1).item) > 0) {
        minChild++;
      }
      if (find(minChild).item.compareTo(find(curr).item) >= 0) {
        return;
      }
      swap(curr, minChild);
      curr = minChild;
    }
  }

  private boolean isLeaf(int index) {
    return index >= nextIndex / 2 && index < nextIndex;
  }

  // TODO: Is Empty

  public boolean isEmpty() {
    return nextIndex == 0;
  }

  // TODO: Size

  public int size() {
    return nextIndex;
  }

  // TODO: Peek

  public E peek() {
    return root == null ? null : root.item;
  }

  // TODO: Contains

  public boolean contains(E item) {
    E[] list = getList();
    int i = 0;
    while (item.compareTo(list[i]) <= 0) {
      if (item.compareTo(list[i]) == 0) {
        return true;
      }
      i++;
    }
    return false;
  }

  // TODO: Return Ordered List

  public E[] getList() {
    E[] list = (E[]) new Comparable[nextIndex];
    int size = nextIndex;
    for (int i = 0; i < size; i++) {
      list[size - 1 - i] = poll();
    }
    for (E elem : list) {
      push(elem);
    }
    return list;
  }

  @Override
  public String toString() {
    if (isEmpty()) {
      return "The tree is empty";
    }
    return root.toString();
  }

  public static void main(String[] args) {
    MinHeapNode<Integer> minHeap = new MinHeapNode<>();
    minHeap.push(5);
    minHeap.push(4);
    minHeap.push(6);
    minHeap.push(3);
    System.out.println(minHeap.peek());
    System.out.println(minHeap.contains(6));
    System.out.println(minHeap.contains(7));

    int s = minHeap.size();
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < s; i++) {
      list.add(minHeap.poll());
    }
    System.out.println(list);
  }

  private static class MinNode<E extends Comparable<E>> {
    private E item;
    private MinNode<E> left;
    private MinNode<E> right;

    private MinNode(E item) {
      this.item = item;
      this.left = this.right = null;
    }

    @Override
    public String toString() {
      return "(" + left + " [" + item + "] " + right + ")";
    }
  }
}
