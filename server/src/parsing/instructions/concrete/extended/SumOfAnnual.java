package parsing.instructions.concrete.extended;

import communication.Report;
import entities.Mappable;
import parsing.customer.Receiver;
import parsing.instructions.concrete.ConDecree;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Команда, находящая сумму по указанному полю
 */
public class SumOfAnnual<K, V extends Mappable<K>, R extends Number> extends ConDecree {
  protected final Function<? super V,? extends R> keySummator;
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией и геттер,
   * что достает суммируемые поля
   * @param sieve текущий управленец коллекцией
   */
  public SumOfAnnual(Receiver sieve, Function<? super V,? extends R> field) {
    super(sieve);
    keySummator = field;
  }

  /**
   * Метод, что исполняет данную команду, т.е.
   * находит сумму полей элементов коллекции
   */
  @Override
  public Report execute() {
    Map<K, R> buffer = sieve.getBy(keySummator);
    R sum = (R) buffer
        .values()
        .stream()
        .collect(Collectors.summingDouble(R::doubleValue));
    return new Report(0, "Find sum: " + sum.toString());
  }
}
