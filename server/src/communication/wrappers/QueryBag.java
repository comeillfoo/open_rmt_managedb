package communication.wrappers;

import communication.ServerCustomer;
import communication.Valuable;
import instructions.rotten.RawDecree;

import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;

/**
 * Пакет с запросом клиента,
 * информацией о нем и канал,
 * на который нужно отправить результат обработки запроса
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @auhtor Leargy aka Anton Sushkevich
 * @see DossierBag
 * @see TunnelBag
 * @see Valuable
 */
public final class QueryBag extends TunnelBag {
  private final RawDecree QUERY; // сам клиентский запрос

  /**
   * Конструктор, принимающий
   * анал клиента и информацию о нем,
   * а также его запрос о команде,
   * что нужно выполнить
   * @param channel  канал клиента
   * @param query команда на исполнение
   */
  public QueryBag(SocketChannel channel, RawDecree query) {
    super(channel);
    QUERY = query;
  }

  /**
   * Свойство взятия клиентского запроса
   * @return присланный запрос
   */
  public final RawDecree Query() { return QUERY; }

  /**
   * Свойство получения именно клиентского канала
   * @return клиентский канал
   */
  @Override
  public final SocketChannel Channel() { return (SocketChannel) super.Channel(); }
}
