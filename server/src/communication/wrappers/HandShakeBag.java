package communication.wrappers;

import communication.Valuable;

import java.io.Serializable;

/**
 * !!!Не используется
 * Приветственный пакет с именем
 * клиента и названием его переменной окружения
 * обязательно должен быть отправлен клиентом
 * при попытке подключения
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class HandShakeBag implements Valuable, Serializable {
  private final String CLIENT_NAME; // имя клиента
  private final String VAR_NAME; // название его переменной окружения

  /**
   * Конструктор, устанавливающий
   * основные параметры этого пакета
   * @param client_name имя клиента
   * @param var_name название переменной окружения
   */
  public HandShakeBag(String client_name, String var_name) {
    CLIENT_NAME = client_name == null? "" : client_name;
    VAR_NAME = var_name;
  }

  /**
   * Свойство взятия имени клиента
   * @return имя клиента
   */
  public String Name() { return CLIENT_NAME; }

  /**
   * Свойство взятия название
   * переменной окружения клиента
   * @return название переменной окружения
   */
  public String $VAR() { return VAR_NAME; }
}
