package filesystem;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.zip.DataFormatException;

public interface Moveable
{
  /**
   * Move file/directory to new location.
   * @param path path to new location
   * @return true if moving is successful, false if not
   * @throws DataFormatException when path is wrongly spelled
   */
  void move(Path path) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException;

  /**
   * Move file/directory to new location.
   * @param path path to new location
   * @return true if moving is successful, false if not
   * @throws DataFormatException when path is wrongly spelled
   */
  void move(String path) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException;
}
