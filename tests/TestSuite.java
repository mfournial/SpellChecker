import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import Dictree.*;

import static Dictree.TestSuiteHelper.readFile;
import static Dictree.TestSuiteHelper.runMain;
import static org.junit.Assert.assertEquals;


/**
 * Created by mfournial on 22/04/2017.
 * Test suite for Dictree
 */
public class TestSuite {

  /*
   *  These tests enable you to work on the data structure
   */
  @Test
  public void testMainLight() {

    // test file
    String punctuationTest = "tests/Dictree/PunctuationTest.txt";
    String exampleSmallTest = "tests/Dictree/ExampleSmallTest.txt";

    // test dictionary
    String smallDictionary = "tests/Dictree/smallDictionary.txt";

    // small dictionary tests
    assertEquals(readFile(punctuationTest), runMain(Optional.of(smallDictionary), "Punctuation.txt"));
    assertEquals(readFile(exampleSmallTest), runMain(Optional.of(smallDictionary), "example.txt"));

  }

  @Test
  public void testMain() {

    // test files
    String exampleTest = "tests/Dictree/ExampleTest.txt";
    String analysisTest = "tests/Dictree/AnalysisTest.txt";
    String hamletTest = "tests/Dictree/HamletTest.txt";

    // test dictionary
    // we'll use the big one that loads by default

    // full dictionary tests
    assertEquals(readFile(exampleTest), runMain("example.txt"));
    assertEquals(readFile(analysisTest), runMain("Analysis.txt"));

    // Bit of a challenge: analyse hamlet
    long startTime = System.nanoTime();
    assertEquals(readFile(hamletTest), runMain("Hamlet.txt"));
    long endTime = System.nanoTime();

    long durationMS = (endTime - startTime) / 1000000;
    System.out.println("Duration of Hamlet test : " + durationMS + "ms");

  }

  @Test
  public void testLoad() throws IOException {

    // dictionaries
    String smallDictionary = "tests/Dictree/smallDictionary.txt";
    String bigDictionary = "src/dictionary.txt";

    Dictree dictree = new RadixTree();

    // timed tests
    long startTime = System.nanoTime();
    dictree.load(new BufferedReader(new FileReader(smallDictionary)));
    long  endTime = System.nanoTime();

    long durationSmall = (endTime - startTime) / 1000000;

    startTime = System.nanoTime();
    dictree.load(new BufferedReader(new FileReader(bigDictionary)));
    endTime = System.nanoTime();

    long durationBig = (endTime - startTime) / 1000000;

    System.out.println("Loading times\n" + "Small dictionary: " + durationSmall + "ms\n"
                       + "Big dictionary: " + durationBig + "ms");
  }
}
