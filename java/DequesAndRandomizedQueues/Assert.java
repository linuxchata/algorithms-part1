
public class Assert {
  public static void IsTrue(boolean actual) {
    if (!actual) {
      System.out.println("Failed IsTrue assertion");
    }
  }
  
  public static void IsFalse(boolean actual) {
    if (actual) {
      System.out.println("Failed IsFalse assertion");
    }
  }
  
  public static void AreSame(int expected, int actual) {
    if (expected != actual) {
      System.out.println("Value " + actual + " is not equal to " + expected);
    }
  }
}
