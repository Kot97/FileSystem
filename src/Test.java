import filesystem.*;

import java.nio.file.NoSuchFileException;
import java.util.zip.DataFormatException;

public class Test
{
  public static void main(String[] args)
  {
    try
    {
      Path p = new Path("/user/desktop");
      System.out.println(Util.lastDir(p).getName());
    }
    catch (DataFormatException | NoSuchFileException e) { e.printStackTrace(); }

  }
}
