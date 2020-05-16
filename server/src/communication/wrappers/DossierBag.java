package communication.wrappers;

import communication.ServerCustomer;

import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;

/**
 * !!!Не используется
 * КГБ пакет, хранящий копромат
 * на клиента, его имя, адрес, порт,
 * адрес его мамы, название переменной окружения,
 * в которой путь до его коллекции
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see TunnelBag
 * @see communication.Valuable
 */
public class DossierBag extends TunnelBag {
  protected final ServerCustomer CUSTOMER; // записанный в списке сервера клиент
  
  /**
   * Конструктор, принимающий
   * анал клиента и информацию о нем
   * @param channel канал клиента
   * @param customer досье на клиента
   */
  public DossierBag(SocketChannel channel, ServerCustomer customer) {
    super(channel);
    CUSTOMER = customer;
  }

  /**
   * Свойство взятия клиентского к/анала
   * @return клиентский канал
   */
  @Override
  public final SocketChannel Channel() { return (SocketChannel) super.CHANNEL; }

  /**
   * Свойство получения
   * информации о клиенте
   * @return сборщик информации о клиенте
   */
  public final ServerCustomer Customer() { return CUSTOMER; }
}
