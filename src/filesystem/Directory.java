package filesystem;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.Vector;
import java.util.zip.DataFormatException;

//TODO : make c++ map from directory

/**
 * Model of file system directory.
 */
public class Directory extends IDirectory implements Moveable, Renameable
{
  /**
   * Create new empty directory in given place.
   * @param path path to directory
   * @param name directory name
   * @throws DataFormatException when path is wrongly spelled
   * @throws NoSuchFileException when path is incorrect in this file system
   * @throws FileAlreadyExistsException when there is already directory with this name in directory
   */
  public Directory(Path path, String name) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException { super(path, name);}

  /**
   * Create new empty directory in given place.
   * @param path path to directory
   * @param name directory name
   * @throws DataFormatException when path is wrongly spelled
   * @throws NoSuchFileException when path is incorrect in this file system
   * @throws FileAlreadyExistsException when there is already directory with this name in directory
   */
  public Directory(String path, String name) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException { super(path, name);}

  /**
   * Create new directory in main directory
   * @param name directory name
   * @param dir directory content
   * @throws DataFormatException when path is wrongly spelled
   * @throws NoSuchFileException when path is incorrect in this file system
   * @throws FileAlreadyExistsException when there is already directory with this name in directory
   */
  public Directory(Path path, String name, Vector<IFile> dir) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException { super(path, name, dir);}

  /**
   * Create new directory in main directory
   * @param name directory name
   * @param dir directory content
   * @throws DataFormatException when path is wrongly spelled
   * @throws NoSuchFileException when path is incorrect in this file system
   * @throws FileAlreadyExistsException when there is already directory with this name in directory
   */
  public Directory(String path, String name, Vector<IFile> dir) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException { super(path, name, dir);}

  /**
   * Move directory to new location.
   * @param path path to new location
   * @throws DataFormatException when path is wrongly spelled
   * @throws NoSuchFileException when path is incorrect in this file system
   * @throws FileAlreadyExistsException when there is directory with this directory name in new location
   */
  @Override
  public void move(Path path) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  { new ProxyMove(this).move(path);}

  /**
   * Move directory to new location.
   * @param path path to new location
   * @throws DataFormatException when path is wrongly spelled
   * @throws NoSuchFileException when path is incorrect in this file system
   * @throws FileAlreadyExistsException when there is directory with this directory name in new location
   */
  @Override
  public void move(String path) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  { move(new Path(path));}

  /**
   * Change directory name.
   * @param name new directory name
   * @throws FileAlreadyExistsException when there is directory with given name in actual directory
   */
  @Override
  public void rename(String name) throws FileAlreadyExistsException
  {
    try { new ProxyRename(this).rename(name); }
    catch (DataFormatException | NoSuchFileException e) { }
  }

}
