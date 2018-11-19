package filesystem;

import javafx.util.Pair;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.zip.DataFormatException;

public class ProxyRename implements Renameable
{
  private IFile file;

  public ProxyRename(IFile file) {this.file = file;}

  @Override
  public void rename(String name) throws DataFormatException, FileAlreadyExistsException, NoSuchFileException
  {
    IDirectory d = file.actual();
    if(d.getItem(name) != null) throw new FileAlreadyExistsException(name);
    file.name = name;
    file.path = Util.unsplit(new Pair<>(name, Util.split(file.getPath()).getValue()));
  }
}
