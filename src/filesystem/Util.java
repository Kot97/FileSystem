package filesystem;

import javafx.util.Pair;

import java.nio.file.NoSuchFileException;
import java.util.zip.DataFormatException;

public class Util
{
  /**
   * Split path to name and table of all directories from Root to file/directory.
   * @param path path to split
   * @return Pair, in which key is file/directory name, value is table of string which contains path to file/directory
   */
  static public Pair<String, String[]> split(String path)
  {
    String[] temp = path.split("/");
    if(temp.length == 0) return new Pair<>(null, null);
    if(temp.length == 1) return new Pair<>("/", null);
    else if(temp.length == 2) return new Pair<>(temp[1], new String[]{temp[1]});
    String[] ret = new String[temp.length - 1];
    for(int i = 1; i < temp.length; i++) ret[i-1] = temp[i];
    return new Pair<>(temp[temp.length-1], ret);
  }

  /**
   * Split path to name and table of all directories from Root to file/directory.
   * @param path path to split
   * @return Pair, in which key is file/directory name, value is table of string which contains path to file/directory
   */
  static public Pair<String, String[]> split(Path path) { return split(path.get()); }

  /**
   * Create path from split return value.
   * @param path path in split return value type
   * @return path as filesystem.Path
   * @throws DataFormatException when there is some bad name for directory in Pair.value
   */
  static public Path unsplit(Pair<String, String[]> path) throws DataFormatException
  {
    String res = "/";
    for(String i : path.getValue()) res += (i + "/");
    res+=path.getKey();
    return new Path(res);
  }

  /**
   * Give reference to last directory in given path.
   * @param path path to work with
   * @return reference to last directory in path
   * @throws NoSuchFileException when path is incorrect in actual file system
   * @throws DataFormatException when there is file on directory place in path
   */
  static public IDirectory lastDir(Path path) throws NoSuchFileException, DataFormatException
  {
    IDirectory prev = Root.getInstance();
    String[] temp = split(path).getValue();
    if(temp == null) return prev;

    for(String i : temp)
    {
      IFile temp2 = prev.getItem(i);
      if(! (temp2 instanceof Directory)) throw new DataFormatException(); //PERR : sprawdzanie instanceof w taki sposob
      prev = (Directory) temp2;
    }
    return prev;
  }

  /**
   * Give reference to last directory in given path.
   * @param path path to work with
   * @return reference to last directory in path
   * @throws NoSuchFileException when path is incorrect in actual file system
   * @throws DataFormatException when path is wrongly spelled or there is file on directory place in path
   */
  static public IDirectory lastDir(String path) throws NoSuchFileException, DataFormatException
  {
    return lastDir(new Path(path));
  }

  /**
   * Count number of file with given name in given directory and all subdirectories.
   * @param directory start directory
   * @param name name of file
   * @return number of files with given name in directory and all subdirectories
   */
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

  /**
   * Count number of file with given name in given directory and all subdirectories.
   * @param directory start directory
   * @param file Ifile to search
   * @return number of files with given name in directory and all subdirectories
   */
  static public int count(IDirectory directory, IFile file)
  {
    //TODO : najpierw dodaj mozliwosc porownywania roznych IFile
    return 0;
  }

  /**
   * Count number of file with given name in all directories in file system.
   * @param name name of file
   * @return number of files with given name in file system
   */
  static public int count(String name)
  {
    return count(Root.getInstance(), name);
  }

  //TODO :
//  static public int count(IFile file)
//  {
//    return count(file.getName());
//  }

  /**
   * Check if given path is valid in file system.
   * @param path path to check
   * @return true if path is valid, false if not
   */
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

  /**
   * Search for file in file system.
   * @param name file name
   * @return reference to first find file with given name
   * @throws NoSuchFileException when there is no file with given name in file system
   */
  public IFile search(String name) throws NoSuchFileException { return Root.getInstance().search(name);}

  //TODO : flush

  static public void flush(Path path)
  {

  }
  static public void flush(String path)
  {

  }
}
