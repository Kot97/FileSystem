package filesystem;

import javafx.util.Pair;

import java.nio.file.NoSuchFileException;
import java.util.zip.DataFormatException;

public class Util
{
  static public Pair<String, String[]> split(String path) //ERR : tu jest błąd,
  {
    String[] temp = path.split("/");
    if(temp.length == 0) return new Pair<>(null, null);
    if(temp.length == 1) return new Pair<>("/", null);
    else if(temp.length == 2) return new Pair<>(temp[1], new String[]{temp[1]});
    String[] ret = new String[temp.length - 1];
    for(int i = 1; i < temp.length; i++) ret[i-1] = temp[i];
    return new Pair<>(temp[temp.length-1], ret);
  }
  static public Pair<String, String[]> split(Path path) { return split(path.get()); }

  static public Path unsplit(Pair<String, String[]> path) throws DataFormatException //PERR
  {
    String res = "/";
    for(String i : path.getValue()) res += (i + "/");
    res+=path.getKey();
    return new Path(res);
  }

  static public IDirectory lastDir(String path) throws NoSuchFileException, DataFormatException
  {
    Path.validate(path);

    IDirectory prev = Root.getInstance();
    String[] temp = split(path).getValue();
    if(temp == null) return prev;

    for(String i : temp)    //PERR : pierwszy element, jak jest z Rootem w path
    {
      IFile temp2 = prev.getItem(i);
      if(! (temp2 instanceof Directory)) throw new DataFormatException(); //PERR : sprawdzanie instanceof w taki sposob
      prev = (Directory) temp2;
    }   //PERR : ostatni element
    return prev;
  }

  static public IDirectory lastDir(Path path) throws NoSuchFileException, DataFormatException
  {
    return lastDir(path.get());
  }

  static public int count(IDirectory directory, String name)
  {
    int ret = 0;
    for(IFile i : directory)
    {
      if(i.getName() == name) ret++;
      if(i instanceof IDirectory) ret += count((IDirectory) i, name);
    }
    return ret;
  }

  static public int count(IDirectory directory, IFile file)
  {
    return count(directory, file.getName());
  }

  static public int count(String name)
  {
    return count(Root.getInstance(), name);
  }

  static public int count(IFile file)
  {
    return count(file.getName());
  }

  public Boolean exist(Path path)
  {
    IFile prev = Root.getInstance();
    IFile i;

    Pair<String, String[]> _temp = split(path);
    String[] temp = _temp.getValue();
    String temp2 = _temp.getKey();
    if(temp == null) if(temp2 == "/") return true;
    else
    {
      try { ((IDirectory)prev).getItem(temp2); }
      catch (NoSuchFileException e) { return false; }
      return true;
    }
    for(String s : temp)
    {
      try { i = ((IDirectory)prev).getItem(s); }
      catch (NoSuchFileException e) { return false; }
      if(! (i instanceof IDirectory)) return false;
      prev = i;
    }
    try { ((IDirectory)prev).getItem(split(path).getKey()); }
    catch (NoSuchFileException e) { return false; }

    return true;
  }

  //TODO : flush

  static public void flush(Path path)
  {

  }
  static public void flush(String path)
  {

  }
}
