package parsing.supplying;

/**
 * Интерфейс Invoker — часть паттерна "Команда", который
 * отвечает за вызов всех доступных команд по имени;
 * эмулирует деятельность клиента
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public interface Invoker {
  void signup(String commandName);
}
