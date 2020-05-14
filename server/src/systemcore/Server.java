package systemcore;

import communication.Component;
import communication.Report;
import communication.wrappers.AlertBag;
import communication.wrappers.PassBag;
import communication.wrappers.QueryBag;
import communication.wrappers.TunnelBag;
import instructions.rotten.base.RawSave;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Ну, сервер и сервер. Чо бубнить-то.
 * Хотя на самом деле он работает
 * в асинхронном режиме, который
 * обрабатывает два режима: запись
 * клиента на сервер, и обработка его запросов
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class Server implements Runnable, Component {
  private boolean isServerRun; // признак работы сервера
  private final Selector GREEDY; // селектор  сервера
  private final ServerSocketChannel STUNNEL; // канал сервера
  // ядро сервера, т.е. его контроллер
  private final ServerController CORE;

  /**
   * Стандартный конструктор с ссылками
   * на ядро сервера, селектор каналов
   * (для однопоточного исполнения) и
   * на открытый канал сервера
   * @param core контроллер сервера
   * @param selector селектор каналов
   * @param channel канал сервера
   */
  public Server(ServerController core, Selector selector, ServerSocketChannel channel) {
    CORE = core;
    GREEDY = selector;
    STUNNEL = channel;
    isServerRun = false;
  }

  /**
   * Основная логика сервера,
   * то есть так как должен работать сервер
   */
  @Override
  public void run() {
    isServerRun = true; // установили флаг запущенности сервера
    // начинаем обрабатывать запросы в асинхронном режиме
    // с помощью спин-лупов-пупов-лупов
    while(isServerRun) {
      // определяем число готовых каналов
      int numberReady = 0;
      // пытаемся поменять число готовых каналов
      try { numberReady = GREEDY.selectNow();
      } catch (IOException e) { // TODO: логировать ошибку
        numberReady = 0;
      }
      // если таковых нет, то идем на новый цикл
      if (numberReady == 0) continue;
      // берем множество готовых ключей
      Set<SelectionKey> keys = GREEDY.selectedKeys();
      // берем итератор по этому множеству
      Iterator<SelectionKey> keyIterator = keys.iterator();
      // проходимся по всем ключам
      while (keyIterator.hasNext()) {
        SelectionKey selected = keyIterator.next(); // берем следующий ключ
        // смотрим инвалидный ли ключ
        if (selected == null) continue;
        if (!selected.isValid()) continue;
        // если к серверу пытаются подключиться, то подключаем
        if (selected.isAcceptable()) signup(selected, GREEDY);
        // если клиент пытается прислать запрос, то обрабатываем
        if (selected.isReadable()) service(selected);
        // убираем обслуженный ключ
        keyIterator.remove();
      }
    }

  }

  /**
   * Метод подключения клиента
   * на сервер, делегирует свою
   * работу отдельному модулю
   * @param ready2Connect ключ сервера, готового принять клиента
   * @param fetcher селектор, хранящий ключи
   */
  private void signup(SelectionKey ready2Connect, Selector fetcher) {
    PassBag clientData = new PassBag((ServerSocketChannel) ready2Connect.channel(), fetcher);
    CORE.notify(this, clientData);
  }

  /**
   * Метод обслуживания клиента,
   * делегирует свою работу отдельному
   * модулю чтения и обработки запросов,
   * посылая данные на общий контроллер
   * @param clientKey ключ клиента, готового к прочтению
   */
  private void service(SelectionKey clientKey) {
    SocketChannel client = (SocketChannel) clientKey.channel();
    TunnelBag wrap = new TunnelBag(client);
    CORE.notify(this, wrap);
  }

  public void closeConnection(AlertBag parcel) {
    try {
      Socket client = parcel.Channel().socket();
      String message = "Client disconnect" +
          "\nclient ip:" + client.getInetAddress().getHostAddress() +
          "\nclient port:" + client.getPort() +
          "\n____________________";
      CORE.notify(this, new AlertBag(null, new Report(0, message)));
      client.close(); //close client's Socket to remove key from selector
      CORE.notify(this, new QueryBag(null, new RawSave()));
    } catch (IOException e) {
      CORE.notify(this, new AlertBag(null, new Report(1, "Ошибка во время отключения")));
    }
  }
}
