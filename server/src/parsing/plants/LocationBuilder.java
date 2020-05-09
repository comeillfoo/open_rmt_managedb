package parsing.plants;

import entities.Junker;
import entities.Location;

/**
 * Фабрика местоположений
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class LocationBuilder implements Factory<Location> {
  /**
   * Метод, создающий экземпляры местоположений
   * @param parts объект сериализации, хранящий лишь неупорядоченные данные
   * @return местоположение - продукция фабрики
   */
  @Override
  public Location make(Junker parts) {
    if (parts == null) return null;
    // формируем действительные параметры
    double[] cogits = parts.Cogits();
    double z;
    if ((cogits == null) || (cogits.length == 0))
      z = Double.MIN_VALUE;
    else z = cogits[0];
    long[] digits = parts.Digits();
    int x; Long y;
    if ((digits == null) || (digits.length == 0)) {
      x = 0;
      y = Long.MIN_VALUE;
    } else if (digits.length == 1) {
      y = digits[1];
      x = 0;
    } else  {
      x = (int) digits[0];
      y = digits[1];
    }
    return new Location(x, y, z);
  }
}
