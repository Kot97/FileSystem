package filesystem;

import java.nio.file.NoSuchFileException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.zip.DataFormatException;

public class IDirectory extends IFile implements Iterable<IFile>
{
  private Vector<IFile> dir;

  /**
   * Create empty directory in given place.
   * @param path path to directory
   * @param name name of directory
   * @throws DataFormatException when path is wrongly spelled
   */
  IDirectory(Path path, String name) throws DataFormatException, NoSuchFileException { super(path, name); }

  /**
   * Create empty directory in given place.
   * @param path path to directory
   * @param name name of directory
   * @throws DataFormatException when path is wrongly spelled
   */
  IDirectory(String path, String name) throws DataFormatException, NoSuchFileException { super(path, name); }

  /**
   * Create directory in given place.
   * @param path path to directory
   * @param name name of directory
   * @param dir content of directory
   * @throws DataFormatException when path is wrongly spelled
   */
  IDirectory(Path path, String name, Vector<IFile> dir) throws DataFormatException, NoSuchFileException { super(path, name); this.dir = dir; }

  /**
   * Create directory in given place.
   * @param path path to directory
   * @param name name of directory
   * @param dir content of directory
   * @throws DataFormatException when path is wrongly spelled
   */
  IDirectory(String path, String name, Vector<IFile> dir) throws DataFormatException, NoSuchFileException { super(path, name); this.dir = dir;}

  /**
   * Search for file or directory in this directory.
   * @param name name of file or directory which we search
   * @return searched file or directory
   * @throws NoSuchFileException when there is no such file in this directory
   */
  public IFile getItem(String name) throws NoSuchFileException
  {
    for(IFile i : dir) if(i.getName().equals("name")) return i;
    throw new NoSuchFileException(name);
  }

  /**
   * Add item to directory.
   * @param file file which we add
   */
  public void addItem(IFile file) { dir.add(file); }

  /**
   * Delete item from directory.
   * @param name name of file or directory which we want to delete
   * @throws NoSuchFileException when there is no such file in this directory
   */
  public void deleteItem(String name) throws NoSuchFileException { dir.remove(getItem(name)); }

  /**
   * List all items in directory.
   */
  public void ls() { for (IFile i : dir) System.out.println(i.getName()); }

  /**
   * List all items in all directory start from this directory.
   */
  public void ls_r()
  {
    for (IFile i : dir)
    {
      if(i instanceof IDirectory) ((IDirectory) i).ls_r();
      else System.out.println(i.getName());
    }
  }

  /**
   * Search for file or directory in this directory and all subdirectories.
   * @param name name of file or directory which we search
   * @return searched file or directory
   * @throws NoSuchFileException when there is no such file in this directory
   */
  public IFile search(String name) throws NoSuchFileException
  {
    for(IFile i : dir)
    {
      if(i instanceof IDirectory)
      {
        try {((IDirectory) i).search(name); } catch (NoSuchFileException e) {}   //PERR
      }
      if(i.getName().equals("name")) return i;
    }
    throw new NoSuchFileException(name);
  }

  /**
   * Apply function on file or, if its called from directory, all files in all subdirectories.
   * @param function function to apply
   */
  public void recursiveTravel(Function function) { for(IFile i : dir) i.recursiveTravel(function); }

  /**
   * Function to calculate size of directory.
   * @return size of all files in directory and subdirectories in bytes
   */
  @Override
  long size()
  {
    long ret = 0;
    for(IFile i : dir) ret += i.size();
    return ret;
  }

  @Override public Iterator<IFile> iterator() { return dir.iterator(); }
  @Override public void forEach(Consumer<? super IFile> action) { for(IFile i : dir) action.accept(i); }
  @Override public Spliterator<IFile> spliterator() { return dir.spliterator(); }
}
