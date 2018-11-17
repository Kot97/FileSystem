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
      Path p = new Path("/user/desktop");
      System.out.println(Util.lastDir(p).getName());
    }
    catch (DataFormatException | NoSuchFileException e) { e.printStackTrace(); }

  }
}
