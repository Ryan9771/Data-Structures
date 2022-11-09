package Data_Structures.StandardBST;

public class Nodebst<E extends Comparable<E>> {
  protected E item;
  protected Nodebst<E> left;
  protected Nodebst<E> right;

  public Nodebst(E elem) {
    this.item = elem;
    this.left = null;
    this.right = null;
  }

  public Nodebst(E elem, Nodebst<E> newLeft, Nodebst<E> newRight) {
    this.item = elem;
    this.left = newLeft;
    this.right = newRight;
  }

  @Override
  public String toString() {
    return "(" + left + "[" + item + "]" + right + ")";
  }
}
