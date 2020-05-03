package parsing.instructions.concrete.extended;

import communication.Report;
import entities.Mappable;
import parsing.customer.Indicator;
import parsing.customer.Receiver;
import parsing.instructions.concrete.ConDecree;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 */
public class FilterContains<K, V extends Mappable<K>, R> extends ConDecree {
  protected final Function<? super V, ? extends R> fertilizer;
  protected final R filtered;
  /**
   *
   * @param sieve
   * @param field
   */
  public FilterContains(Receiver sieve, R filter, Function field) {
    super(sieve);
    fertilizer = field;
    filtered = filter;
  }

  /**
   *
   */
  @Override
  public Report execute() {
    // коллекция, что содержит соответствие между ключом и полем элемента
    Map<K, R> buffer = sieve.getBy(fertilizer); // возможно придется добавить ClassCast
    // коллекция, что содержит соответствие между ключом и действительным ключом
    Map<K, K> realkeys = ((Receiver<K, V>) sieve).getBy(V::Key); // костыль
    // коллекция, которая содержит нужные ключи, равные полю, которое ищем
    Map<K, R> correct = new HashMap<>();
    // заполнили предыдущую коллекцию
    buffer
        .entrySet()
        .stream()
        .filter((Map.Entry<K, R> enter) -> (enter.getValue().equals(filtered)))
        .forEach((enter)->
        {
          correct.put(enter.getKey(), enter.getValue());
        });
    // массив ключей элементов, что содержат нужное поле
    Map.Entry<K, K>[] keys = (Map.Entry<K, K>[]) realkeys
        .entrySet()
        .stream()
        .filter((Map.Entry<K, K> enter) -> (correct.containsKey(enter.getKey()))).toArray();
    // очистили, чтобы заполнить
    realkeys.clear();
    // заполняем пустую коллекцию из фильтрованного массива
    for (Map.Entry<K, K> e : keys)
      realkeys.put(e.getKey(), e.getValue());
    // получаем описание тех элементов, чьи реальные ключи содержатся в фильтрованной коллекции
    String result = ((Receiver<K, V>) sieve).survey((element)->(realkeys.containsValue(element.Key())));
    return new Report(0, result);
  }
}
