package instructions.concrete.extended;

import communication.Report;
import entities.Mappable;
import parsing.customer.Receiver;
import instructions.concrete.ConDecree;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Обобщенная команда, отфильтровывающая информацию
 * о каждом элементе коллекции по нужному полю,
 * равному утсановленному пользователем
 * @param <K> ключ элементов коллекции
 * @param <V> тип элементов колллекции
 * @param <R> тип поле, по которому происходит фильтрование
 */
public class FilterContains<K, V extends Mappable<K>, R> extends ConDecree {
  protected final Function<? super V, ? extends R> fertilizer;
  protected final R filtered;
  /**
   * Обычный конструктор, связывающий
   * команду с исполнителем и геттером,
   * которым достаем ЗНАЧЕНИЕ поля.
   * @param sieve ссылка на ресивер
   * @param filter поле, которое ищем
   * @param field функция, достающая значение
   */
  public FilterContains(Receiver<K, V> sieve, R filter, Function<V, R> field) {
    super(sieve);
    fertilizer = field;
    filtered = filter;
  }

  /**
   * Метод исполняющий команду, реализованный
   * из общего интерфейса комманд
   * @return результат исполнения команды
   */
  @Override
  public Report execute() {
    // коллекция, что содержит соответствие между ключом и полем элемента
    Map<K, R> buffer = SIEVE.getBy(fertilizer); // возможно придется добавить ClassCast
    // коллекция, что содержит соответствие между ключом и действительным ключом
    Map<K, K> realkeys = ((Receiver<K, V>) SIEVE).getBy(V::Key); // костыль
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
    String result = ((Receiver<K, V>) SIEVE).survey((element)->(realkeys.containsValue(element.Key())));
    return new Report(0, result);
  }

  @Override
  public String toString() { return NAME; }
}
