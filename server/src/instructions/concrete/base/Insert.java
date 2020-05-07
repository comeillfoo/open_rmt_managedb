package instructions.concrete.base;

import communication.Report;
import entities.Organization;
import parsing.customer.Receiver;

/**
 * Класс команды вставки кастомного
 * элемента в коллекцию по заданному ключу
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Committer
 * @see ConDecree
 * @see parsing.instructions.Decree
 * @see Command
 */
public final class Insert extends Committer {
  protected final Integer key;
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией, добавляемый элемент
   * и ключ, по которому нужно добавить элемент
   * @param sieve текущий управленец коллекцией
   * @param key ключ добавляемого элемента
   * @param added добавляемый элемент
   */
  public Insert(Receiver sieve, Integer key, Organization added) {
    super(sieve, added);
    this.key = key;
  }

  /**
   * Магия, что позволит троллям
   * восполнить запасы элементов коллекции
   */
  @Override
  public Report execute() {
    if (sieve == null)
      return new Report(1, "Ссылка на коллекцию не была обнаружена, пожалуйста, свяжитесь со своим системным администратором");
    if (key == null)
      return new Report(1, "Неправильный формат ключа добавляемого элемента");
    if (embedded == null)
      return new Report(1, "Обнаружена попытка добавить неопределенный элемент");
    Organization buffer = null;
    Receiver<Integer, Organization> realSiever = (Receiver<Integer, Organization>)sieve;
    realSiever.search(key, buffer, (org)->(true));
    if (buffer != null)
      return new Report(3, "Обнаружена попытка добавить элемент по уже существующему ключу");
    buffer = embedded;
    realSiever.add(key, embedded, (org)->(true));
    if (buffer.equals(embedded))
      return new Report(0, "Элемент успешно добавлен");
    else return new Report(0xCCCF, "Возникли ошибки при попытки добавления элемента");
  }
  public static final String NAME = "insert";
  public static final String BRIEF = "Добавляет элемент с указанным [key] в колекцию.";
  public static final String SYNTAX = NAME + " [key] {element}";
  public static final int ARGNUM = 2;
}
