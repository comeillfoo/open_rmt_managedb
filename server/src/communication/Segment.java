package communication;



import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс, представляющий собой некую посылку или конверт,
 * хранящий информацию об отправителе, и обрабатываемые для него данные.
 * <strong>
 *   ATTENTION: в ходе работы сервера реальный тип данных может кардинально меняться:
 *   начиная от просто команды, заканчивая отчетами о выполненных работах.
 * </strong>
 * @see Command
 * @see Report
 */
public class Segment implements Valuable, Serializable {
  // fields
  protected final Serializable DATA;
  protected final SocketChannel CLIENT;
  // builders
  public Segment(SocketChannel client, Serializable data) {
    this.CLIENT = client;
    this.DATA = data;
  }
  // properties and methods
  public Serializable getData() { return DATA; }
  //public InputStream getIntoClient() throws IOException { return client.socket().getInputStream(); }
  //public OutputStream getOutClient() throws IOException { return client.socket().getOutputStream(); }
  public SocketChannel getClient() { return CLIENT; }
}
