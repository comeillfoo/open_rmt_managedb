package receiver;

import communication.Component;
import communication.Mediator;
import communication.ServerCustomer;
import communication.wrappers.HandShakeBag;
import communication.wrappers.NetBag;
import systemcore.ServerController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// TODO: обязательно требуется ref*cktoring
/**
 *
 */
public abstract class Receptionist implements Component, Mediator {
  protected final ServerController core; // контроллер системы сервера
  // список подключенных к серверу клиентов
  protected final Map<SocketChannel, ServerCustomer> customers = new HashMap<>();

  /**
   * Стандартный конструктор,
   * с установкой контроллера над модулем
   * @param core контроллер системы модулей
   */
  public Receptionist(ServerController core) { this.core = core; }

  /**
   * Метод постановки подключенного клиента на учет
   * @param listener
   * @throws IOException
   */
  public void listen(NetBag packet) {
    // достать серверный канал
    ServerSocketChannel server = packet.Channel();
    // достать селектор сервера
    Selector selector = packet.Select();
    // определить канал клиента
    SocketChannel connected = null;
    // попробывать подключить клиента к серверу
    try { connected = server.accept(); } catch (IOException e) { // TODO: логировать о неуспешности подключения
      return;
    }

    //добавил чтобы избежать написание второго try
    if (connected == null) {
      System.err.println("Connection interrupted");
      return;
    }
    // TODO: правильно обработать прерванное подключение

    // просто оповещение о подключении
    System.out.println("Connection is set" +
            "\nclient ip:" + connected.socket().getInetAddress().getHostAddress() +
            "\nclient port:" + connected.socket().getPort() +
            "\n____________________");
    // TODO: залогировать успешное подключение
    // попытаться сконфигурировать канал в неблокирующий режим
    try { connected.configureBlocking(false); } catch (IOException e) { // TODO: логировать неуспешност конфигурации
      return;
    }
    // TODO: логировать успешность конфигурации
    // попытка добавить клиента в выборку
    try { connected.register(selector, SelectionKey.OP_READ);
    } catch (ClosedChannelException e) {
      // TODO: логировать неуспешность регистрации
      return;
    }
    // TODO: логировать успешность регистрации
    // взять у клиента имя и название переменной окружения
    // создаем временный буфер
    ByteBuffer buffer = ByteBuffer.allocate(2*1024);
    buffer.clear();
    // читаем с клиента байты
    try { connected.read(buffer);
    } catch (IOException e) {
      // TODO: логировать неуспешность приветственной передачи
      return;
    }
    // TODO: логировать успешность входной передачи
    buffer.flip(); // Смотри, ща салтуху *бану
    // закидываем, полученные данные с поток байтов
    ByteArrayInputStream bistream = new ByteArrayInputStream(buffer.array());
    // определяем поток сериализации
    ObjectInputStream objstream = null;
    try {
      objstream = new ObjectInputStream(bistream);
    } catch (IOException e) {
      // TODO: логировать неуспешность создания потока
      return;
    }
    // TODO: логировать успешность создания потока

    // определяем входной пакет
    HandShakeBag received = null;
    // пытаемся прочитать
    try { received = (HandShakeBag) objstream.readObject(); }
    catch (ClassNotFoundException | ClassCastException e) {
      // TODO: логировать ошибку клиента по отправке несуществующего класса
      return;
    } catch (IOException e) {
      // TODO: логировать
      return;
    } finally {
      // в любом случе нужно их закрыть
      try {
        objstream.close();
        bistream.close();
      } catch (IOException e) {
        // TODO: логировать
        System.exit(1);
      }
    }
    // проверили, отправили нам что-то
    if (received == null) {
      // TODO: логировать
      return;
    }

    // достаем нужные параметры как имя и название
    String name = received.Name();
    String varName = received.$VAR();
    Socket csBuffer = connected.socket();
    ServerCustomer newbie = name.isEmpty()?
        new ServerCustomer(csBuffer.getInetAddress(), csBuffer.getPort(), varName) :
        new ServerCustomer(name, csBuffer.getInetAddress(), csBuffer.getPort(), varName);
    customers.put(connected, newbie);
    // TODO: логировать успешное добавление клиента
  }

  /**
   *
   * @param clientChannel
   * @return
   */
  public ServerCustomer getCustomer(SocketChannel clientChannel) { return customers.get(clientChannel); }
}
