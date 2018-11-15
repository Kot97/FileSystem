import java.util.zip.DataFormatException;

public class Path
{
  private String content;

  private void validate(String name) throws DataFormatException
  {
    if(!name.matches("^((?!.*//.*)(?!.*/ .*)/([^\\\\(){}:*?<>|\"'])*)"))
      throw new DataFormatException();
  }

  public String get() { return content; }
  public void set(String content) throws DataFormatException
  {
    validate(content);
    this.content = content;
  }
}
