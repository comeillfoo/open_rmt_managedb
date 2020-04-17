package perusal;

import communication.Component;
import communication.Segment;
import communication.Mediator;

/**
 * Шаблон модуля чтения запросов от клиента,
 * преобразующий полученные байты во внятный клиентский запрос.
 */
public abstract class QueryReader implements Component {
  protected final Mediator kapellmeister;
  public abstract void retrieve(Segment parcel);
  public QueryReader(Mediator m) {
    kapellmeister = m;
  }
}
