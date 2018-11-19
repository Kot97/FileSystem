package filesystem;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.zip.DataFormatException;

public class ProxyMove implements Moveable
{
  private IFile file;

  public ProxyMove(IFile file) {this.file = file;}

  @Override
  public void move(Path path) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  {
    IDirectory d = Util.lastDir(path);
    if(d.getItem(file.getName()) != null) throw new FileAlreadyExistsException(file.getName());
    IDirectory temp = file.actual();
    d.addItem(file);
    temp.deleteItem(file.getName());
  }

  @Override
  public void move(String path) throws DataFormatException, NoSuchFileException, FileAlreadyExistsException
  {
    move(new Path(path));
  }
}
