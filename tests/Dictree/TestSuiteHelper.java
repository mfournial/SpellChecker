package Dictree;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import Checker.Main;

/**
 * Created by mfournial on 22/04/2017.
 */
public class TestSuiteHelper {

  public static String runMain(String inputFile) {

    // OUT Collector
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream ps;
    System.setOut(ps = new PrintStream(output));

    Main.main(new String[] {inputFile});

    // returns main's output
    String result = output.toString();
    try {
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.setOut(System.out);
    ps.close();
    return result;
  }

  public static String readFile(String testFile) {
    StringBuilder stringBuilder = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        stringBuilder.append(line);
        stringBuilder.append('\n');
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return stringBuilder.toString();
  }
}
