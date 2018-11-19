import filesystem.*;

import java.nio.file.NoSuchFileException;
import java.util.zip.DataFormatException;

public class Test
{
  public static void main(String[] args)
  {
    try
    {
      Directory d1 = new Directory("/", "user");
      Directory d2 = new Directory("/user", "desktop");
      File f = new File("/user", "file", "test content");
      File f2 = new File("/user", "file2", "test content");
      File f3 = new File("/user/desktop", "file3", "test content");

      //Root.getInstance().recursiveTravel((Object file) -> { System.out.println("Hello World!"); return null; } ); //ERR
    }
    catch (DataFormatException | NoSuchFileException e) { e.printStackTrace(); }
  }
}
