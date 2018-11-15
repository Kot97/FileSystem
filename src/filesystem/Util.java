package filesystem;

import java.nio.file.NoSuchFileException;
import java.util.zip.DataFormatException;

public class Util
{
  static public Directory lastDir(Path path) throws NoSuchFileException, DataFormatException
  {
    String[] temp = path.get().split("//"); //PERR
    Directory prev = Root.getDirectory();
    for(String i : temp)    //PERR : pierwszy element, jak jest z Rootem w path
    {
      IFile temp2 = prev.getItem(i);
      if(! (temp2 instanceof Directory)) throw new DataFormatException(); //PERR : sprawdzanie instanceof w taki sposob
      prev = (Directory) temp2;
    }   //PERR : ostatni element
    return prev;
  }

  static public Directory lastDir(String path) throws NoSuchFileException, DataFormatException
  {
    Path.validate(path);
    String[] temp = path.split("//"); //PERR
    Directory prev = Root.getDirectory();
    for(String i : temp)    //PERR : pierwszy element, jak jest z Rootem w path
    {
      IFile temp2 = prev.getItem(i);
      if(! (temp2 instanceof Directory)) throw new DataFormatException(); //PERR : sprawdzanie instanceof w taki sposob
      prev = (Directory) temp2;
    }   //PERR : ostatni element
    return prev;
  }
}
