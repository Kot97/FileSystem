package filesystem;

import java.nio.file.NoSuchFileException;
import java.util.function.Consumer;
import java.util.zip.DataFormatException;

public interface Traverseable
{
  //TODO : zrob, zeby dobrze dzialalo
  /**
   * Apply function on file or, if its called from directory, all files in all subdirectories.
   * @param function function to apply
   */
  void recursiveTravel(Consumer<IFile> function);
  IDirectory actual() throws DataFormatException, NoSuchFileException;
  IDirectory parent() throws DataFormatException, NoSuchFileException;
}
