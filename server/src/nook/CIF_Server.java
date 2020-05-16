package nook;

import communication.Mediator;
import communication.wrappers.AlertBag;
import communication.wrappers.PassBag;
import receiver.Hostess;
import systemcore.ServerController;

import java.io.IOException;
import java.net.Socket;
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
          if (!readyKey.isValid()) {
            continue;
          }
          // если к серверу пытается кто-то подключиться, то подключаем
          if (readyKey.isAcceptable()) register(readyKey, selector);
          // если клиент отправил что-то, то обрабатываем запрос
          if (readyKey.isReadable()) service(readyKey);
          //УБЕРАЕМ КЛЮЧИ КОТОРЫЕ УЖЕ БЫЛИ ОБСЛУЖЕНЫ!
          keyIterator.remove();
        }
      }
    }catch (UnknownHostException e) {e.printStackTrace();}
    catch (IOException e) {e.printStackTrace();}
  }
  private void register(SelectionKey readyKey, Selector alarm) throws IOException {
    //добавил,чтобы опустить ошибку после разрыва подключения со стороны клиента (А такая тут и не выпадет P.S Leargy)
    try {
      new Hostess((ServerController) controller).listen(new PassBag((ServerSocketChannel) readyKey.channel(), selector));
    }catch (NullPointerException e){
      System.err.println("<connection interrupted>");
    }
  }

  private void service(SelectionKey readyKey) throws IOException {
    AlertBag alertBag = new AlertBag((SocketChannel) readyKey.channel(), null);
    controller.notify(this, alertBag);
  }

  @Override
  public void closeConnection(AlertBag parcel) {
    try {
      Socket client = parcel.Channel().socket();
      System.out.println("Client disconnect" +
              "\nclient ip:" + client.getInetAddress().getHostAddress() +
              "\nclient port:" + client.getPort() +
              "\n____________________");
      client.close(); //close client's Socket to remove key from selector
    }catch (IOException e) {}
  }
}
