package parsing.plants;

import communication.Component;
import parsing.Resolver;
import parsing.customer.distro.LimboKeeper;
import parsing.instructions.concrete.ConDecree;
import parsing.instructions.rotten.RawDecree;

/**
 * Псевдо-фабрика сборки из абстрактных комманд
 * команд конкретных с ссылками на обработчик коллекции
 * и уже конкретный экземпляр элемента этой коллекции
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Component
 * @see Resolver
 * @see Factory
 */
public final class InstructionBuilder implements Component {
  protected final Resolver magiV; // ссылка на SSPC
  protected final Factory marduk; // ссылка на производителя элементов

  /**
   * Конструктор устанавливающий
   * ссылку на контроллер и фабрику,
   * формирующую элементы коллекции
   * @param controller контроллер
   * @param facility фабрика
   */
  public InstructionBuilder(Resolver controller, Factory facility) {
    magiV = controller;
    marduk = facility;
  }

  // метод формирования из отправленной команды
  // исполняемой команды
  /**
   * Преобразуем абстрактную команду,
   * присланную от пользователя в
   * конкретную команду с ссылкой на
   * текущий обработчик.
   * В ходе преобразования устанавливается
   * ссылка на обработчик, из параметров
   * добавляемого или сравниваемого объекта
   * формируется конкретный объект
   * @param flesh абстрактная команда
   * @param receiver обработчик коллекции
   * @return конкретная команда
   */
  public ConDecree make(RawDecree flesh, LimboKeeper receiver) {
    return null;
    // TODO: написать реализацию
  }
}
