package nook;

import communication.Mediator;
import communication.Segment;
import receiver.Receptionist;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public final class CIF_Server extends Server {
  private boolean isServerRun;
  private final Selector selector;

  public CIF_Server(Mediator m, Selector selector) {
    super(m);
    this.selector = selector;
  }
  @Override
  public void run() {
    try {
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
  private void register(SelectableChannel readyServer, Selector alarm) throws IOException {
    //добавил,чтобы опустить ошибку после разрыва подключения со стороны клиента
    try {
      Receptionist.listen((ServerSocketChannel) readyServer).register(alarm, SelectionKey.OP_READ);
    }catch (NullPointerException e){

    }

  }
  private void service(SelectableChannel channel) {
    controller.notify(this, new Segment((SocketChannel) channel, null));
  }
}
