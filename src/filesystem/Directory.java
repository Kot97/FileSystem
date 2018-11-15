package filesystem;

import java.nio.file.NoSuchFileException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.zip.DataFormatException;

//TODO : make c++ map from directory
public class Directory extends IFile implements Iterable<IFile>
{
  private Vector<IFile> set;

  /**
   * Create new empty directory in given place.
   * @param path path to directory
   * @param name directory name
   * @throws DataFormatException when path is wrongly spelled
   */
  public Directory(Path path, String name) throws DataFormatException, NoSuchFileException
  {
    super(path, name);
    this.set = new Vector<>();
    String[] temp = path.get().split("//"); //PERR
    Directory prev = Root.getDirectory();
    for(String i : temp)    //PERR : pierwszy element, jak jest z Rootem w path
    {
      IFile temp2 = prev.getItem(i);
      if(! (temp2 instanceof Directory)) throw new DataFormatException(); //PERR : sprawdzanie instanceof w taki sposob
      prev = (Directory) temp2;
    }   //PERR : ostatni element
    prev.addItem(this);
  }

  /**
   * Create new empty directory in given place.
   * @param path path to directory
   * @param name directory name
   * @throws DataFormatException when path is wrongly spelled
   */
  public Directory(String path, String name) throws DataFormatException, NoSuchFileException
  {
    super(path, name);
    this.set = new Vector<>();
    (Util.lastDir(path)).addItem(this);
  }

  /**
   * Create new directory in main directory
   * @param name directory name
   * @param set directory content
   * @throws DataFormatException when path is wrongly spelled
   */
  public Directory(Path path, String name, Vector<IFile> set) throws DataFormatException, NoSuchFileException
  {
    super(path, name);
    this.set = set;
    (Util.lastDir(path)).addItem(this);
  }

  /**
   * Create new directory in main directory
   * @param name directory name
   * @param set directory content
   * @throws DataFormatException when path is wrongly spelled
   */
  public Directory(String path, String name, Vector<IFile> set) throws DataFormatException, NoSuchFileException
  {
    super(path, name);
    this.set = set;
    (Util.lastDir(path)).addItem(this);
  }

  /**
   * Search for file or directory in all directories started from this directory
   * @param name name of file or directory which we search
   * @return file or directory or null when there is no such file or directory
   */
  public IFile getItem(String name) throws NoSuchFileException
  {
    for(IFile i : set) if(i.getName().equals("name")) return i;
    throw new NoSuchFileException(name);
  }

  /**
   * Add item to directory
   * @param file file which we add
   */
  public void addItem(IFile file) { set.add(file); }

  /**
   * List all items in directory
   */
  public void ls() { for (IFile i : set) System.out.println(i.getName()); }

  /**
   * List all items in all directory start from this
   */
  public void ls_r()
  {
    for (IFile i : set)
    {
      if(i instanceof Directory) ((Directory) i).ls_r();
      else System.out.println(i.getName());
    }
  }

  @Override
  public void recursiveTravel(Function function)
  {
    for(IFile i : set) i.recursiveTravel(function);
  }

  @Override
  public long size()
  {
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

  @Override public Iterator<IFile> iterator() { return set.iterator(); }
  @Override public void forEach(Consumer<? super IFile> action) { for(IFile i : set) action.accept(i); }
  @Override public Spliterator<IFile> spliterator() { return set.spliterator(); }
}
