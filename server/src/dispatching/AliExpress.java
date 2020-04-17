package dispatching;

import communication.Segment;
import communication.Mediator;

import java.io.IOException;
import java.io.ObjectOutputStream;

public final class AliExpress extends Dispatcher {
  public void sendCorona(Segment postcard) {
    try (ObjectOutputStream sitcomInator = new ObjectOutputStream(postcard.getOutClient())) {
      sitcomInator.writeObject(postcard.getData());
    } catch (IOException e) { System.err.println("Occurred some shit in your file system or io streams"); }
  }

  public AliExpress(Mediator m) { super(m); }
}
