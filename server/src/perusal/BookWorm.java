package perusal;


import communication.ClientPackage;
import communication.Component;
import communication.Segment;
import communication.Valuable;
import communication.wrappers.DossierBag;
import systemcore.ServerController;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Модуль чтения запроса от клиента
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka  Lenar Khannanov
 * @see QueryReader
 */
public final class BookWorm extends QueryReader {
  // fields
  private ByteArrayInputStream byteArrayInputStream;
  private ObjectInputStream objectInputStream;
  private ByteBuffer byteBuffer = ByteBuffer.allocate(2*1024);
  // builders
  public BookWorm(ServerController kapellmeister) { super(kapellmeister); }
  // methods
  @Override
  public void retrieve(DossierBag parcel) {
    byteBuffer.clear();
    SocketChannel tempChannel = (SocketChannel) parcel.Channel();
    try {
      if (tempChannel.read(byteBuffer) == -1) {
        throw new IOException();
      }
      byteBuffer.flip();
      byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
      objectInputStream = new ObjectInputStream(byteArrayInputStream);
      ClientPackage query = (ClientPackage) objectInputStream.readObject();
      KAPELLMEISTER.notify(this, new Segment(tempChannel, (Serializable) query));
    }catch (IOException e) {
      // TODO: логировать ошибку
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
    } catch (ClassNotFoundException e) {
      // TODO: логировать ошибку
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
    }finally {
      try {
        byteArrayInputStream.close();
        objectInputStream.close();
      }catch (IOException |NullPointerException e) {
        System.err.println("Ты как сюда добрался? O.O");
      }
    }
  }

  @Override
  public void notify(Component sender, Valuable data) {
    
  }
}
