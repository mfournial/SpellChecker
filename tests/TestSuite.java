import org.junit.Test;

import java.util.Optional;

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
}
