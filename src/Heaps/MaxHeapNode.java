package Heaps;

public class MaxHeapNode<E extends Comparable<E>> {
    
    private int nextIndex;
    private MaxNode<E> root;
    private static final int MAX_SIZE = 100;

    public MaxHeapNode() {
        this.nextIndex = 0;
        this.root = null;
    }

    /**
     * Adds an item to the MaxHeap
     */
    public void push(E item) {
        if (isEmpty()) {
            root = new MaxNode<E>(item);
            nextIndex++;
        } else if (nextIndex < MAX_SIZE) {
            String bin = Integer.toBinaryString(nextIndex + 1).substring(1);
            root = add(item, root, bin);
            siftUp(nextIndex);
            nextIndex++;
        }

    }

    /**
     * Removes the largest item from the MaxHeap
     */
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        nextIndex--;
        swap(0, nextIndex);
        if (nextIndex != 0) {
            heapify();
        }
        MaxNode<E> res = find(nextIndex);
        E resItem = res.item;
        res = null;
        return resItem;
    }

    /**
     * Returns the first item without polling
     */
    public E peek() {
        return root == null ? null : root.item;
    }

    /**
     * Returns whether the heap is empty
     */
    public boolean isEmpty() {
        return nextIndex == 0;
    }

    /**
     * Returns the size of the heap
     */
    public int size() {
        return nextIndex;
    }

    /**
     * Helper method that recursively adds a node 
     */
    private MaxNode<E> add(E item, MaxNode<E> curr, String bin) {
        if (bin.isEmpty()) {
            return new MaxNode<E>(item);
        }
        if (bin.charAt(0) == '1') {
            curr.right = add(item, curr.right, bin.substring(1));
        } else {
            curr.left = add(item, curr.left, bin.substring(1));
        }
        return curr;
    }

    /**
     * Helper method that propagates the new inserted elem to the right position
     * 
     */
    private void siftUp(int curr) {
        while (curr != 0 && find(curr).item.compareTo(find(parent(curr)).item) > 0) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
    }

    /**
     * Heapifies the tree
     */
    private void heapify() {
        int curr = 0;
        while (!isLeaf(curr)) {
            int maxChild = leftChild(curr);
            if (maxChild + 1 < nextIndex && find(maxChild).item.compareTo(find(maxChild + 1).item) < 0) {
                maxChild++;
            }
            if (find(maxChild).item.compareTo(find(curr).item) <= 0) {
                return;
            }
            swap(maxChild, curr);
            curr = maxChild;
        }
    }

    /**
     * Finds the node with the particular index
     */
    private MaxNode<E> find(int index) {
        String bin = Integer.toBinaryString(index + 1).substring(1);
        MaxNode<E> curr = root;
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

    /**
     * Swaps the nodes of the 2 given indexes
     * 
     */
    private void swap(int index1, int index2) {
        MaxNode<E> node1 = find(index1);
        MaxNode<E> node2 = find(index2);
        E temp = node1.item;
        node1.item = node2.item;
        node2.item = temp;
    }

    /** 
     * Returns the parent of the given index
     */
    private int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Returns the left child of the given index
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * Returns whether the node is a leaf node
     */
    private boolean isLeaf(int index) {
        return index >= nextIndex / 2 && index < nextIndex;
    }

    @Override
    public String toString() {
        return root == null ? null : root.toString();
    }

    /**
     * The MaxNode class
     */
    private static class MaxNode<E extends Comparable<E>> {
        private E item;
        private MaxNode<E> left;
        private MaxNode<E> right;

        private MaxNode(E item) {
            this.item = item;
            this.left = this.right = null;
        }

        @Override
        public String toString() {
            return "[" + left + " (" + item + ") " + right + "]";
        }
    }

    /* ********** */

    public static void main(String[] args) {
        MaxHeapNode<Integer> maxHeap = new MaxHeapNode<>();
        maxHeap.push(1);
        maxHeap.push(2);
        maxHeap.push(3);
        maxHeap.push(5);
        maxHeap.push(4);
        System.out.println(maxHeap);

        int size = maxHeap.size();
        for (int i = 0; i < size; i++) {
            System.out.println(maxHeap.poll());
        }

    }

}
