package Data_Structures.LinkedList;

public class Pair<E, V> {

  private final E key;
  private final V value;

  public Pair(E key, V value) {
    this.key = key;
    this.value = value;
  }

  public E getFirst() {
    return key;
  }

  public V getSecond() {
    return value;
  }
}
