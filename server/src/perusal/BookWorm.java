package perusal;

import communication.Segment;
import communication.Mediator;
import parsing.interaction.instructions.Command;

import java.io.IOException;
import java.io.ObjectInputStream;

public final class BookWorm extends QueryReader {
  // fields
  // builders
  public BookWorm(Mediator m) { super(m); }
  // methods
  @Override
  public void retrieve(Segment parcel) {
    try(ObjectInputStream instream = new ObjectInputStream(parcel.getIntoClient())) {
      Command query = (Command) instream.readObject();
      kapellmeister.notify(this, new Segment(parcel.getClient(), query));
    } catch (IOException e) {
      // TODO: логировать ошибку
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
    } catch (ClassNotFoundException e) {
      // TODO: логировать ошибку
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
    }
  }
}
