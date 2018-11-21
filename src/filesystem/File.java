package filesystem;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.function.Consumer;
import java.util.zip.DataFormatException;

/**
 * Model of file in file system.
 */
public class File extends IFile implements Moveable, Renameable
{
  private String content;

  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }

  /**
   * Create new empty file in given place.
   * @param path path to file
   * @param name file name
   * @throws DataFormatException when path is wrongly spelled
   * @throws NoSuchFileException when path is incorrect in this file system
   * @throws FileAlreadyExistsException when there is already file with this name in directory
   */
  public File(Path path, String name) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  {
    super(path, name);
    this.content = "";
  }

  /**
   * Create new empty file in given place.
   * @param path path to file
   * @param name file name
   * @throws DataFormatException when path is wrongly spelled
   * @throws NoSuchFileException when path is incorrect in this file system
   * @throws FileAlreadyExistsException when there is already file with this name in directory
   */
  public File(String path, String name) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  {
    super(path, name);
    this.content = "";
  }

  /**
   * Create new file in given place.
   * @param path path to file
   * @param name file name
   * @param content file content
   * @throws DataFormatException when path is wrongly spelled
   * @throws NoSuchFileException when path is incorrect in this file system
   * @throws FileAlreadyExistsException when there is already file with this name in directory
   */
  public File(Path path, String name, String content) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  {
    super(path, name);
    this.content = content;
  }

  /**
   * Create new file in given place.
   * @param path path to file
   * @param name file name
   * @param content file content
   * @throws DataFormatException when path is wrongly spelled
   * @throws NoSuchFileException when path is incorrect in this file system
   * @throws FileAlreadyExistsException when there is already file with this name in directory
   */
  public File(String path, String name, String content) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  {
    super(path, name);
    this.content = content;
  }

  @Override
  public void recursiveTravel(Consumer<IFile> function) { function.accept(this); }

  //TODO : size

  @Override
  public long size()
  {
    //TODO
    return 0;
  }

  @Override
  public void move(Path path) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  {
    new ProxyMove(this).move(path);
  }

  @Override
  public void move(String path) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  {
    move(new Path(path));
  }

  @Override
  public void rename(String name) throws DataFormatException, FileAlreadyExistsException, NoSuchFileException
  {
    new ProxyRename(this).rename(name);
  }
}
