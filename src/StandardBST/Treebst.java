package StandardBST;

import java.util.ArrayList;
import java.util.List;

public class Treebst<E extends Comparable<E>> {

  // TODO: Constructor

  private Nodebst<E> root;
  private int size;

  public Treebst() {
    this.root = null;
    this.size = 0;
  }

  // TODO: Add

  public boolean add(E elem) {
    int prevSize = size;
    root = add(elem, root);
    return size != prevSize;
  }

  private Nodebst<E> add(E elem, Nodebst<E> node) {
    if (node == null) {
      size++;
      return new Nodebst<>(elem);
    }
    if (elem.compareTo(node.item) == 0) {
      return node;
    }
    if (elem.compareTo(node.item) < 0) {
      node.left = add(elem, node.left);
      return node;
    } else {
      node.right = add(elem, node.right);
      return node;
    }
  }

  // TODO: Is Empty

  public boolean isEmpty() {
    return size == 0;
  }

  // TODO: Remove

  public boolean remove(E elem) {
    if (isEmpty()) {
      return false;
    }
    List<E> state = new ArrayList<>();
    root = remove(elem, root, state);
    return !state.isEmpty();
  }

  private Nodebst<E> remove(E elem, Nodebst<E> curr, List<E> state) {
    if (state == null) {
      return null;
    }
    if (elem.compareTo(curr.item) < 0) {
      curr.left = remove(elem, curr.left, state);
    } else if (elem.compareTo(curr.item) > 0) {
      curr.right = remove(elem, curr.right, state);
    } else {
      state.add(elem);
      size--;
      curr = deleteNode(curr);
    }
    return curr;
  }

  private Nodebst<E> deleteNode(Nodebst<E> curr) {

    if (curr.left == null && curr.right == null) {
      return null;
    }
    if (curr.left == null) {
      return curr.right;
    }
    if (curr.right == null) {
      return curr.left;
    }

    Nodebst<E> minNode = findMin(curr.right);
    return new Nodebst<>(minNode.item, curr.left, removeMin(curr.right));
  }

  private Nodebst<E> removeMin(Nodebst<E> curr) {
    if (curr == null) {
      return null;
    }
    if (curr.left == null) {
      return curr.right;
    }
    curr.left = removeMin(curr.left);
    return curr;
  }

  private Nodebst<E> findMin(Nodebst<E> curr) {
    if (curr == null) {
      return null;
    }
    if (curr.left == null) {
      return curr;
    }
    return findMin(curr.left);
  }

  // TODO: Contains

  public boolean contains(E elem) {
    Nodebst<E> curr = root;
    while (!(curr == null)) {
      if (elem.compareTo(curr.item) == 0) {
        return true;
      }
      if (elem.compareTo(curr.item) < 0) {
        curr = curr.left;
      } else {
        curr = curr.right;
      }
    }
    return false;
  }

  // TODO: Height

  public int height() {
    return height(root);
  }

  private int height(Nodebst<E> curr) {
    if (curr == null) {
      return 0;
    }
    return 1 + Math.max(height(curr.left), height(curr.right));
  }

  // TODO: Traversal

  public List<E> traverseInOrder() {
    List<E> res = new ArrayList<>();
    traverseInOrder(root, res);
    return res;
  }

  private void traverseInOrder(Nodebst<E> curr, List<E> res) {
    if (curr == null) {
      return;
    }
    traverseInOrder(curr.left, res);
    res.add(curr.item);
    traverseInOrder(curr.right, res);
  }

  @Override
  public String toString() {
    if (isEmpty()) {
      return "";
    }
    return root.toString();
  }

  public static void main(String[] args) {
    Treebst<Integer> tree = new Treebst<>();
    tree.add(1);
    tree.add(-1);
    tree.add(2);
    tree.add(4);
    tree.add(5);
    tree.add(0);
    tree.add(0);
//    System.out.println(tree.traverseInOrder());
//    System.out.println(tree);
//    tree.remove(4);
//    tree.remove(2);
//    tree.remove(1);
//    System.out.println(tree.traverseInOrder());
//    tree.remove(5);
//    tree.remove(-1);
//    tree.remove(0);
    System.out.println(tree);
  }
}
