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
   *  Reusable data across tests
   */
  final String bigDictionary = "src/dictionary.txt";
  final String smallDictionary = "tests/Dictree/smallDictionary.txt";
  final int SIZEBIG = 354973;
  final int SIZESMALL = 21;

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
    String bigDictionary = "src/dictionary.txt";

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
    String fb1 = "wronglyment";
    String fb2 = "notaword";
    String fb3 = "aaaaaaaaa";
    String fb4 = "azza";
    String fb5 = "'thisisnot";
    String fb6 = "sh'ould";
    String fb7 = "should'";
    String fb8 = "should''";
    String fb9 = "it''s";

    // true
    String tb1 = "a";
    String tb2 = "parliament";
    String tb3 = "string";
    String tb4 = "spell";
    String tb5 = "checker";
    String tb6 = "don't";
    String tb7 = "dont";
    String tb8 = "cyprinodontoid";
    String tb9 = "cyprinodont";

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

    // big dictionary
    dictree = new RadixTree(new BufferedReader(new FileReader(bigDictionary)));

    assertFalse(dictree.check(fb1));
    assertFalse(dictree.check(fb2));
    assertFalse(dictree.check(fb3));
    assertFalse(dictree.check(fb4));
    assertFalse(dictree.check(fb4));
    assertFalse(dictree.check(fb5));
    assertFalse(dictree.check(fb6));
    assertFalse(dictree.check(fb7));
    assertFalse(dictree.check(fb8));
    assertFalse(dictree.check(fb9));

    assertTrue(dictree.check(tb1));
    assertTrue(dictree.check(tb2));
    assertTrue(dictree.check(tb3));
    assertTrue(dictree.check(tb4));
    assertTrue(dictree.check(tb5));
    assertTrue(dictree.check(tb6));
    assertTrue(dictree.check(tb7));
    assertTrue(dictree.check(tb8));
    assertTrue(dictree.check(tb9));
  }

  @Test
  public void ConcurrentRTTestLoad() {

    String smallDictionary = "tests/Dictree/smallDictionary.txt";
    String bigDictionary = "src/dictionary.txt";

    Dictree dictree = new ConcurrentRT();

    // timed tests
    long startTime = System.nanoTime();

    FileReader fileReader;
    try {
      fileReader = new FileReader(smallDictionary);
    } catch (FileNotFoundException e) {
      System.err.println("Concurrent fileReader error");
      return;
    }
    dictree.load(new BufferedReader(fileReader));
    assertEquals(SIZESMALL, dictree.size());

    long  endTime = System.nanoTime();

    long durationSmall = (endTime - startTime) / 1000000;

    try {
      fileReader = new FileReader(bigDictionary);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    startTime = System.nanoTime();
    dictree.load(new BufferedReader(fileReader));
    endTime = System.nanoTime();

    long durationBig = (endTime - startTime) / 1000000;

    assertEquals(SIZEBIG, dictree.size());

    System.out.println("Loading times\n" + "Small dictionary: " + durationSmall + "ms\n"
                       + "Big dictionary: " + durationBig + "ms");
  }

  @Test
  public void testConcurrentWord() {

    // false

    String f1 = "kj";
    String f2 = "aabb";
    String f3 = "tis";
    String f4 = "A'yti";
    String f5 = "'bythis";
    String f6 = "'hello";
    String f7 = "he'llo";
    String f8 = "Helo";
    String f9 = "tests'";

    // true

    String t1 = "true";
    String t2 = "a";
    String t3 = "hello";
    String t4 = "test";
    String t5 = "going";
    String t6 = "belongs";
    String t7 = "it's";
    String t8 = "dont";
    String t9 = "dont'";

    FileReader fileReader;
    try {
      fileReader = new FileReader(bigDictionary);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    } ;

    Dictree dictree = new ConcurrentRT(new BufferedReader(fileReader));
    assertFalse(dictree.check(f1));
    assertFalse(dictree.check(f2));
    assertFalse(dictree.check(f3));
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
