package AVLTrees;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<E extends Comparable<E>> {

  private int size;
  private AVLNode<E> root;

  public AVLTree() {
    this.size = 0;
    this.root = null;
  }

  // TODO: Add

  public boolean add(E item) {
    List<E> addedItems = new ArrayList<>();
    root = add(root, item, addedItems);
    return !addedItems.isEmpty();
  }

  private AVLNode<E> add(AVLNode<E> curr, E item, List<E> state) {
    if (curr == null) {
      curr = new AVLNode<>(item);
      size++;
      state.add(item);
    } else if (item.compareTo(curr.item) == 0) {
      return curr;
    } else if (item.compareTo(curr.item) < 0) {
      curr.left = add(curr.left, item, state);
    } else {
      curr.right = add(curr.right, item, state);
    }
    curr = rebalanceIfNeeded(curr);
    curr.height = Math.max(height(curr.left), height(curr.right)) + 1;
    return curr;
  }

  // TODO: Remove

  public boolean remove(E item) {
    List<E> state = new ArrayList<>();
    root = remove(root, item, state);
    return !state.isEmpty();
  }

  private AVLNode<E> remove(AVLNode<E> curr, E item, List<E> state) {
    if (curr == null) {
      return null;
    }
    if (item.compareTo(curr.item) == 0) {
      state.add(item);
      size--;
      curr = deleteNode(curr);
    } else if (item.compareTo(curr.item) < 0) {
      curr.left = remove(curr.left, item, state);
    } else {
      curr.right = remove(curr.right, item, state);
    }
    if (curr != null) {
      curr = rebalanceIfNeeded(curr);
      curr.height = Math.max(height(curr.left), height(curr.right)) + 1;
    }
    return curr;
  }

  private AVLNode<E> deleteNode(AVLNode<E> curr) {
    if (curr.left == null && curr.right == null) {
      return null;
    }
    if (curr.left == null) {
      return curr.right;
    }
    if (curr.right == null) {
      return curr.left;
    }
    AVLNode<E> minNode = findMin(curr.right);
    return new AVLNode<>(minNode.item, curr.left, removeMin(curr.right));
  }

  private AVLNode<E> findMin(AVLNode<E> curr) {
    if (curr.left == null) {
      return curr;
    }
    return findMin(curr.left);
  }

  private AVLNode<E> removeMin(AVLNode<E> curr) {
    if (curr.left == null) {
      return curr.right;
    }
    return removeMin(curr.left);
  }

  // TODO: Rebalance

  private AVLNode<E> rebalanceIfNeeded(AVLNode<E> curr) {
    final int deltaHeight = height(curr.left) - height(curr.right);

    if (deltaHeight == 2) {
      if (height(curr.left.left) >= height(curr.left.right)) {
        return rightRotation(curr);
      } else {
        return leftRightRotation(curr);
      }
    } else if (deltaHeight == -2) {
      if (height(curr.right.right) >= height(curr.right.left)) {
        return leftRotation(curr);
      } else {
        return rightLeftRotation(curr);
      }
    } else {
      return curr;
    }
  }

  // TODO: Rotate Left

  private AVLNode<E> leftRotation(AVLNode<E> curr) {
    AVLNode<E> tempTree = curr.right;
    curr.right = tempTree.left;
    tempTree.left = curr;
    return tempTree;
  }

  // TODO: Rotate Right

  private AVLNode<E> rightRotation(AVLNode<E> curr) {
    AVLNode<E> tempTree = curr.left;
    curr.left = tempTree.right;
    tempTree.right = curr;
    return tempTree;
  }

  // TODO: Rotate Left-Right

  private AVLNode<E> leftRightRotation(AVLNode<E> curr) {
    curr.left = leftRotation(curr.left);
    return rightRotation(curr);
  }

  // TODO: Rotate Right-Left

  private AVLNode<E> rightLeftRotation(AVLNode<E> curr) {
    curr.right = rightRotation(curr.right);
    return leftRightRotation(curr);
  }

  // TODO: Contains

  public boolean contains(E item) {
    AVLNode<E> curr = root;

    while (curr != null) {
      if (item.compareTo(curr.item) == 0) {
        return true;
      }
      if (item.compareTo(curr.item) < 0) {
        curr = curr.left;
      } else {
        curr = curr.right;
      }
    }
    return false;
  }

  // TODO: IsEmpty

  public boolean isEmpty() {
    return size == 0;
  }

  // TODO: Size

  public int getSize() {
    return size;
  }

  // TODO: Height

  public int height(AVLNode<E> node) {
    if (node == null) {
      return -1;
    }
    return node.height;
  }

  public int size() {
    return size;
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = new AVLTree<>();
    tree.add(1);
    tree.add(4);
    tree.add(-1);
    tree.add(7);
    tree.add(2);
    tree.remove(4);
    System.out.println("done");
    System.out.println(tree.contains(1));
    System.out.println(tree.contains(3));
    System.out.println(tree.contains(7));
  }

  private static class AVLNode<E extends Comparable<E>> {
    private int height;
    private AVLNode<E> left;
    private AVLNode<E> right;
    private E item;

    private AVLNode(E item) {
      this.height = 0;
      this.left = null;
      this.right = null;
      this.item = item;
    }

    private AVLNode(E item, AVLNode<E> left, AVLNode<E> right) {
      this.height = 0;
      this.left = left;
      this.right = right;
      this.item = item;
    }
  }
}
