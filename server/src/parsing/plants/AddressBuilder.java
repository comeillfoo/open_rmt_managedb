package parsing.plants;

import entities.Address;
import entities.Junker;

public final class AddressBuilder implements Factory<Address> {
  /**
   * Метод, создающий экземпляр типа T
   *
   * @param parts объект сериализации, хранящий лишь неупорядоченные данные
   * @return объект типа T, где T - тип производимой продукции
   */
  @Override
  public Address make(Junker parts) {
    return null;
  }
}
