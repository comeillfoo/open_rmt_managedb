package communication.wrappers;

import communication.Valuable;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 *
 */
public final class NetBag extends TunnelBag {
  private final Selector greedy;

  /**
   *
   * @param server
   * @param selector
   */
  public NetBag(ServerSocketChannel server, Selector selector) {
    super(server);
    greedy = selector;
  }

  /**
   *
   * @return
   */
  @Override
  public ServerSocketChannel Channel() { return (ServerSocketChannel) super.Channel(); }

  /**
   *
   * @return
   */
  public Selector Select() { return greedy; }
}
