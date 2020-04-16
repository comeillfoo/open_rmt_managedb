package misc;

import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Queue;

public class Conveyor {
  private static final Queue<Query> customers = new ArrayDeque<>();
  public static void enroll(SocketChannel client) {
    Query request = new Query(client);
    customers.add(request);
  }
}
