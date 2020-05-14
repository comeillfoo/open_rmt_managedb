package communication.wrappers;

import instructions.concrete.ConDecree;

import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;

public final class ExecuteBag extends TunnelBag {
  private final ConDecree DECREE;
  /**
   * Конструктор принимающий
   * наш this very k-nal
   * @param channel клиентский или серверный канал
   */
  public ExecuteBag(SocketChannel channel, ConDecree decree) {
    super(channel);
    DECREE = decree;
  }

  @Override
  public SocketChannel Channel() { return (SocketChannel) super.CHANNEL; }

  public ConDecree Exec() { return DECREE; }
}
