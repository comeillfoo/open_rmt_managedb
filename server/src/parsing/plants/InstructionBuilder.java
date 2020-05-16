package parsing.plants;

import communication.Component;
import communication.wrappers.ExecuteBag;
import communication.wrappers.QueryBag;
import entities.Organization;
import instructions.rotten.IJunked;
import instructions.rotten.RawCommitter;
import instructions.rotten.RawDecree;
import parsing.Resolver;
import parsing.customer.distro.LimboKeeper;
import parsing.plants.pickers.CommittersBuilder;
import parsing.plants.pickers.JustCommandBuilder;
import parsing.supplying.interpreter.Shell;

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
  protected final Resolver MAGIV; // ссылка на SSPC
  protected final Factory<Organization> MARDUK; // ссылка на производителя элементов
  protected final CommittersBuilder HOMIE = new CommittersBuilder();
  protected final JustCommandBuilder JUSTIFY = new JustCommandBuilder();
  /**
   * Конструктор устанавливающий
   * ссылку на контроллер и фабрику,
   * формирующую элементы коллекции
   * @param controller контроллер
   * @param facility фабрика
   */
  public InstructionBuilder(Resolver controller, Factory<Organization> facility) {
    MAGIV = controller;
    MARDUK = facility;
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
   */
  public void make(QueryBag flesh, LimboKeeper receiver) {
    RawDecree command = flesh.Query();
    if (command instanceof IJunked)
      MAGIV.notify(this, new ExecuteBag(flesh.Channel(), HOMIE.make((RawCommitter) command, receiver, MARDUK)));
    else MAGIV.notify(this, new ExecuteBag(flesh.Channel(), JUSTIFY.make(command, receiver)));
  }


}
