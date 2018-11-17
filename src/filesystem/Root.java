package filesystem;

import java.util.zip.DataFormatException;

public class Root extends IDirectory
{
  private static Root ourInstance;

  static
  {
    try { ourInstance = new Root(); }
    catch (DataFormatException e) { e.printStackTrace(); }
  }

  public static Root getInstance() {
    return ourInstance;
  }

  private Root() throws DataFormatException { super(); }

}
