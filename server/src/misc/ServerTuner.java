package misc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public final class ServerTuner {
  public static Selector tune(String hostname, int port, int maximum) throws IOException, UnknownHostException {
    ServerSocket connection = new ServerSocket(port, maximum);
    connection.bind(new InetSocketAddress(InetAddress.getByName(hostname), port));
    Selector selector = Selector.open();
    ServerSocketChannel serverSocketChannel = connection.getChannel();
    serverSocketChannel.configureBlocking(false);
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    return selector;
  }
}
