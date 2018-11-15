package filesystem;

import java.nio.file.NoSuchFileException;
import java.util.zip.DataFormatException;

public class Root
{
  private static Root ourInstance = new Root();
  public static Root getInstance() {
    return ourInstance;
  }

  private static Directory d;

  static
  {
    try  { d = new Directory("", ""); }
    catch (DataFormatException | NoSuchFileException e) { e.printStackTrace(); }
  }

  public static Directory getDirectory() { return d; }
}
