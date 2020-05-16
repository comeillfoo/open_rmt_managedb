package entities;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.xml.bind.annotation.*;

/**
 * Просто адрес, просто как дела
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Location
 */
@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Address {
  // почтовый индекс
  @XmlAttribute(name = "zipcode")
  @NotNull
  private final String zipCode;

  /**
   * Свойство получения почтового
   * индекса адреса
   * @return строка почтового индекса
   */
  public String ZipCode() { return zipCode; }

  // местоположение города
  @XmlElement(name = "town")
  @Nullable
  private final Location town;

  /**
   * Свойство получения местоположения
   * райцентра местности
   * @return местоположение ближайшего райцентра
   */
  public Location Town() { return town; }

  /**
   * По принимаемым параметрам
   * це питекантроп, устанавливает
   * все в значения по умолчанию
   */
  public Address() {
    zipCode = "";
    town = null;
  }

  /**
   * Улучшенный конструктор с параметрами
   * проверками, только блэкджэка не хватает,
   * и девушек с низкой социальной ответственностью
   * @param zipCode почтовый индекс нашего Вегаса
   * @param town местоположение нашего Мухосранска
   */
  public Address(String zipCode, Location town) {
    this.zipCode = zipCode == null? "" : zipCode;
    this.town = town;
  }

  // objects methods
  /**
   * Нужно ведь выводит адрес этих
   * засранцев
   * @return строковое представление адреса
   */
  @Override
  public String toString() {
    return "Address[zipCode: " + zipCode + "; town: " + town + "]";
  }

  /**
   * Хорстманновское переопределение
   * метода ИквалЗ
   * @param other это мы проверяем на равенство
   * @return признак равенства
   */
  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (this == other) return true;
    if (!this.getClass().getName().equals(other.getClass().getName())) return false;
    Address address = (Address) other;
    return zipCode.equals(address.zipCode) && town.equals(address.town);
  }

  /**
   * Составляем целочисленное значение
   * по полиноминальной формуле
   * @return целое число, типа коды
   */
  @Override
  public int hashCode() {
    int p = 0x1F;
    return zipCode.hashCode() * p + ((town == null)? 0 : town.hashCode());
  }
}
