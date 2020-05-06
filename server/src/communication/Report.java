package communication;

import java.io.Serializable;

/**
 * Класс отчетов, хранящих информацию о результатах,
 * работы сервера.
 * Может использоваться для:
 * <ul>
 *   <li>Хранения результатов обработки запросов</li>
 *   <li>Хранения результатов чтения запросов</li>
 *   <li>Хранения результатов попытки отправки результатов обработки запросов</li>
 * </ul>
 */
public final class Report implements Serializable {
  // fields
  private final int errorCode;
  private final String message;
  // builders
  public Report(int errorCode, String message) {
    this.errorCode = errorCode;
    this.message = (message == null)? "" : message;
  }
  // properties and methods
  /**
   * Свойство, определяющее успешность
   * выполнения операции
   * @return успешно/неуспешно
   */
  public boolean isSuccessful() { return errorCode == 0; }

  /**
   * Метод взятия сообщения
   * @return строка с сообщением
   */
  public String account() { return message; }
}
