package parsing;


import communication.Component;
import communication.Mediator;
import communication.wrappers.AlertBag;
import communication.wrappers.QueryBag;
import entities.Organization;
import parsing.customer.bootstrapper.LoaferLoader;
import parsing.customer.distro.LimboKeeper;
import parsing.plants.Factory;
import parsing.plants.InstructionBuilder;
import parsing.supplying.FondleEmulator;
import parsing.supplying.interpreter.Shell;
import systemcore.ServerController;

import java.nio.channels.SocketChannel;

/**
 * Шаблон класса модуля, вытаскивающего пользовательский запрос
 * и делегирующий свою работу по обработке готовым классам.
 */
public abstract class Resolver implements Mediator, Component {
  protected  ServerController CONTROLLER; // контроллер модуля
  protected FondleEmulator kael; // сутенер комманд
  protected LimboKeeper fate; // сутенер коллекции
  protected InstructionBuilder wizard; // фабрика вызываемых комманд
  protected Factory plant; // фабрика элементов коллекции
  protected LoaferLoader<Organization> breadLoader; // загрузчик коллекции
  protected SocketChannel client;

  // основной метод обработки клиентского запроса
  /**
   * Основной метод обработки запросов
   * клиента. Клиентский пакет содержит
   * данные о клиенте (имя, адрес, порт, НАЗВАНИЕ
   * ПЕРЕМЕННОЙ ОКРУЖЕНИЯ), канал клиента (дабы
   * послать модулю отправки ответов)
   * @param query пакет с клиентским запросом
   */
  public abstract void parse(QueryBag query);

  // конструктор по умолчанию
  // в терминах лабы
  public Resolver() {}
  /**
   * Конструктор, устанавливающий хозяина
   * @param controller контроллер подсистемы
   */
  public Resolver(ServerController controller) { CONTROLLER = controller; }

  public SocketChannel ClientChannel() { return client; }

  public void ImmediateStop(AlertBag alert) {
    AlertBag clientAlert = new AlertBag(client, alert.Notify());
    CONTROLLER.ImmediateStop(clientAlert);
  }
}
