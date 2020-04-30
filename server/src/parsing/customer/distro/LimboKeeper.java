package parsing.customer.distro;

import entities.Organization;
import logging.customer.HawkPDroid;
import parsing.customer.local.Commander;
import parsing.customer.Receiver;
import parsing.customer.bootstrapper.LoaferLoader;
import parsing.customer.local.TotalCommander;

/**
 * Прокаченный {@link TotalCommander}, предназначенный для работы по сети,
 * а именно с контроллером исполнения запросов.
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @see TotalCommander
 * @see Commander
 * @see Receiver
 */
public abstract class LimboKeeper extends TotalCommander {
  /**
   * Конструктор, принимающий любой хороший загрузчик и
   * Логгер, который умеет не только составлять протокол,
   * но и отправлять его модулю разбора запросов клиента
   * @param loader загрузчик коллекции
   * @param logger логгер, взаимодействующий с контроллером
   */
  protected LimboKeeper(LoaferLoader<Organization> loader, HawkPDroid logger) { super(loader, logger); }
}
