package perusal;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import communication.Segment;
import communication.Mediator;
import parsing.interaction.instructions.Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public final class BookWorm extends QueryReader {
  // fields
  // builders
  public BookWorm(Mediator m) { super(m); }
  // methods
  @Override
  public void retrieve(Segment parcel) {
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    SocketChannel tempChannel = parcel.getClient();
    try {
    if(tempChannel.read(byteBuffer) == -1) {
      tempChannel.finishConnect();
      tempChannel.socket().close();
      return;
    }
    // TODO: бросает IllegalBlockingModeException после получения сообщения от клиента и после отключения клиента(второй случай выходит если Receptionist возвращает null)
      SocketChannel instream = parcel.getClient();
      //Command query = (Command)
      instream.read(byteBuffer);
      Arrays.asList(byteBuffer).forEach(a -> System.out.println(a));
      //mediator.notify(this, new Segment(parcel.getClient(), query));
    } catch (IOException e) {
      // TODO: логировать ошибку
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
    //} catch (ClassNotFoundException e) {
      // TODO: логировать ошибку
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
    }
  }
}
