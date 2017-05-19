package Dictree;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

public class ConcurrentRT implements Dictree {

  private final AtomicInteger size;
  private volatile NodeRadix root;

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
}
