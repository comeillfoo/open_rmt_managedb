package communication.wrappers;

import communication.ServerCustomer;

import java.nio.channels.spi.AbstractSelectableChannel;

/**
 * КГБ пакет
 */
public final class DossierBag extends TunnelBag {
  private final ServerCustomer customer; // записанный в списке сервера клиент
  /**
   * Конструктор, принимающий
   * анал клиента и информацию о нем
   * @param channel канал клиента
   * @param customer досье на клиента
   */
  public DossierBag(AbstractSelectableChannel channel, ServerCustomer customer) {
    super(channel);
    this.customer = customer;
  }

  /**
   * Свойство получения
   * информации о клиенте
   * @return сборщик информации о клиенте
   */
  public ServerCustomer Customer() { return customer; }
}
