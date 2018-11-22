package filesystem;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.function.Consumer;
import java.util.zip.DataFormatException;

/**
 * Model of link in file system.
 */
public class Link extends IFile
{
  Path content;

  /**
   * Create link.
   * @param path path where link will be placed
   * @param content path to linked file/directory
   * @throws DataFormatException
   * @throws NoSuchFileException
   * @throws FileAlreadyExistsException
   */
  public Link(Path path, Path content) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  {
    super(path, Util.split(content).getKey() + ".link");
    this.content = content;
  }

  /**
   * Create link.
   * @param path path where link will be placed
   * @param content path to linked file/directory
   * @throws DataFormatException
   * @throws NoSuchFileException
   * @throws FileAlreadyExistsException
   */
  public Link(String path, String content) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  {
    super(path, Util.split(content).getKey() + ".link");
    this.content = new Path(content);
  }

  public Path get() { return content;}

  public IFile use() throws NoSuchFileException, DataFormatException
  {
    return Util.lastDir(content).getItem(Util.split(content).getKey());
  }

  @Override
  long size()
  {
    return 0;
  }

  @Override
  public void recursiveTravel(Consumer<IFile> function)
  {

  }
}
