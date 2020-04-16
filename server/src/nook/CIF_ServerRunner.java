package nook;

public final class CIF_ServerRunner {
  public static void main(String[] args) {
    CIF_Server servant = new CIF_Server(0xdeaf);
    new Thread(servant).start();
  }
}
