public class MainDir {
  private static MainDir ourInstance = new MainDir();
  public static MainDir getInstance() {
    return ourInstance;
  }

  private static Dir d = new Dir("Main");
  public static Dir getDir() {return d;}
}
