package filesystem;

import java.util.function.Function;

public interface Traverseable
{
  /**
   * Apply function on file or, if its called from directory, all files in all subdirectories.
   * @param function function to apply
   */
  void recursiveTravel(Function function);
  Directory actual();
  Directory parent();
}
