package entities;

import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Местоположение от спутников GPRS,
 * Глонасс и т.п.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushevich
 */
@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Location {
  // координата по оси абсцисс
  @XmlAttribute(name = "x")
  private final long x;

  /**
   * Свойство получения,
   * координаты по оси OX
   * @return целочисленная координата
   */
  public long getX() { return x; }

  // координата по оси ординат
  @XmlAttribute(name = "y")
  @NotNull
  private final Long y;

  /**
   * Свойство получения
   * координаты по оси OY
   * @return ссылка на целое число координатой
   */
  public Long getY() { return y; }

  // координата по оси аппликат
  @XmlAttribute(name = "z")
  private final double z;

  /**
   * Свойство получения,
   * координаты по оси OZ
   * @return действительная координата
   */
  public double getZ() { return z; }

  /**
   * Еще один представитель
   * обезъяноподобных конструкторов
   */
  public Location() { x = 0L; y = Long.MIN_VALUE; z = 0.0; }

  /**
   * Следующее звено эволюции
   * конструкторов с параметрами
   * @param x координата по оси абсцисс
   * @param y координата по оси ординат
   * @param z координата по оси аппликат
   */
  public Location(long x, Long y, double z) {
    this.x = x;
    this.y = (y == null)? Long.MIN_VALUE : y;
    this.z = z;
  }

  // objects methods
  /**
   * Стильная строчка с данными нашими
   * @return строковое представление
   */
  @Override
  public String toString() {
    return "Location[x = " + x + "; y = " + y + "; z = " + z + "]";
  }

  /**
   * Хорстманн был, Хорстманн есть,
   * Хорстманн будет есть. Его
   * реализация этого метода
   * @param other с эти идет сравнение
   * @return результат сравнения
   */
  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (this == other) return true;
    if (!this.getClass().getName().equals(other.getClass().getName())) return false;
    Location locate = (Location) other;
    return x == locate.x && y.equals(locate.y) && z == locate.z;
  }

  /**
   * Чиселка, что
   * объект в число превращает
   * по полиноминальному принципу
   * @return целочисленная константа
   */
  @Override
  public int hashCode() {
    int p = 0x2B;
    return (int) (((x * p) + y) * p + z);
  }
}
