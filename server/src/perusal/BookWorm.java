package perusal;

import communication.ClientPackage;
import communication.Segment;
import communication.Mediator;

import java.io.IOException;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public final class BookWorm extends QueryReader {
  // fields
  private ByteArrayInputStream byteArrayInputStream;
  private ObjectInputStream objectInputStream;
  private ByteBuffer byteBuffer = ByteBuffer.allocate(2*1024);
  // builders
  public BookWorm(Mediator m) { super(m); }
  // methods
  @Override
  public void retrieve(Segment parcel) {
    byteBuffer.clear();
    SocketChannel tempChannel = parcel.getClient();
    try {
      tempChannel.read(byteBuffer);
      byteBuffer.flip();
      byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
      objectInputStream = new ObjectInputStream(byteArrayInputStream);
      ClientPackage query = (ClientPackage) objectInputStream.readObject();
      mediator.notify(this, new Segment(parcel.getClient(), (Serializable) query));
    } catch ( IOException e) {
      mediator.notify(this,parcel); //This class got nothing, we lost.
      // TODO: логировать ошибку
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
    } catch (ClassNotFoundException e) {
      // TODO: логировать ошибку
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
    }
  }
}
