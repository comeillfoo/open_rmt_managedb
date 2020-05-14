package parsing.supplying;

import communication.wrappers.ExecuteBag;
import instructions.concrete.ConDecree;

/**
 * Интерфейс Invoker — часть паттерна "Команда", который
 * отвечает за вызов всех доступных команд по имени;
 * эмулирует деятельность клиента
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public interface Invoker {
  /**
   * Метод записи комманды в
   * список обслуживаемых комманд
   * @param commandName название команды
   */
  void signup(ConDecree command);
  // TODO: возможно придется добавить еще параметры
  
  /**
   * Метод вызова команды
   * @param execution присланная команда
   */
  void invoke(ExecuteBag execution);
}
