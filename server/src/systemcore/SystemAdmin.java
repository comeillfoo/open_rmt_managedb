package systemcore;

import communication.Component;
import communication.Mediator;
import communication.Valuable;
import czerkaloggers.HawkPDroid;
import czerkaloggers.systemcore.C7_E3_GE3;

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
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class SystemAdmin implements Mediator {
  private final String NAME;
  private Server myLittleBigRunt; // я не знал как правильно перевести мой п*здюк
  private final HawkPDroid<SystemAdmin> TONGUE;

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
   * @param hostname сервера
   * @param port настройки сервера
   */
  public void install(String hostname, int port) {

    ServerSocketChannel firstChannel = null; // создание серверного канала
    // попытка открыть канал
    try { firstChannel = ServerSocketChannel.open(); } catch (IOException e) {
      TONGUE.logboard(1, NAME + ": у меня не получилось поднять сервер");
      System.exit(1);
    }
    // попытка связать открытый канал с нужным адресом
    try { firstChannel.bind(new InetSocketAddress(hostname, port)); } catch (UnknownHostException outer) {
      TONGUE.logboard(1, NAME + ": у меня не получилось найти хост, помогите");
      try { firstChannel.close(); } catch (IOException inner) {
        TONGUE.logboard(1, NAME + ": у меня не получилось закрыть каналы");
      } finally { System.exit(1); }
      // в любом случае не забываем завершить приложение
    } catch (IOException outer) {
      TONGUE.logboard(1, NAME + ": у меня не получилось связать канал с хостом и портом");
      // попытка закрыть испорченный канал
      try { firstChannel.close(); } catch (IOException inner) {
        TONGUE.logboard(1, NAME + ": у меня не получилось закрыть каналы");
      } finally { System.exit(1); }
      // в любом случае не забываем завершить приложение
    }

    Selector greedy = null; // создание селектора
    // попытка открыть селектор
    try { greedy = Selector.open(); }
    catch (IOException outer) {
      TONGUE.logboard(1, NAME + ": у меня не получилось открыть селектор");
      try { firstChannel.close(); } catch (IOException inner) {
        TONGUE.logboard(1, NAME + ": у меня не получилось закрыть каналы");
      } finally { System.exit(1); }
      // в любом случае не забываем завершить приложение
    }
    
    // попытка сконфигурировать канал
    try { firstChannel.configureBlocking(false); } catch (IOException outer) {
      TONGUE.logboard(1, NAME + ": у меня не получилось настроить серверный канал");
      try {
        firstChannel.close();
        greedy.close(); } catch (IOException inner) {
        TONGUE.logboard(1, NAME + ": у меня не получилось закрыть каналы");
      } finally { System.exit(1); }
      // в любом случае не забываем завершить приложение
    }

    // попытка зарегистрировать канал в селектор
    try { firstChannel.register(greedy, SelectionKey.OP_ACCEPT); }
    catch (ClosedChannelException e) {
      TONGUE.logboard(0, NAME + ": Упс...канал сервера закрылся");
      try {
        firstChannel.close();
        greedy.close(); } catch (IOException inner) {
        TONGUE.logboard(1, NAME + ": у меня не получилось закрыть каналы");
      } finally { System.exit(1); }
      // в любом случае не забываем завершить приложение
    }

    // передаем и селектор и канал, так как их нужно будет закрывать
    ServerController core = new ServerController(greedy, firstChannel);
    // получаем ссылку на собранный сервер
    myLittleBigRunt = core.whereServer();
    TONGUE.logboard(0, NAME + ": подняль сервер");
  }

  /**
   * Метод по запуску установленного
   * сервера в отдельном потоке
   */
  public void launch() {
    if (myLittleBigRunt != null) new Thread(myLittleBigRunt).start();
    else {
      TONGUE.logboard(1, NAME + ": у меня не получилось запустить это");
      System.exit(1);
      // закрываем приложение
    }
  }

  /**
   * Надстройка над конструктором, чтобы веселее было
   * @param name имя сисадмина
   * @return сына маминой подруги, который поставит сервер по майнкрафту
   */
  public static SystemAdmin summon(String name) { return new SystemAdmin(name); }

  public SystemAdmin(String name) {
    NAME = name;
    TONGUE = (HawkPDroid<SystemAdmin>) C7_E3_GE3.assemble(this, C7_E3_GE3::new);
  }

  @Override
  public void notify(Component sender, Valuable data) {  }
}
