package perusal;


import communication.ClientPackage;
import communication.Component;
import communication.Valuable;
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
  private final ByteBuffer BYTE_BUFFER = ByteBuffer.allocate(2*1024);

  // builders

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
  }

  // methods

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
      // TODO: логировать
      // TODO: отправить уведомление клиенту
      return;
    } catch (IOException e) {
      // TODO: логировать
      // TODO: отправить уведомление клиенту
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
      // TODO: логировать
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
      return;
    }
    ClientPackage query = null; // выделяем переменную под клиентский пакет
    // пытаемся прочитать объект из потока
    try {
     query = (ClientPackage) objstream.readObject();
    } catch (ClassNotFoundException e) {
      // TODO: логировать
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
      return;
    } catch (IOException e) {
      // TODO: логировать
      // TODO: отправить отчет посреднику, чтобы тот уведомил клиента (возможно, только для режима debug)
      return;
    }
    // TODO: разобраться с присылаемыми данными
    // формируем команду, требующую исполнения
    RawDecree decree = query.getCommand();

    // формируем полностью запрос
    // сокет клиента нужен, чтобы можно
    // из модуля обработки сразу перенаправить
    // на модуль отправки результатов
    QueryBag q = new QueryBag(tmpChannel, decree);
    // отправляем полученные данные на модуль обработки запроса
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

  /*
  // Антон це твое
  // я это переписал, чтобы
  // избавиться от лишних полей в классе
  public void retrieveOld(DossierBag parcel) {
    BYTE_BUFFER.clear();
    SocketChannel tempChannel = (SocketChannel) parcel.Channel();
    try {
      if (tempChannel.read(BYTE_BUFFER) == -1) {
        throw new IOException();
      }
      BYTE_BUFFER.flip();
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(BYTE_BUFFER.array());
      ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
      ClientPackage query = (ClientPackage) objectInputStream.readObject();
      KAPELLMEISTER.notify(this, new Segment(tempChannel, (Serializable) query));
    }catch (IOException e) {
    } catch (ClassNotFoundException e) {
    }finally {
      try {
        byteArrayInputStream.close();
        objectInputStream.close();
      }catch (IOException |NullPointerException e) {
        System.err.println("Ты как сюда добрался? O.O");
      }
    }
  }
  */
}
