package Dictree;

import java.io.BufferedReader;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ConcurrentRT implements Dictree, java.io.Serializable {

  private final AtomicInteger size;
  private volatile NodeRadix root;
  private static long serialVersionUID = 555555555;

  public ConcurrentRT (BufferedReader reader) {
    size = new AtomicInteger(0);
    load(reader);
  }

  public ConcurrentRT() {
    size = new AtomicInteger(-1);
  }

  @Override
  public void load(BufferedReader reader) {

    size.set(0);
    root = new NodeRadix();

    reader.lines()
        .parallel()
        .forEach(l -> IntStream.range(0, l.length()).forEachOrdered( i -> {

          NodeRadix current = root;

          char c = Character.toLowerCase(l.charAt(i));

          if(!current.hasNext()) {
            current.addNext();
          }

          if (c == '\'') {
            current = current.getNode(26).get();
          } else {
           current = current.getNode(c - 'a').get();
          }

          if (i == l.length() - 1) {
            current.setWord();
            size.incrementAndGet();
          }
        }) );

//    StampedLock lock = new StampedLock();

//    reader.lines()
//        .parallel()
//        .forEach(l -> IntStream.range(0, l.length()).forEach( i -> {

//          NodeRadix current = root;

//          char c = l.charAt(i);

//          long stamp = lock.readLock();

//          try {
//            if (!current.hasNext()) {
//              stamp = lock.tryConvertToWriteLock(stamp);
//              current.addNext();
//            }

//            if (c == '\'') {
//              current = current.getNode(26).get();
//            } else {
//              current = current.getNode(c - 'a').get();
//            }
//          } finally {
//            lock.unlock(stamp);
//          }
//          if (i == l.length() - 1) {
//            stamp = lock.writeLock();
//            current.setWord();
//            size.incrementAndGet();
//          }
//        }) );
  }

  private void add(NodeRadix root, String l) {

  }

  @Override
  public boolean check(String word) {
    NodeRadix current = root;
    for(int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if(c == '\'') {
        if (current.getNode(26).isPresent()) {
          current = current.getNode(26).get();
        } else {
          return false;
        }
      } else if (current.getNode(c - 'a').isPresent()){
        current = current.getNode(c - 'a').get();
      } else {
        return false;
      }
    }
    return current.isWord();
  }

  @Override
  public int size() {
    return size.get();
  }

  @Override
  public void addUniqueWord(String word) {
    NodeRadix current = root;
    for (int i = 0; i < word.length(); i++) {
      char c = Character.toLowerCase(word.charAt(i));

      if(!current.hasNext()) {
        current.addNext();
      }

      if (c == '\'') {
        Optional<NodeRadix> next = current.getNode(26);
        current = next.get();
      } else {
        current = current.getNode(c - 'a').get();
      }
    }

    current.setWord();
  }

  @Override
  public void removeUniqueWord(String word) {

    NodeRadix current = root;

    for (int i = 0; i < word.length(); i++) {
      char c = Character.toLowerCase(word.charAt(i));
      if (c == '\'') {
        if (current.getNode(26).isPresent()) {
          current = current.getNode(26).get();
        } else {
          return;
        }
      } else if (current.getNode(c - 'a').isPresent()) {
        current = current.getNode(c - 'a').get();
      } else {
        return;
      }
    }

    current.forgetWord();
  }
}
