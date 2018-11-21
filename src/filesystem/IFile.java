package filesystem;
//TODO : javadoc for all

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.function.Function;
import java.util.zip.DataFormatException;

/**
 * Abstract class for any kind of computer files.
 */
public abstract class IFile implements Traverseable
{
  protected String name;
  protected Path path;

  public String getName() { return name; }
  public Path getPath() { return path; }

  /**
   * Function to calculate size of file.
   * @return size of file in bytes
   */
  abstract long size();

  /**
   * Create Root directory.
   * @throws DataFormatException never
   */
  protected IFile()
  {
    this.name = "/";
    try { this.path = new Path("/");}
    catch (DataFormatException e) {}
  }

  /**
   * Create file in given place.
   * @param path path to file
   * @param name name of file
   * @throws DataFormatException when path is wrongly spelled
   */
  IFile(Path path, String name) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  {
    if(path.get() == "/") this.path = new Path( "/" + name);
    else this.path = new Path(path.get() + "/" + name);

    this.path = new Path(path.get() + "/" + name);
    this.name = name;
    Util.lastDir(path).addItem(this);
  }

  /**
   * Create file in given place.
   * @param path path to file
   * @param name name of file
   * @throws DataFormatException when path is wrongly spelled
   */
  IFile(String path, String name) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  {
    if(path == "/") this.path = new Path( "/" + name);
    else this.path = new Path(path + "/" + name);

    this.name = name;
    Util.lastDir(path).addItem(this);
  }

  /**
   * Apply function on file or, if its called from directory, all files in all subdirectories.
   * @param function function to apply
   */
  public void recursiveTravel(Function function) { function.apply(this); }

  /**
   * Actual directory.
   * @return Directory in which there is this file
   * @throws DataFormatException when called from Root
   */
  public IDirectory actual() throws DataFormatException
  {
    String path = this.path.get();
    Path temp = new Path(path.substring(0, path.length() - this.name.length()));  //PERR
    try { return Util.lastDir(temp); }
    catch (NoSuchFileException e) { e.printStackTrace(); return null;}
  }

  /**
   * Parent directory.
   * @return Directory in which there is actual directory
   * @throws DataFormatException when called from Root and Root subdirectories
   */
  public IDirectory parent() throws DataFormatException
  {
    IDirectory temp = actual();
    return temp.actual();
  }

  //TODO : copy
//  public Boolean copy(Path path)
//  {
//
//  }
//
//  public Boolean copy(String path)
//  {
//
//  }
}
