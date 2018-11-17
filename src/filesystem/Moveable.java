package filesystem;

import java.util.zip.DataFormatException;

public interface Moveable
{
  /**
   * Move file/directory to new location.
   * @param path path to new location
   * @return true if moving is successful, false if not
   * @throws DataFormatException when path is wrongly spelled
   */
  Boolean move(Path path) throws DataFormatException;

  /**
   * Move file/directory to new location.
   * @param path path to new location
   * @return true if moving is successful, false if not
   * @throws DataFormatException when path is wrongly spelled
   */
  Boolean move(String path) throws DataFormatException;
}
