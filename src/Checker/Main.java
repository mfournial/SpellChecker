package Checker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Dictree.Dictree;
import Dictree.RadixTree;

/**
 * Created by mfournial on 21/04/2017.
 * Demonstration of Dictree implementation
 */
public class Main {
  // Maximum length of a word and number of words counted
  final static int LENGHT = 40;
  static int words = 0;

  // Default dictionary and document
  final static String defaultdictionary = "src/dictionary.txt";
  final static String defaultdir = "src/inputs/";

  public static void main(String[] argv) {
    // Check correct number of arguments
    if(argv.length != 1 && argv.length != 2) {
      System.out.println("Usage: java Speller [dictionary] file");
      return;
    }

    // Tries to read dictionary
    FileReader dictionary;
    try {
      if(argv.length == 2) {
        dictionary = new FileReader(argv[0]);
      } else {
        dictionary = new FileReader(defaultdictionary);
      }
    } catch (FileNotFoundException e) {
      System.out.println("Could not read Dictionary");
      return;
    }

    BufferedReader reader = new BufferedReader(dictionary);
    Dictree dictree;

    try {
      dictree = new RadixTree(reader);
    } catch (IOException e) {
      System.err.println("Could not load the dictionary");
      return;
    }

    try {
      reader.close();
    } catch (IOException e) {
      System.err.println("Could not close the dictionary");
    }

    // Tries to open doc
    FileReader document;
    try {
      if (argv.length == 2) {
        document = new FileReader(defaultdir + argv[1]);
      } else {
        document = new FileReader(defaultdir + argv[0]);
      }
    } catch (FileNotFoundException e) {
      System.out.println("Could not read Document");
      return;
    }

    System.out.println("Reading from: " + defaultdir + argv[0]);

    // Prepares to report misspelled words
    ArrayList<String> typos = new ArrayList<>();

    // Purify the reading
    reader = new BufferedReader(document);

    // get each word
    ArrayList<Character> current = new ArrayList<>();
    // protection against words too long
    int length = 0;

    // Goes through each words
    try {
      for (int c = reader.read(); c != -1; c = reader.read()) {
        // Letter in a word
        if((Character.isAlphabetic(c) || c == '\'') && length < LENGHT) {
          current.add((char) c);
          length++;
        }

        // Ignore numbers in a word
        else if(Character.isDigit(c)) {
          // Consumes the word till its end
          while((c = reader.read()) != -1 && Character.isAlphabetic(c));

          // Prepares for next word
          length = 0;
          current = new ArrayList<>();
        }

        // Should be a word
        else {

          if(current.isEmpty()) {
            continue;
          }

          words++;

          // Creates the word
          char[] word = new char[current.size()];
          for(int i = 0; i < current.size(); i++) {
            word[i] = Character.toLowerCase(current.get(i));
          }

          // If word doesn't exists, report it
          if(!dictree.check(new String(word))){
            typos.add(new String(word));
          }

          // Prepares for next word
          length = 0;
          current = new ArrayList<>();
        }
      }

      // Closes our document when we finished reading
      reader.close();
    } catch (IOException e) {
      System.out.println("Error reading document");
    }

    // Prints the mistakes
    System.out.println("Numbers of words checked: " + words);
    System.out.println("Numbers of words in dictionary: " + dictree.size());
    System.out.println("Misspelled words:");
    for (String typo: typos) {
      System.out.println(typo);
    }

    // Exit gracefully
    try {
      dictionary.close();
      document.close();
      reader.close();
      words = 0;
    } catch (IOException e) {
      System.out.println("Error closing the files");
    }

    // We're done
  }
}

