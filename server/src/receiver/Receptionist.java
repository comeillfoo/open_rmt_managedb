package receiver;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public final class Receptionist {
  public static SocketChannel listen(ServerSocketChannel listener) throws IOException {
    SocketChannel connectedClient = listener.accept();
    connectedClient.configureBlocking(false);
    return connectedClient;
  }
}
