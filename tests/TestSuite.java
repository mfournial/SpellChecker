import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import Dictree.*;

import static Dictree.TestSuiteHelper.readFile;
import static Dictree.TestSuiteHelper.runMain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by mfournial on 22/04/2017.
 * Test suite for Dictree
 */
public class TestSuite {

  // TODO Static loading for easier timing
  // TODO final strings for minimal code change

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

  /*
   *  Load test
   *  Depends on the implementation made by the programmer, should be run with a debugger that would
   *  tell a lot of about the state of the DS, especially for smallDictionary
   */
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

  /*
   *  First possible generalized test for the Dictree Interface, requires loading the dictionary
   */

  @Test
  public void testCheck() throws IOException {

    // Dictionaries
    String smallDictionary = "tests/Dictree/smallDictionary.txt";
    String bigDictionary = "tests/Dictree/bigDictionary.txt";

    /*
     Test words for small dictionary
     */

    // false
    String f1 = "b";
    String f2 = "bek";
    String f3 = "bey";
    String f4 = "'dont";
    String f5 = "do'nt";
    String f6 = "dontt";
    String f7 = "";
    String f8 = "badbadly";
    String f9 = "spellchecker";

    //true
    String t1 = "a";
    String t2 = "bad";
    String t3 = "badly";
    String t4 = "spell";
    String t5 = "should";
    String t6 = "don't";
    String t7 = "dont";
    String t8 = "this";
    String t9 = "the";

    /*
     Test words for big dictionary
     */

    // false
    // true
    // TODO

    /*
     Runs the tests
     */

    // small dictionary
    Dictree dictree = new RadixTree(new BufferedReader(new FileReader(smallDictionary)));

    assertFalse(dictree.check(f1));
    assertFalse(dictree.check(f2));
    assertFalse(dictree.check(f3));
    assertFalse(dictree.check(f4));
    assertFalse(dictree.check(f4));
    assertFalse(dictree.check(f5));
    assertFalse(dictree.check(f6));
    assertFalse(dictree.check(f7));
    assertFalse(dictree.check(f8));
    assertFalse(dictree.check(f9));

    assertTrue(dictree.check(t1));
    assertTrue(dictree.check(t2));
    assertTrue(dictree.check(t3));
    assertTrue(dictree.check(t4));
    assertTrue(dictree.check(t5));
    assertTrue(dictree.check(t6));
    assertTrue(dictree.check(t7));
    assertTrue(dictree.check(t8));
    assertTrue(dictree.check(t9));
  }
}
