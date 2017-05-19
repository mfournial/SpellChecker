package Dictree;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

public class ConcurrentRT implements Dictree {

  private AtomicInteger size;
  private NodeRadix root;
  Set<String> words;

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

    words = new HashSet<>();
    reader.lines().parallel().forEach(l -> words.add(l));

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
    return false;
  }

  @Override
  public int size() {
    return words.size();
  }
}
