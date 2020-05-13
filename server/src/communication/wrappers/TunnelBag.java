package communication.wrappers;

import communication.Valuable;

import java.nio.channels.spi.AbstractSelectableChannel;

/**
 *
 */
public class TunnelBag implements Valuable {
  protected final AbstractSelectableChannel channel;

  public TunnelBag(AbstractSelectableChannel channel) { this.channel = channel; }

  public AbstractSelectableChannel Channel() { return channel; }
}
