package filesystem;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.zip.DataFormatException;

public class Path implements Iterable<String>
{
  private String content;

  public String get() { return content; }

  /**
   * Check if name is correct path name.
   * @param name path name to check
   * @throws DataFormatException when path is wrongly spelled
   */
  static public void validate(String name) throws DataFormatException
  {
    if(!name.matches("^((?!.*//.*)(?!.*/ .*)/([^\\\\(){}:*?<>|\"'])*)"))
      throw new DataFormatException();
  }

  /**
   * Create filesystem.Path from String.
   * @param name path name
   * @throws DataFormatException when path is wrongly spelled
   */
  public Path(String name) throws DataFormatException
  {
    validate(name);
    this.content = name;
  }

  //TODO : zrob Path iterable

  @Override
  public Iterator<String> iterator()
  {
    return null;
  }

  @Override
  public void forEach(Consumer<? super String> action)
  {

  }

  @Override
  public Spliterator<String> spliterator()
  {
    return null;
  }
}
