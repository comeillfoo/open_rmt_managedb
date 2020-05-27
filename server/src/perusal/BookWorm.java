package perusal;


import communication.ClientPackage;
import communication.Component;
import communication.Report;
import communication.Valuable;
import communication.wrappers.AlertBag;
import communication.wrappers.QueryBag;
import communication.wrappers.TunnelBag;
import czerkaloggers.HawkPDroid;
import czerkaloggers.perusal.C9_T9_GE3;
import instructions.rotten.RawDecree;
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
  private final HawkPDroid<BookWorm> CHRONICLER; // логирующий элемент
  private ByteArrayInputStream byteArrayInputStream;
  private ObjectInputStream objectInputStream;
  // наш буфер с приходящими данными все 2 Кибибайта наши на веки-вечные
  private final ByteBuffer BYTE_BUFFER = ByteBuffer.allocate(3*1024);

  // главный конструктор
  /**
   * Проектный конструктор по умолчанию,
   * устанавливающий начальника модуля
   * @param kapellmeister контроллер модулем
   */
  public BookWorm(ServerController kapellmeister) {
    super(kapellmeister);
    // собираем наш логгер
    CHRONICLER = (HawkPDroid<BookWorm>) C9_T9_GE3.assemble(this, C9_T9_GE3::new);
    CHRONICLER.logboard(0,"Собран логгер модуля чтения");
  }

  // главный метод взятия запроса с клиента
  /**
   * Метод чтения клиентского запроса
   * @param parcel копромат на клиента
   */
  @Override
  public void retrieve(TunnelBag parcel) {
    // очищаем буфер
    BYTE_BUFFER.clear();
    // выделяем переменную под клиентский канал/анал
    SocketChannel tmpChannel = (SocketChannel) parcel.Channel();

    // пытаемся прочитать данные с нашей Шанель в буфер
    try {
      // если резко достигли конца потока, то выкидываем исключение
      if (tmpChannel.read(BYTE_BUFFER) == -1)
        throw new EOFException("Достигнут конец потока");
    } catch (EOFException e) {
      CHRONICLER.logboard(0xe0f,"Неожиданно достигнут конец потока");
//      CHRONICLER.notify(0xe0f,"Неожиданно достигнут конец потока");
      KAPELLMEISTER.ImmediateStop(new AlertBag((SocketChannel) parcel.Channel(), new Report(0xe0f, "Неожиданно достигнут конец потока")));
      return;
    } catch (IOException e) {
      CHRONICLER.logboard(10,"Не удалось прочитать пользовательский запрос");
      CHRONICLER.notify(10,"Не удалось прочитать пользовательский запрос");
      KAPELLMEISTER.ImmediateStop(new AlertBag(null, new Report(10, "Не удалось прочитать пользовательский запрос")));
      return;
    }
    // смотри, ща сальтуху *бану
    BYTE_BUFFER.flip();
    // создаем из буфера поток чтения байтов из буфера
    ByteArrayInputStream bstream = new ByteArrayInputStream(BYTE_BUFFER.array());
    ObjectInputStream objstream = null; // выделяем переменную для десериализации объектов
    // пытаемся создать объектный поток для чтения готовых объектов
    try {
      objstream = new ObjectInputStream(bstream);
    } catch (IOException e) {
      CHRONICLER.logboard(10,"Не удалось создать поток сериализации");
      return;
    }
    ClientPackage query = null; // выделяем переменную под клиентский пакет
    // пытаемся прочитать объект из потока
    try {
     query = (ClientPackage) objstream.readObject();
    } catch (ClassNotFoundException e) {
      CHRONICLER.logboard(0xcfe,"Присланные данные не удовлетворяют протоколу между сервером и клиентом");
      CHRONICLER.notify(0xcfe,"Присланные Вами данные не соответсвуют формату сервера, проверьте: туда ли Вы подключились");
      return;
    } catch (IOException e) {
      CHRONICLER.logboard(10,"Не удалось десериализовать данные из потока");
      CHRONICLER.notify(10,"Не удалось десериализовать данные из потока");
      return;
    }
    // TODO: разобраться с присылаемыми данными
    // формируем команду, требующую исполнения
    RawDecree decree = query.getCommand();
    CHRONICLER.logboard(0,"Данные успешно прочитаны -- сформирован запрос");
    // формируем полностью запрос
    // сокет клиента нужен, чтобы можно
    // из модуля обработки сразу перенаправить
    // на модуль отправки результатов
    QueryBag q = new QueryBag(tmpChannel, decree);
    // отправляем полученные данные на модуль обработки запроса
    CHRONICLER.logboard(0,"Запрос отправлен на обработку");
    notify(this, q);
  }

  // метод отправки запроса
  /**
   * Метод отправки всех благ
   * от души -- отправляем данные
   * на контроллер от нас или
   * логирующего элемента
   * @param sender отправитель данных
   * @param data сами данные
   */
  @Override
  public void notify(Component sender, Valuable data) {
    // если отправляем либо мы, либо логгер,
    // то возвращаем на контроллер
    if ((sender == CHRONICLER) || (sender == this))
      KAPELLMEISTER.notify(this, data);
  }
}
