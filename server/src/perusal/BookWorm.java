package perusal;


import communication.ClientPackage;
import communication.Mediator;
import communication.Segment;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

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
      if (tempChannel.read(byteBuffer) == -1) {
        throw new IOException();
      }
      byteBuffer.flip();
      byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
      objectInputStream = new ObjectInputStream(byteArrayInputStream);
      ClientPackage query = (ClientPackage) objectInputStream.readObject();
      mediator.notify(this, new Segment(parcel.getClient(), (Serializable) query));
    }catch (IOException e) {

      mediator.notify(this, parcel); //This class got nothing, we lost.
      // TODO: логировать ошибку
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
    } catch (ClassNotFoundException e) {
      new ClassNotFoundException("Wrong server casting received package.", e).getMessage();
      // TODO: логировать ошибку
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
//    }catch (InvalidClassException e) {
//      new InvalidClassException("Server don't have an example of command in received package.",e.getMessage()).getMessage();
//    }
    }finally {
      try {
        byteArrayInputStream.close();
        objectInputStream.close();
      }catch (IOException |NullPointerException e) {
        System.err.println("Ты как сюда добрался? O.O");
      }
    }
  }
}
