package receiver;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public final class Receptionist {
  public static SocketChannel listen(ServerSocketChannel listener) throws IOException {
    SocketChannel connectedClient = listener.accept(); // TODO: после резкого отключения клиента connectedClient принимает значение null
    //добавил чтобы избежать написание второго try
    if (connectedClient ==null) {
      System.err.println("Connection interrupted");
      return null;
    }
    //просто оповещение о подключении
    System.out.println("Connection is set" +
            "\nclient ip:" + connectedClient.socket().getInetAddress().getHostAddress() +
            "\nclient port:" + connectedClient.socket().getPort());

    connectedClient.configureBlocking(false);
    return connectedClient;
  }
}
