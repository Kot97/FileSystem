package filesystem;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.zip.DataFormatException;

public interface Renameable
{
  /**
   * Change file/directory name.
   * @param name new file/directory name
   * @return true if renaming is successful, false if not
   * @throws DataFormatException when path is wrongly spelled
   */
  void rename(String name) throws DataFormatException, FileAlreadyExistsException, NoSuchFileException;
}
