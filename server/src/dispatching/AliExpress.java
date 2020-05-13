package dispatching;

import communication.Component;
import communication.Mediator;
import communication.Segment;
import communication.Valuable;
import czerkaloggers.HawkPDroid;
import czerkaloggers.dispatching.TT_32_GE3;
import systemcore.ServerController;

import java.io.*;
import java.nio.ByteBuffer;

public final class AliExpress extends Dispatcher {
  private final HawkPDroid<AliExpress> pentode;
  private ByteArrayOutputStream byteArrayOutputStream;
  private ObjectOutputStream sitcomInator;
  @Override
  public void sendCorona(Segment postcard) {
    byteArrayOutputStream = new ByteArrayOutputStream();
    try {
      sitcomInator = new ObjectOutputStream(byteArrayOutputStream);
      sitcomInator.writeObject(postcard.getData());
      sitcomInator.flush();
      postcard.getClient().write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
    } catch (IOException e) { System.err.println("Occurred some shit in your file system or io streams"); }
  }

  public AliExpress(ServerController m) {
    super(m);
    pentode = (HawkPDroid<AliExpress>) TT_32_GE3.assemble(this, TT_32_GE3::new);
  }

  @Override
  public void notify(Component sender, Valuable data) {

  }
}
