import java.util.zip.DataFormatException;

public class Path
{
  private String content;

  static public void validate(String name) throws DataFormatException
  {
    if(!name.matches("^((?!.*//.*)(?!.*/ .*)/([^\\\\(){}:*?<>|\"'])*)"))
      throw new DataFormatException();
  }

  public Path(String content) throws DataFormatException
  {
    validate(content);
    this.content = content;
  }

  public String get() { return content; }
  public void set(String content) throws DataFormatException
  {
    validate(content);
    this.content = content;
  }
}
