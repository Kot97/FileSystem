public class ProxyFile extends IFile
{
  private IFile file;

  public ProxyFile(IFile file) { super(file.path, file.name); }

  public Boolean rename(String new_name)
  {
    Dir d = MainDir.getDir().search(this);
    if(d.getItem(new_name) != null) return false;
    file.name = new_name;
    return true;
  }

  public Boolean move(String new_path)
  {
    //TODO
    return true;
  }

  public Boolean move(Path new_path)
  {
    //TODO
    return true;
  }
}
