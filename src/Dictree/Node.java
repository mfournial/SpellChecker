package Dictree;

import java.util.Optional;

/**
 * Created by mfournial on 21/04/2017.
 */
class Node {
  public final int NB_LETTERS = 27;

  private boolean isWord;
  private Node[] next;

  Node() {
    isWord = false;
  }

  protected boolean isWord() {
    return isWord;
  }

  protected boolean hasNext() {
    return next != null;
  }

  protected void addNext() {
    this.next = new Node[27];
    for (int i = 0; i < 27; i++) {
      next[i] = new Node();
    }
  }

  protected Optional<Node> getNode(int i) {
    assert 0 <= i && i <= 27;
    if(next == null) {
      return Optional.empty();
    } else {
      return Optional.of(next[i]);
    }
  }

  protected void setWord() {
    isWord = true;
  }

}
