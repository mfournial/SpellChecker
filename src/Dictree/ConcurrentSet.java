package Dictree;

import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by mfournial on 19/05/2017.
 * Objective is to hold the Strings fetched in parallel by the bufferedReader
 */
public class ConcurrentSet {

  private final AtomicReference<Set<String>> set;

  public ConcurrentSet() {
    set = new AtomicReference<>();
  }
}
