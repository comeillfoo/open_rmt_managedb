package parsing.plants;

import entities.Coordinates;
import entities.Junker;

/**
 * Фабрика координат
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class CoordinatesBuilder implements Factory<Coordinates> {
  /**
   * Метод, создающий экземпляр типа координат
   * @param parts объект сериализации, хранящий лишь неупорядоченные данные
   * @return экземпляр координат
   */
  @Override
  public Coordinates make(Junker parts) {
    if (parts == null) return new Coordinates();
    // обрабатываем целые числа
    long[] digits = parts.Digits();
    int x;
    if ((digits == null) || (digits.length == 0)) return new Coordinates();
    else x = (int) digits[0];
    // обрабатываем действительные числа
    double[] cogits = parts.Cogits();
    Float y;
    if ((cogits == null) || (cogits.length == 0)) return new Coordinates(x);
    y = (float) cogits[0];
    return new Coordinates(x, y);
  }
}
