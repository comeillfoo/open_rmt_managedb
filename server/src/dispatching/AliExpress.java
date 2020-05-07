package dispatching;

import communication.Mediator;
import communication.Segment;

import java.io.*;
import java.nio.ByteBuffer;

public final class AliExpress extends Dispatcher {
  private  ByteArrayOutputStream byteArrayOutputStream;
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

  public AliExpress(Mediator m) { super(m); }

}
