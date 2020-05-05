package parsing.plants;

import communication.Component;
import entities.Junker;
import entities.Organization;
import parsing.Resolver;

public final class OrganizationBuilder implements Factory<Organization>, Component {
  private final Resolver magiV; // ссылка на SSPC

  public OrganizationBuilder(Resolver controller) {
    magiV = controller;
  }
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
