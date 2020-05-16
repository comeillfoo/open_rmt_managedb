package parsing.plants;

import entities.Address;
import entities.Junker;
import entities.Location;

/**
 * Фабрика адресов
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class AddressBuilder implements Factory<Address> {
  private final Factory<Location> locateBuilder = new LocationBuilder();
  /**
   * Метод, создающий экземпляр адреса
   * @param parts объект сериализации, хранящий лишь неупорядоченные данные
   * @return тип продукции — адрес
   */
  @Override
  public Address make(Junker parts) {
    if (parts == null) return null;
    Junker[] guts = parts.Guts();
    Location locate;
    if ((guts == null) || (guts.length == 0))
      locate = null;
    else locate = locateBuilder.make(guts[0]);
    String[] lines = parts.Lines();
    String zipCode;
    if ((lines == null) || (lines.length == 0))
      zipCode = "";
    else if (lines[0] == null)
      zipCode = "";
    else zipCode = lines[0];
    return new Address(zipCode, locate);
  }
}
