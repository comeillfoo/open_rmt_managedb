package entities;

import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Элемент, содержащий координаты
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Coordinates {
  // координата по оси абсцисс
  @XmlAttribute(name = "x")
  private final int x;

  /**
   * Координата по оси абсцисс
   * @return целочисленная координата по оси OX
   */
  public int getX() { return x; }

  // координата по оси ординат
  @XmlAttribute(name = "y")
  @NotNull
  private final Float y;

  /**
   * Свойство получения
   * координаты по оси ординат.
   * Должна быть больше -538
   * @return действительное значение координаты по оси OY
   */
  public Float getY() { return y; }

  /**
   * Конструктор неандерталец
   * без параметров
   */
  public Coordinates() {
    x = 0;
    y = -538f + Float.MIN_VALUE;
  }

  /**
   * Конструкторский кроманьонец
   * с параметрами и проверками
   * @param x целочисленная координата по оси OX
   * @param y действительная координта по оси OY
   */
  public Coordinates(int x, Float y) {
    this.x = x;
    this.y = (y == null)? -538 + Float.MIN_VALUE : ((y < -538f + Float.MIN_VALUE)? -538f + Float.MIN_VALUE : y);
  }

  /**
   * Конструкторы с разделенным
   * параметрами
   * @param x координата по оси OX
   */
  public Coordinates(int x) { this(x, null); }

  /**
   * Конструктор с разделенными
   * параметрами
   * @param y координата по оси OY
   */
  public Coordinates(Float y) { this(0, y); }

  // objects methods
  /**
   * Хочу видеть как строчку
   * @return строковое представление
   */
  @Override
  public String toString() {
    return "Coordinates[x = " + x + "; y = " + y + "]";
  }

  /**
   * Хорстманн всегда с тобой
   * его реализация иквауэлз здесь
   * @param other вот с этим сравниваем, не перепутай
   * @return результат сравнения
   */
  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (this == other) return true;
    if (!this.getClass().getName().equals(other.getClass().getName())) return false;
    Coordinates cord = (Coordinates) other;
    return x == cord.x && y.equals(cord.y);
  }

  /**
   * Как же чиселку хочется
   * @return числовое представление объекта
   */
  @Override
  public int hashCode() {
    int p = 0x6F;
    return (int)((x * p) + y);
  }

}
