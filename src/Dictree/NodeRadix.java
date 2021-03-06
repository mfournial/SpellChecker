package Dictree;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by mfournial on 21/04/2017.
 */
class NodeRadix implements Serializable{
  public final int NB_LETTERS = 27; // 26 letters + '

  private boolean isWord;
  private NodeRadix[] next;

  NodeRadix() {
    isWord = false;
  }

  protected boolean isWord() {
    return isWord;
  }

  protected boolean hasNext() {
    return next != null;
  }

  protected void addNext() {
    this.next = new NodeRadix[NB_LETTERS];
    for (int i = 0; i < NB_LETTERS; i++) {
      next[i] = new NodeRadix();
    }
  }

  protected Optional<NodeRadix> getNode(int i) {
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

  protected void forgetWord() { isWord = false; }

}
