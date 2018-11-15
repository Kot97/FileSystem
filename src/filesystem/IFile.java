package filesystem;
//TODO : javadoc for all

import java.nio.file.NoSuchFileException;
import java.util.function.Function;
import java.util.zip.DataFormatException;

/**
 * Abstract class for any kind of computer files and directories.
 */
//TODO : interface, ktore implementuje IFile
public abstract class IFile
{
  protected String name;
  protected Path path;

  public String getName() { return name; }
  public Path getPath() { return path; }

  /**
   * Create file/directory in given place.
   * @param path path to file/directory
   * @param name name of file/directory
   * @throws DataFormatException when path is wrongly spelled
   */
  IFile(Path path, String name) throws DataFormatException
  {
    this.path = new Path(path.get() + "/" + name);
    this.name = name;
  }

  /**
   * Create file/directory in given place.
   * @param path path to file/directory
   * @param name name of file/directory
   * @throws DataFormatException when path is wrongly spelled
   */
  IFile(String path, String name) throws DataFormatException
  {
    this.path = new Path(path + "/" + name);
    this.name = name;
  }

  /**
   * Change file/directory name.
   * @param name new file/directory name
   * @return true if renaming is successful, false if not
   * @throws DataFormatException when path is wrongly spelled
   */
  public Boolean rename(String name) throws DataFormatException, NoSuchFileException
  {
    //TODO
    return true;
  }

  /**
   * Move file/directory to new location.
   * @param path path to new location
   * @return true if moving is successful, false if not
   * @throws DataFormatException when path is wrongly spelled
   */
  public Boolean move(Path path) throws DataFormatException
  {
    //TODO
    return true;
  }

  /**
   * Move file/directory to new location.
   * @param path path to new location
   * @return true if moving is successful, false if not
   * @throws DataFormatException when path is wrongly spelled
   */
  public Boolean move(String path) throws DataFormatException
  {
    //TODO
    return true;
  }

  /**
   * Apply function on file or, if its called from directory, all files in all subdirectories.
   * @param function function to apply
   */
  abstract void recursiveTravel(Function function);

  /**
   * Function to calculate size of file/directory.
   * @return size of file/directory in bytes
   */
  abstract long size();

  abstract Directory actual();
  abstract Directory parent();
}
