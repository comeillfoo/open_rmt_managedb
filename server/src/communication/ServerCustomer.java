package communication;

import java.net.InetAddress;
import java.util.UUID;

/**
 * !!!Не ипользуется
 * Сущность, клиента,
 * подключенного на сервер,
 * дабы знать основную информацию
 * о нем и хранить собственную базу
 * на ресепшене
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @auhtor Leargy aka Anton Sushkevich
 */
public final class ServerCustomer {
  private final UUID id; // уникальный идентификатор клиента
  private final String name; // имя клиента
  private final InetAddress ip; // его адрес
  private final int port; // порт его подключения
  private final String varName; // название его переменной окружения

  /**
   * Упрощенный конструктор без имени клиента,
   * а с его основными параметрами подключения типа
   * адреса, порта и с обязательным параметром -
   * название переменной окружения его коллекции
   * @param ip адрес клиента в сети
   * @param port порт клиента
   * @param varName название переменной окружения
   */
  public ServerCustomer(InetAddress ip, int port, String varName) {
    id = UUID.randomUUID();
    name = "Client #" + id;
    this.ip = ip;
    this.port = port;
    this.varName = varName;
  }

  /**
   * Расширенный конструктор с
   * дополнительным параметром имени
   * клиента, но по логике работает также,
   * как и конструктор {@link ServerCustomer#ServerCustomer(InetAddress, int, String)}
   * @param name имя клиента
   * @param ip адрес клиента в сети
   * @param port порт клиента
   * @param varName название переменной окружения
   */
  public ServerCustomer(String name, InetAddress ip, int port, String varName) {
    id = UUID.randomUUID();
    this.name = name;
    this.ip = ip;
    this.port = port;
    this.varName = varName;
  }

  /**
   * Свойство получения
   * уникального идентификатора
   * клиента на сервере
   * @return строковое представление ID
   */
  public String ID() { return id.toString(); }

  /**
   * Свойство получения имени клиента
   * @return строка с именем
   */
  public String Name() { return name; }

  /**
   * Свойство получения названия
   * переменной окружения, в которой относительный
   * путь до коллекции, с которой работаем
   * @return строковое название переменной окружения
   */
  public String $VAR() { return varName; }

  /**
   * Свойство получения полного
   * адреса клиента, т.е. его порт
   * и IP-адрес
   * @return строка адреса клиента
   */
  public String InetAddress() { return ip.toString() + ":" + port; }
}
