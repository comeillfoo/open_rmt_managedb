package systemcore;

import communication.Component;
import communication.wrappers.NetBag;
import communication.wrappers.TunnelBag;
import czerkaloggers.HawkPDroid;

import java.io.IOException;
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
  private final Selector greedy; // селектор  сервера
  private final ServerSocketChannel serverTunnel; // канал сервера
  // ядро сервера, т.е. его контроллер
  private final ServerController core;

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
    this.core = core;
    greedy = selector;
    serverTunnel = channel;
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
      try { numberReady = greedy.selectNow();
      } catch (IOException e) { // TODO: логировать ошибку
        numberReady = 0;
      }
      // если таковых нет, то идем на новый цикл
      if (numberReady == 0) continue;
      // берем множество готовых ключей
      Set<SelectionKey> keys = greedy.selectedKeys();
      // берем итератор по этому множеству
      Iterator<SelectionKey> keyIterator = keys.iterator();
      // проходимся по всем ключам
      while (keyIterator.hasNext()) {
        SelectionKey selected = keyIterator.next(); // берем следующий ключ
        // смотрим инвалидный ли ключ
        if (selected == null) continue;
        if (!selected.isValid()) continue;
        // если к серверу пытаются подключиться, то подключаем
        if (selected.isAcceptable()) signup(selected, greedy);
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
    NetBag clientData = new NetBag((ServerSocketChannel) ready2Connect.channel(), fetcher);
    core.notify(this, clientData);
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
    core.notify(this, wrap);
  }
}
