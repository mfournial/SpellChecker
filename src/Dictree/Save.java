package Dictree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by mmf115 on 20/05/17.
 * dev class to monitor serialization
 */
public class Save {
  public static void main(String[] args) {

//    FileReader filereader;
//
//    try {
//      filereader = new FileReader("src/dictionary.txt");
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//      return;
//    }
//
//    Dictree tree = new ConcurrentRT(new BufferedReader(filereader));
//
//    String codebase = "src/Dictree/ConcurrentRT.java";
//
//    try {
//      FileOutputStream fileOut = new FileOutputStream("out/ConcurrentRT.ser");
//      ObjectOutputStream stream = new ObjectOutputStream(fileOut);
//      stream.writeObject(tree);
//      stream.close();
//      fileOut.close();
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    long begining = System.currentTimeMillis();
    long end = 0;
    try {

      FileInputStream fileIn = new FileInputStream("out/ConcurrentRT.ser");
      ObjectInputStream streamIn = new ObjectInputStream(fileIn);
      ConcurrentRT dictree = (ConcurrentRT) streamIn.readObject();
      streamIn.close();
      fileIn.close();
      end = System.currentTimeMillis();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    } catch (IOException e) {
      e.printStackTrace();
      return;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    long difference = end -begining;
    System.out.println("time to load: " + difference + "!!");

  }
}
