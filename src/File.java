public class File extends IFile
{
  private String content;

  public File(String name)
  {
    super(name);
    this.content = "";
  }

  public File(String name, String content)
  {
    super(name);
    this.content = content;
  }

  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }
}
