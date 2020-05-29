package communication.wrappers;

import instructions.concrete.ConDecree;

import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;

/**
 * Пакет исполнения, хранящий
 * клиентский канал и команду,
 * что клиента заказал
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see TunnelBag
 */
public final class ExecuteBag extends TunnelBag {
  private final ConDecree DECREE;
  /**
   * Конструктор принимающий
   * наш this very k-nal и команду,
   * требующую выполнения
   * @param channel клиентский или серверный канал
   * @param decree конкретно передаваемая команда
   */
  public ExecuteBag(SocketChannel channel, ConDecree decree) {
    super(channel);
    DECREE = decree;
  }

  /**
   * Свойство взятия канал
   * пользователя
   * @return канал клиента
   */
  @Override
  public SocketChannel Channel() { return (SocketChannel) super.CHANNEL; }

  /**
   * Свойство взятия команды
   * на исполнение
   * @return конкретная команда
   */
  public ConDecree Exec() { return DECREE; }
}
