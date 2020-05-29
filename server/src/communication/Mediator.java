package communication;

/**
 * Сам посредник или управленец. Регулирует работу сервера и его модулей,
 * или модулей и их компонентов.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public interface Mediator {
  /**
   * Просто метод из паттерна,
   * организующий работу всех компонентов
   * связной системы
   * @param sender отправитель данных
   * @param data сами данные
   */
  void notify(Component sender, Valuable data);
}
