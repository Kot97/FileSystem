package filesystem;
//TODO : javadoc for all

import java.util.zip.DataFormatException;

/**
 * Abstract class for any kind of computer files.
 */
public abstract class IFile
{
  protected String name;
  protected Path path;

  public String getName() { return name; }
  public Path getPath() { return path; }

  /**
   * Create file in given place.
   * @param path path to file
   * @param name name of file
   * @throws DataFormatException when path is wrongly spelled
   */
  IFile(Path path, String name) throws DataFormatException
  {
    this.path = new Path(path.get() + "/" + name);
    this.name = name;
    //TODO : dodaj w odpowiednie miejsce
  }

  /**
   * Create file in given place.
   * @param path path to file
   * @param name name of file
   * @throws DataFormatException when path is wrongly spelled
   */
  IFile(String path, String name) throws DataFormatException
  {
    this.path = new Path(path + "/" + name);
    this.name = name;
    //TODO : dodaj w odpowiednie miejsce
  }

  /**
   * Function to calculate size of file.
   * @return size of file in bytes
   */
  abstract long size();
}
