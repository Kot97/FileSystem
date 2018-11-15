package filesystem;

import java.nio.file.NoSuchFileException;
import java.util.function.Function;
import java.util.zip.DataFormatException;

public class File extends IFile
{
  private String content;

  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }

  public File(Path path, String name) throws DataFormatException, NoSuchFileException
  {
    super(path, name);
    this.content = "";
    (Util.lastDir(path)).addItem(this);
  }

  public File(String path, String name) throws DataFormatException, NoSuchFileException
  {
    super(path, name);
    this.content = "";
    (Util.lastDir(path)).addItem(this);
  }

  public File(Path path, String name, String content) throws DataFormatException, NoSuchFileException
  {
    super(path, name);
    this.content = content;
    (Util.lastDir(path)).addItem(this);
  }

  public File(String path, String name, String content) throws DataFormatException, NoSuchFileException
  {
    super(path, name);
    this.content = content;
    (Util.lastDir(path)).addItem(this);
  }

  @Override
  public void recursiveTravel(Function function) { function.apply(this); }

  @Override
  public long size()
  {
    //TODO
    return 0;
  }

  @Override
  public Directory actual()
  {
    return null;
  }

  @Override
  public Directory parent()
  {
    return null;
  }
}
