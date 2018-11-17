package filesystem;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.Vector;
import java.util.zip.DataFormatException;

//TODO : make c++ map from directory
public class Directory extends IDirectory implements Moveable, Renameable
{
  /**
   * Create new empty directory in given place.
   * @param path path to directory
   * @param name directory name
   * @throws DataFormatException when path is wrongly spelled
   */
  public Directory(Path path, String name) throws DataFormatException, NoSuchFileException
  {
    super(path, name);
  }

  /**
   * Create new empty directory in given place.
   * @param path path to directory
   * @param name directory name
   * @throws DataFormatException when path is wrongly spelled
   */
  public Directory(String path, String name) throws DataFormatException, NoSuchFileException
  {
    super(path, name);
  }

  /**
   * Create new directory in main directory
   * @param name directory name
   * @param dir directory content
   * @throws DataFormatException when path is wrongly spelled
   */
  public Directory(Path path, String name, Vector<IFile> dir) throws DataFormatException, NoSuchFileException
  {
    super(path, name, dir);
  }

  /**
   * Create new directory in main directory
   * @param name directory name
   * @param dir directory content
   * @throws DataFormatException when path is wrongly spelled
   */
  public Directory(String path, String name, Vector<IFile> dir) throws DataFormatException, NoSuchFileException
  {
    super(path, name, dir);
  }

  @Override
  public Boolean move(Path path) throws DataFormatException
  {
    return null;
  }

  @Override
  public Boolean move(String path) throws DataFormatException
  {
    return null;
  }

  @Override
  public Boolean rename(String name) throws DataFormatException, FileAlreadyExistsException
  {
    return null;
  }
}
