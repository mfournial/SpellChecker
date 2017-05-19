package Dictree;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by mfournial on 21/04/2017.
 * Radix Tree proposes a longer loading time of the dictionary but a very fast check time -O(N)-
 */
public class RadixTree implements Dictree {

  private int size;
  private NodeRadix root;

  public RadixTree(BufferedReader reader) throws IOException{
    load(reader);
  }

  public RadixTree() {
    size = -1;
  }

  @Override
  public void load(BufferedReader reader) {

    // Resets the class
    size = 0;
    root = new NodeRadix();

    // Reads the dictionary line by line, corresponding to a new Word on each line
    String line;

    try {
      line = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      size = -1;
      root = null;
      return;
    }

    // Add all the word in the buffer
    while(line != null) {

      NodeRadix current = root;

      // Traverses the radix tree
      for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i).toLower();

        // Creates a children node if needed
        if (!current.hasNext()) {
          current.addNext();
        }

        // Goes to the children node at the relevant position
        // Indexes 0 to 25 are letters in order, 26 is '
        if (c == '\'') {
          current = current.getNode(26).get();
        } else {
          current = current.getNode(c - 'a').get();
        }
      }

      // Sets as a Word
      current.setWord();
      size++;

      // prepare next iteration
      try {
        line = reader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public boolean check(String word) {
    NodeRadix current = root;
    for(char c : word.toCharArray()) {
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

    // checks if the word exists or not
    return current.isWord();

  }

  @Override
  public int size() {
    return size;
  }
}
