package instructions.concrete.extended;

import entities.Organization;
import parsing.customer.Receiver;

/**
 * Команда суммирования по
 * полю ежегодной прибыли корпораций
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class SumOfAnnualTurnover extends SumOfAnnual<Integer, Organization, Float> {
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией и геттер,
   * что достает суммируемые поля
   * @param sieve текущий управленец коллекцией
   */
  public SumOfAnnualTurnover(Receiver sieve) {
    super(sieve, Organization::getAnnualTurnover);
  }
}
