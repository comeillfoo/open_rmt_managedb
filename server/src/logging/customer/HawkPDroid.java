package logging.customer;

import communication.Component;
import parsing.Resolver;

/**
 * Логгер, умеющий работать с контроллером
 * исполнения запросов.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public abstract class HawkPDroid implements ReceiverLogger, Component {
  protected final Resolver magiV; // ссылка на SSPC
  protected HawkPDroid(Resolver m) { magiV = m; }
}
