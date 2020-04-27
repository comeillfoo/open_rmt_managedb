package parsing.customer;

import entities.Mappable;
import parsing.customer.bootstrapper.LoaferLoader;
import parsing.customer.bootstrapper.NakedCrateLoader;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Управленец коллекцией только уже с данными
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @param <K> тип ключа коллекции
 * @param <V> тип элемента коллекции
 * @see Receiver
 * @see LoaferLoader
 */
public abstract class Commander<K, V extends Mappable<K>> implements Receiver<K, V> {
  protected final Map<K, V> elements = new HashMap<>(); // хранимое отображение
  protected String creationDate = ZonedDateTime.now().toString(); // дата создания
  protected LoaferLoader<V> breadLoader; // буханка-загрузчик xml-коллекций
}
