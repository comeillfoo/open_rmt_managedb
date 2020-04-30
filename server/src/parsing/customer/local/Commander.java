package parsing.customer.local;

import entities.Mappable;
import logging.customer.ReceiverLogger;
import parsing.customer.Receiver;
import parsing.customer.bootstrapper.LoaferLoader;
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
  protected final Map<K, V> database = new HashMap<>(); // хранимое отображение
  protected String creationDate = ZonedDateTime.now().toString(); // дата создания
  protected final LoaferLoader<V> breadLoader; // буханка-загрузчик xml-коллекций
  protected final ReceiverLogger whistleblower; // ссылка на логгер
  public Commander(LoaferLoader<V> loader, ReceiverLogger logger) {
    breadLoader = loader;
    whistleblower = logger;
  }
}
