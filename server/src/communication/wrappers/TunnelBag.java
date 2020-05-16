package communication.wrappers;

import communication.Valuable;

import java.nio.channels.spi.AbstractSelectableChannel;

/**
 * Базовый из всех сетевых пакетов,
 * хранящих лишь клиентский, либо серверный
 * канал. Спасибо Oracle, что дали возможность
 * нашим огранизмам передавать наследственные
 * признаки
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Valuable
 */
public class TunnelBag implements Valuable {
  // серверный аля клиентский к/анал
  protected final AbstractSelectableChannel CHANNEL;

  /**
   * Конструктор принимающий
   * наш this very k-nal
   * @param channel клиентский или серверный канал
   */
  public TunnelBag(AbstractSelectableChannel channel) { this.CHANNEL = channel; }

  /**
   * Свойство взятия канала
   * @return какой-то канал (клиент или сервер)
   */
  public AbstractSelectableChannel Channel() { return CHANNEL; }
}
