import org.junit.Test;

import static Dictree.TestSuiteHelper.readFile;
import static Dictree.TestSuiteHelper.runMain;
import static org.junit.Assert.assertEquals;


/**
 * Created by mfournial on 22/04/2017.
 * Test suite for Dictree
 */
public class TestSuite {
  @Test
  public void testMain() {

    // test files
    String exampleTest = "tests/Dictree/ExampleTest.txt";
    String analysisTest = "tests/Dictree/AnalysisTest.txt";
    String hamletTest = "tests/Dictree/HamletTest.txt";

    // test dictionary
    String smallDictionary = "tests/Dictree/smallDictionary.txt";


    // easy tests
    assertEquals(readFile(exampleTest), runMain("example.txt"));
    assertEquals(readFile(analysisTest), runMain("Analysis.txt"));
    assertEquals(readFile(hamletTest), runMain("Hamlet.txt"));
  }
}
