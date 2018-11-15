public class ProxyFile extends IFile
{
  private IFile file;

  public ProxyFile(IFile file) { super(file.name); }

  public Boolean rename(String new_name)
  {
    Dir d = MainDir.getDir().search(this);
    if(d.getItem(new_name) != null) return false;
    file.name = new_name;
    return true;
  }
}
