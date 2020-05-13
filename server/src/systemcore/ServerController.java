package systemcore;

import communication.*;
import communication.wrappers.DossierBag;
import communication.wrappers.TunnelBag;
import czerkaloggers.HawkPDroid;
import czerkaloggers.systemcore.S4_C8_GE3;
import dispatching.AliExpress;
import dispatching.Dispatcher;
import parsing.Resolver;
import parsing.SubProcessController;
import perusal.BookWorm;
import perusal.QueryReader;
import receiver.Hostess;
import receiver.Receptionist;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Контроллер системы, некая плата
 * с компонентами, управляющая работой сервера
 * и его модулями:
 * <ul>
 *   <li>Сервер</li>
 *   <li>Модуль принятия</li>
 *   <li>Модуль чтения</li>
 *   <li>Модуль разборки</li>
 *   <li>Модуль отправки</li>
 *   <li>Модуль логирования</li>
 * </ul>
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Mediator
 */
public final class ServerController implements Mediator {
  // список компонент серверной системы
  private final Server MAIN_SERVER; // сервер, который мы поднимаем
  private final Receptionist SECRETARY; // менеджер, что оперирует подключенными клиентами
  private final QueryReader PARSER; // модуль чтения запросов
  private final Dispatcher SUPPLIER; // модуль отправки запроса обратно клиента
  private final HawkPDroid<ServerController> TRIODE; // логгер


  // конструктор, инициализирующий полностью систему
  /**
   * Конструктор, собирающий систему из
   * следующих компонент:
   * <ul>
   *   <li>Сам сервер</li>
   *   <li>Модуль приема подключений</li>
   *   <li>Модуль чтения запросов</li>
   *   <li>
   *     Модуль обработки запросов
   *     (для каждого клиента он свой)
   *   </li>
   *   <li>Модуль отправки ответов</li>
   *   <li>Логгирующий элемент</li>
   * </ul>
   */
  public ServerController(Selector selector, ServerSocketChannel channel) {
    MAIN_SERVER = new Server(this, selector, channel); // создали сервер
    SECRETARY = new Hostess(this); // поставили девушку на ресепшен
    PARSER = new BookWorm(this); // установили модуль чтения
    SUPPLIER = new AliExpress(this); // установили модуль отправки
    TRIODE = new S4_C8_GE3(this); // установили логгер
  }

  /**
   * Основной метод сортировки данных
   * по компонентам
   * @param sender отправитель
   * @param data отправляемые данные
   */
  @Override
  public void notify(Component sender, Valuable data) {
    // если отправил сервер
    // то отправляем на парсинг
    if (sender == MAIN_SERVER) {
      TunnelBag parcel = (TunnelBag) data;
      // достаем клиентский anal
      SocketChannel current = (SocketChannel) parcel.Channel();
      // достать с ресепшена данные о клиенте
      ServerCustomer record = SECRETARY.search(current);
      // преобразовать в новый перекидаемый пакет
      // с данными о клиенте и канале клиенте
      DossierBag dossier = new DossierBag(current, record);
      // отправили модулю чтения преобразованный пакет
      PARSER.retrieve(dossier);
    } else if (sender == PARSER) {
      new SubProcessController(this).parse((Segment) data);
    } else if (sender instanceof Resolver)
      SUPPLIER.sendCorona((Segment) data); // отправили клиенту
  }

  /**
   * Свойство взятия ссылки на сервер
   * @return текущий сервер
   */
  public final Server whereServer() { return MAIN_SERVER; }
}
