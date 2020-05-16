package parsing.customer.distro;

import entities.Organization;
import czerkaloggers.HawkPDroid;
import parsing.customer.bootstrapper.LoaferLoader;

/**
 * Комсомолка, спортсменка и просто сама леди Судьба, что
 * вершит судьбой БД и рассылает своих прихвостней, дабы
 * те дергали логгер, клиента и кота за причинное место.
 * Название является синонимичным переводом сервиса DropBox
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @see LimboKeeper
 * @see parsing.customer.local.TotalCommander
 * @see parsing.customer.local.Commander
 * @see parsing.customer.Receiver
 */
public final class ShedBlock extends LimboKeeper {
  /**
   * Конструктор, принимающий любой хороший загрузчик и
   * Логгер, который умеет не только составлять протокол,
   * но и отправлять его модулю разбора запросов клиента
   * @param logger логгер, взаимодействующий с контроллером
   */
  public ShedBlock(LoaferLoader<Organization> loader, HawkPDroid logger) {
    super(loader, logger);
  }
}
