package communication;

import parsing.instructions.Command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
public class Segment {
  // fields
  protected final Serializable data;
  protected final SocketChannel client;
  // builders
  public Segment(SocketChannel client, Serializable data) {
    this.client = client;
    this.data = data;
  }
  // properties and methods
  public Serializable getData() { return data; }
  public InputStream getIntoClient() throws IOException { return client.socket().getInputStream(); }
  public OutputStream getOutClient() throws IOException { return client.socket().getOutputStream(); }
  public SocketChannel getClient() { return client; }
}
