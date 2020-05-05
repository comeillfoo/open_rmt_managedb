package perusal;

import communication.Component;
import communication.Segment;
import communication.Mediator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Шаблон модуля чтения запросов от клиента,
 * преобразующий полученные байты во внятный клиентский запрос.
 */
public abstract class QueryReader implements Component {
  protected final Mediator mediator;
  public abstract void retrieve(Segment parcel);
  public QueryReader(Mediator m) {
    mediator = m;
  }
}
