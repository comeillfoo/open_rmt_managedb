package parsing.plants;

import entities.Junker;
import entities.Location;

/**
 *
 */
public final class LocationBuilder implements Factory<Location> {
  /**
   * Метод, создающий экземпляр типа T
   *
   * @param parts объект сериализации, хранящий лишь неупорядоченные данные
   * @return объект типа T, где T - тип производимой продукции
   */
  @Override
  public Location make(Junker parts) {
    return null;
  }
}
