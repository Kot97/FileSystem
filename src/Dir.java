import java.nio.file.NoSuchFileException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Vector;
import java.util.function.Consumer;

public class Dir extends IFile implements Iterable<IFile>
{
  private Vector<IFile> set;

  /**
   * Create new empty directory in main directory
   * @param name directory name
   */
  public Dir(String name)
  {
    super(name);
    this.set = new Vector<>();
    MainDir.getDir().addItem(this);
  }

  /**
   * Create new empty directory in dirName directory
   * @param name directory name
   * @param dirName directory in which we want to create new directory
   * @throws NoSuchFileException when dirName don't exist this exception is thrown
   */
  public Dir(String name, Dir dirName) throws NoSuchFileException
  {
    super(name);
    this.set = new Vector<>();
    Dir d = MainDir.getDir().search(dirName);
    if(d == null) throw new NoSuchFileException(dirName.getName());
    d.addItem(this);
  }

  /**
   * Create new empty directory in dirName directory
   * @param name directory name
   * @param dirName directory in which we want to create new directory
   * @throws NoSuchFileException when dirName don't exist this exception is thrown
   */
  public Dir(String name, String dirName) throws NoSuchFileException
  {
    super(name);
    this.set = new Vector<>();
    Dir d = MainDir.getDir().search(new Dir(dirName));
    if(d == null) throw new NoSuchFileException(dirName);
    d.addItem(this);
  }

  /**
   * Create new directory in main directory
   * @param name directory name
   * @param set directory content
   */
  public Dir(String name, Vector<IFile> set)
  {
    super(name);
    this.set = set;
    MainDir.getDir().addItem(this);
  }

  /**
   * Create new directory in dirName directory
   * @param name directory name
   * @param set directory content
   * @param dirName directory in which we want to create new directory
   * @throws NoSuchFileException when dirName don't exist this exception is thrown
   */
  public Dir(String name, Vector<IFile> set, Dir dirName) throws NoSuchFileException
  {
    super(name);
    this.set = set;
    Dir d = MainDir.getDir().search(dirName);
    if(d == null) throw new NoSuchFileException(dirName.getName());
    d.addItem(this);
  }


  /**
   * Create new directory in dirName directory
   * @param name directory name
   * @param set directory content
   * @param dirName directory in which we want to create new directory
   * @throws NoSuchFileException when dirName don't exist this exception is thrown
   */
  public Dir(String name, Vector<IFile> set, String dirName) throws NoSuchFileException
  {
    super(name);
    this.set = set;
    Dir d = MainDir.getDir().search(new Dir(dirName));
    if(d == null) throw new NoSuchFileException(dirName);
    d.addItem(this);
  }

  /**
   * Search for file or directory in all directories started from this directory
   * @param name name of file or directory which we search
   * @return file or directory or null when there is no such file or directory
   */
  public IFile getItem(String name)
  {
    for(IFile i : set) if(i.getName().equals("name")) return i;
    return null;
  }

  /**
   * Search for directory in which there is given file or directory
   * @param file file or directory for which we search directory
   * @return directory or null when there is no such file or directory
   */
  //PERR
  public Dir search(IFile file)
  {
    if(file instanceof Dir)
    {
      for(IFile i : set)
      {
        if(i instanceof Dir)
        {
          if(i == file) return ((Dir) i);
          Dir a = ((Dir) i).search(file);
          if (a != null) return a;
        }
      }
    }
    else
    {
      for(IFile i : set)
      {
        if(i instanceof Dir)
        {
          Dir a = ((Dir) i).search(file);
          if (a != null) return a;
        }
        if(i == file) return this;
      }
    }
    return null;
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
      if(i instanceof Dir) ((Dir) i).ls_r();
      else System.out.println(i.getName());
    }
  }

  @Override public Iterator<IFile> iterator() { return set.iterator(); }
  @Override public void forEach(Consumer<? super IFile> action) { for(IFile i : set) action.accept(i); }
  @Override public Spliterator<IFile> spliterator() { return set.spliterator(); }
}
