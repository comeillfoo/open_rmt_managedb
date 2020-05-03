package entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Бесплатное дополнение, хранящее список элементов.
 * Требуется для корректного парсинга XML файла с помощью JAXB -
 * пожалуй, "одна из лучших библиотек XML".
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @see Mappable
 * @see Organization
 * @see OrganizationType
 * @see Location
 * @see Coordinates
 * @see Address
 */
@XmlRootElement(name = "organizations")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Organizations {
  @XmlElement(name = "organization")
  private List<Organization> companies;

  /**
   * Простой конструктор без параметров
   * Создаёт по умолчанию ArrayList
   */
  public Organizations() { companies = new ArrayList<>(); }

  /**
   * Основной конструктор сохраняющий список организаций
   * @param companies List&lt;Organization&gt; список организаций
   */
  public Organizations(List<Organization> companies) { this.companies = companies; }

  /**
   * Дополнительный конструктор для перевода отображения в список
   * @param companies Map&lt;Integer, Organization&gt; коллекции организаций
   * с целочисленным ключом
   */
  public Organizations(Map<Integer, Organization> companies) { this.companies = new ArrayList<>(companies.values()); }

  /**
   * Геттер для получения ссылки на список организаций,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * @return List&lt;Organization&gt; ссылка на список организаций
   */
  public final List<Organization> getCompanies() { return companies; }
}
