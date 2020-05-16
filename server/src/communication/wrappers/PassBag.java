package communication.wrappers;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Пакет, требующийся при регистрации
 * нового клиента. Типа пасспорта на ресепшене
 * или полиса в регистратуре, т.е. без него данные
 * о клиенте нельзя будет записать в базу
 * @author Come_1LL_F00
 * @author Leargy aka Anton Sushkevich
 * @see TunnelBag
 * @see communication.Valuable
 */
public final class PassBag extends TunnelBag {
  private final Selector GREEDY; // селектор на сервере

  /**
   * Вот этя устанавливает
   * серверный канал и селектор,
   * требующиеся для регистрации.
   * Так как с этого канала мы и получаем нового клиента
   * @param server серверный канал
   * @param selector селектор
   */
  public PassBag(ServerSocketChannel server, Selector selector) {
    super(server);
    GREEDY = selector;
  }

  /**
   * Свойство взятия серверного канала
   * @return серверный канал
   */
  @Override
  public ServerSocketChannel Channel() { return (ServerSocketChannel) super.CHANNEL; }

  /**
   * Свойство получения селектора
   * для регистрации
   * @return селектор
   */
  public Selector Select() { return GREEDY; }
}
