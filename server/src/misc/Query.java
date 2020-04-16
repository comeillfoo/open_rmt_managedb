package misc;

import java.nio.channels.SocketChannel;

public class Query {
  private final SocketChannel source;
  private final byte[] data = new byte[1024];
  private QueryStage current = QueryStage.PERUSAL;
  public Query(SocketChannel source) {
    this.source = source;
  }
}
