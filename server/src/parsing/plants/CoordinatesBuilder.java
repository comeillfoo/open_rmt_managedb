package parsing.plants;

import entities.Coordinates;
import entities.Junker;

public final class CoordinatesBuilder implements Factory<Coordinates> {
  /**
   * Метод, создающий экземпляр типа T
   *
   * @param parts объект сериализации, хранящий лишь неупорядоченные данные
   * @return объект типа T, где T - тип производимой продукции
   */
  @Override
  public Coordinates make(Junker parts) {
    return null;
  }
}
