import filesystem.*;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.zip.DataFormatException;

public class Test
{
  public static void main(String[] args)
  {
    try
    {
      Directory user = new Directory("/", "user");
      Directory desktop = new Directory("/user", "desktop");
      Directory documents = new Directory("/user/desktop", "documents");
      File f = new File("/user", "file", "test content");
      File f2 = new File("/user/desktop", "file2", "test content");
      File f3 = new File("/user/desktop/documents", "file3", "test content");

      System.out.println("Root content: ");
      Root.getInstance().ls();
      System.out.println(" ");

      System.out.println("User content: ");
      user.ls();
      System.out.println(" ");

      System.out.println("Desktop content: ");
      desktop.ls();
      System.out.println(" ");

      System.out.println("Documents content: ");
      documents.ls();
      System.out.println(" ");

    }
    catch (DataFormatException | NoSuchFileException | FileAlreadyExistsException e) { e.printStackTrace(); }
  }
}
