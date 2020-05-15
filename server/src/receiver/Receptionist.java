package receiver;

import communication.Component;
import communication.Mediator;
import communication.ServerCustomer;
import communication.wrappers.PassBag;
import systemcore.ServerController;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * Абстракция любого клиентского
 * менеджера на сервере. Ставит клиентов на прослушку,
 * пытается получить от клиента входной пакет с информацией
 * о его подключении и переменной окружения, на которую
 * определена его коллекция.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Component
 * @see Mediator
 */
public abstract class Receptionist implements Component, Mediator {
  protected final ServerController CORE; // контроллер системы сервера
  // список подключенных к серверу клиентов
  protected final Map<SocketChannel, ServerCustomer> CUSTOMERS = new HashMap<>();
  // выделяем буфер на постоянной основе 2 Кибибайта
  protected final ByteBuffer BYTE_BUFFER = ByteBuffer.allocate(2 * 1024);

  /**
   * Стандартный конструктор,
   * с установкой контроллера над модулем
   * @param core контроллер системы модулей
   */
  public Receptionist(ServerController core) { this.CORE = core; }

  /**
   * Метод постановки подключенного клиента на учет
   * @param packet входной сетевой пакет
   */
  public abstract void listen(PassBag packet);

  /**
   * Функция поиска клиента по каналу,
   * которому он подключился только что
   * @param clientChannel канал клиента
   * @return информация о клиенте
   */
  public final ServerCustomer search(SocketChannel clientChannel) { return CUSTOMERS.get(clientChannel); }


}
