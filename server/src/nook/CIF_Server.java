package nook;

import communication.Mediator;
import communication.Segment;
import receiver.Receptionist;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.security.Key;
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

        Thread.sleep(500);//искуственно замедляем цикл
        
        int readyChannels = selector.selectNow();
        if (readyChannels == 0) continue;
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = keys.iterator();
        while (keyIterator.hasNext()) {
          SelectionKey readyKey = keyIterator.next();
          if(!readyKey.isValid()){
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
    } catch (InterruptedException e) {e.printStackTrace();}
    catch (UnknownHostException e) {e.printStackTrace();}
    catch (IOException e) {e.printStackTrace();}
  }
  private void register(SelectionKey readyKey, Selector alarm) throws IOException {
    //добавил,чтобы опустить ошибку после разрыва подключения со стороны клиента
    try {
      Receptionist.listen((ServerSocketChannel) readyKey.channel()).register(alarm, SelectionKey.OP_READ|SelectionKey.OP_WRITE|SelectionKey.OP_CONNECT);
    }catch (NullPointerException e){

    }

  }
  private void service(SelectionKey readyKey) throws IOException {
//   if (closeConnect(readyKey)){
//     return;
//   }
    controller.notify(this, new Segment((SocketChannel) readyKey.channel(), null));
  }

//  private boolean closeConnect(SelectionKey tempKey) throws IOException {
//    ByteBuffer byteBuffer =ByteBuffer.allocate(1024);
//    SocketChannel tempChannel = ((SocketChannel) tempKey.channel());
//    if(tempChannel.read(byteBuffer) == -1) {
//      tempChannel.finishConnect();
//      tempChannel.socket().close();
//      return true;
//    }
//    return false;
//  }
}
