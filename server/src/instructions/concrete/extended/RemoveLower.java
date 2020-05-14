package instructions.concrete.extended;

import communication.Report;
import entities.Organization;
import parsing.customer.Receiver;

public class RemoveLower extends RemoveThan {
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией, а также объект
   * добавляемого элемента
   *
   * @param sieve текущий управленец коллекцией
   * @param added добавляемый элемент
   */
  public RemoveLower(Receiver sieve, Organization added) {
    super(sieve, added);
  }

  /**
   * Общий метод исполнения для всех исполняемых комманд
   */
  @Override
  public Report execute() {
    return null;
  }
}
