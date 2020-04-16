package nook;

import misc.Conveyor;
import misc.ServerTuner;
import receiver.Receptionist;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public final class CIF_Server implements Runnable {
  private final int port;
  private final int maxClientNumber = 50;
  private boolean isServerRun;

  public CIF_Server(int port) {
    this.port = port;
  }

  /**
   * Порядок действий на сервере, после установки порта:
   * <ol>
   *   <li>1 Установить адрес и порт сервера</li>
   *   <li>2 Создать сокет сервера</li>
   *   <li>3 Привязать созданный сокет к адресу и порту</li>
   *   <li>4 </li>
   * </ol>
   */
  @Override
  public void run() {
    try {
      Selector selector = ServerTuner.tune("localhost", port, maxClientNumber);
      isServerRun = true;
      while (isServerRun) {
        int readyChannels = selector.selectNow();
        if (readyChannels == 0) continue;
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = keys.iterator();
        while (keyIterator.hasNext()) {
          SelectionKey readyKey = keyIterator.next();
          // если к серверу пытается кто-то подключиться, то подключаем
          if (readyKey.isAcceptable()) register(readyKey.channel(), selector);
          // если клиент отправил что-то, то обрабатываем запрос
          if (readyKey.isReadable()) service(readyKey.channel());
        }
      }
    } catch (UnknownHostException e) {} catch (IOException e) {}
  }

  private void register(SelectableChannel readyServer, Selector alarm) throws IOException { Receptionist.listen((ServerSocketChannel) readyServer).register(alarm, SelectionKey.OP_READ); }

  private void service(SelectableChannel channel) {
    Conveyor.enroll((SocketChannel) channel);
  }
}
