package systemcore;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Системный администратор, нужен для того, чтоюы упростить настройку сервера и всей системы
 * до минимума. Данный индивид устанавливает контроллер сервера, состоящий из:
 * <ul>
 *   <li>самого сервера</li>
 *   <li>модуля чтения запросов</li>
 *   <li>модуля обработки запросов</li>
 *   <li>модуля отправки результатов обработки запросов</li>
 * </ul>
 */
public final class SystemAdmin {
  private Server myLittleBigRunt; // я не знал как правильно перевести мой п*здюк
  // TODO: добавить логгер
  /**
   * Порядок действий по настройке системы:
   * <ol>
   *   <li>1 Создать сокет сервера</li>
   *   <li>2 Привязать сокет к определенному хосту и порту</li>
   *   <li>3 Открыть селектор</li>
   *   <li>4 Получить серверный канал из его сокета</li>
   *   <li>5 Поставить канал в неблокирующий режим</li>
   *   <li>6 Зарегистрировать канал в селектор с приоритетом на принятие клиентов</li>
   *   <li>7 Создать и сконфигурировать контроллер сервера</li>
   *   <li>8 Получить от контроллера, сконфигурированный сервер</li>
   * </ol>
   */
  public void install(String hostname, int port) {

    ServerSocketChannel firstChannel = null; // создание серверного канала
    // попытка открыть канал
    try { firstChannel = ServerSocketChannel.open(); } catch (IOException e) {
      // TODO: залогировать ошибку
      System.exit(1);
    }

    // попытка связать открытый канал с нужным адресом
    try { firstChannel.bind(new InetSocketAddress(InetAddress.getByName(hostname), port)); } catch (UnknownHostException outer) {
      // TODO: логировать, что не найден хост
      try { firstChannel.close(); } catch (IOException inner) { // TODO: логировать ошибку закрытия
      } finally { System.exit(1); }
      // в любом случае не забываем завершить приложение
    } catch (IOException outer) {
      // TODO: логировать
      // попытка закрыть испорченный канал
      try { firstChannel.close(); } catch (IOException inner) { // TODO: логировать ошибку закрытия
      } finally { System.exit(1); }
      // в любом случае не забываем завершить приложение
    }

    Selector greedy = null; // создание селектора
    // попытка открыть селектор
    try { greedy = Selector.open(); }
    catch (IOException outer) {
      // TODO: логировать ошибку
      try { firstChannel.close(); } catch (IOException inner) { // TODO: логировать ошибку закрытия
      } finally { System.exit(1); }
      // в любом случае не забываем завершить приложение
    }
    
    // попытка сконфигурировать канал
    try { firstChannel.configureBlocking(false); } catch (IOException outer) {
      // TODO: логировать ошибку конфигурации
      try {
        firstChannel.close();
        greedy.close(); } catch (IOException inner) { // TODO: логировать ошибку закрытия
      } finally { System.exit(1); }
      // в любом случае не забываем завершить приложение
    }

    // попытка зарегистрировать канал в селектор
    try { firstChannel.register(greedy, SelectionKey.OP_ACCEPT); }
    catch (ClosedChannelException e) {
      // TODO: логировать ошибку
      try {
        firstChannel.close();
        greedy.close(); } catch (IOException inner) { // TODO: логировать ошибку закрытия
      } finally { System.exit(1); }
      // в любом случае не забываем завершить приложение
    }

    // передаем и селектор и канал, так как их нужно будет закрывать
    ServerController core = new ServerController(greedy, firstChannel);
    // получаем ссылку на собранный сервер
    myLittleBigRunt = core.whereServer();
    System.out.println("----Server installed----"); // TODO: залогировать успешность установки сервера
  }

  /**
   * Метод по запуску установленного
   * сервера в отдельном потоке
   */
  public void launch() {
    if (myLittleBigRunt != null) new Thread(myLittleBigRunt).start();
    else {
      // TODO: логировать ошибку
      System.exit(1);
      // закрываем приложение
    }
  }

  /**
   * Надстройка над конструктором, чтобы веселее было
   * @return сына маминой подруги, который поставит сервер по майнкрафту
   */
  public static SystemAdmin summon() { return new SystemAdmin(); }
}
