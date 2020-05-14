package perusal;

import communication.Component;
import communication.Mediator;
import communication.wrappers.DossierBag;
import communication.wrappers.TunnelBag;
import systemcore.ServerController;

/**
 * Шаблон модуля чтения запросов от клиента,
 * преобразующий полученные байты во внятный клиентский запрос.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public abstract class QueryReader implements Component, Mediator {
  protected final ServerController KAPELLMEISTER; // контроллер модуля

  // главный метод чтения
  /**
   * Этот метод, как плазменный резак --
   * вычленяет нужное из клиента,
   * а именно его запрос и передает
   * его следующему модулю
   * @param parcel копромат на клиента
   */
  public abstract void retrieve(TunnelBag parcel);

  // главный конструктор модуля
  /**
   * Конструктор по стандарту,
   * в котором устанавливается
   * хозяин модуля
   * @param core ядро системы, т.е. контроллер сервера
   */
  public QueryReader(ServerController core) { KAPELLMEISTER = core; }
}
