package communication;

import nook.CIF_ServerController;
import nook.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
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
  public void install(String hostname, int port) throws IOException {
    ServerSocket connection = new ServerSocket();
    connection.bind(new InetSocketAddress(InetAddress.getByName(hostname), port));
    Selector selector = Selector.open();
    ServerSocketChannel serverSocketChannel = connection.getChannel();
    serverSocketChannel.configureBlocking(false);
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    Mediator controller = new CIF_ServerController(selector);
    myLittleBigRunt = controller.getMainServer();
  }

  /**
   * Метод по запуску сервера в отдельном потоке
   */
  public void launch() { if (myLittleBigRunt != null) new Thread(myLittleBigRunt).start(); }

  /**
   * Надстройка над конструктором, чтобы веселее было
   * @return сына маминой подруги, который поставит сервер по майнкрафту
   */
  public static SystemAdmin summon() { return new SystemAdmin(); }
}
