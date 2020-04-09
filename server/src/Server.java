import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {
  private final byte[] ip;
  private final int port;
  public Server(byte[] ip, int port) {
    this.ip = ip;
    this.port = port;
  }
  public byte[] getIP() { return ip; }
  public int getPort() { return port;}
  public InetAddress getAddress() throws UnknownHostException { return InetAddress.getByAddress(getIP());}
}
