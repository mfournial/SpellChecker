package Dictree;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mfournial on 21/04/2017.
 */
public interface Dictree {

  // Load the dictionary in memory from a buffered reader
  // TODO add the pres about dic
  void load(BufferedReader reader);

  // Checks if the word exists
  // TODO add the spres about word
  boolean check(String word);

  // Return the number of words loaded, -1 if none
  int size();

  // Default method to check if tree is loaded
  default boolean isLoaded() {
    return size() !=  -1;
  }
}
