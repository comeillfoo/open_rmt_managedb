package parsing.plants;

import entities.Junker;
import entities.Organization;

public final class OrganizationBuilder implements Factory<Organization> {
  /**
   * Метод, создающий экземпляр типа T
   *
   * @param parts объект сериализации, хранящий лишь неупорядоченные данные
   * @return объект типа T, где T - тип производимой продукции
   */
  @Override
  public Organization make(Junker parts) {
    return null;
  }
}
