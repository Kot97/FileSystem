//TODO : javadoc for all

public abstract class IFile
{
  protected String name;

  public IFile(String name) { this.name = name; }

  public String getName() { return name; }
  public Boolean rename(String new_name)
  {
    ProxyFile proxy = new ProxyFile(this);
    return proxy.rename(new_name);
  }
}
