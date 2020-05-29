package entities;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.xml.bind.annotation.*;
import java.time.ZonedDateTime;

/**
 * Основной класс, выполняющий роль элемента коллекции
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Mappable
 */
@XmlRootElement(name = "organization")
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Mappable<Integer> {
  @XmlTransient
  private static int count = 1;
  @XmlAttribute(name = "id")
  private final int id; // generates automatically
  @XmlAttribute(name = "name")
  @NotNull
  private final String name;

  @XmlElement(name = "coordinates")
  @NotNull
  private final Coordinates coordinates;

  /**
   * Геттер для получения координат элемента,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappable)
   * @return координаты организации
   */
  public Coordinates getCoordinates() { return coordinates; }
  @XmlTransient
  @NotNull
  private final ZonedDateTime creationDate = ZonedDateTime.now();

  /**
   * Геттер для получения даты создания элемента,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappable)
   * @return дата создания организации
   */
  public ZonedDateTime getCreationDate() { return creationDate; }

  @XmlAttribute(name = "annual-turnover")
  private final float annualTurnover;

  /**
   * Геттер для получения ежегодной прибыли организации,
   * к сожалению, не может быть отрицательной. Хотелось
   * чтобы и в жизни так было.
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappable)
   * @return String name название организации
   */
  public float getAnnualTurnOver() { return annualTurnover; }

  @XmlElement(name = "fullname")
  @NotNull
  private final String fullname;

  /**
   * Геттер для получения полного имени элемента,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappable)
   * @return полное название организации
   */
  public String getFullname() { return fullname; }

  @XmlAttribute(name = "employees-count")
  private final int employeesCount;

  /**
   * Геттер для получения числа сотрудников
   * организации -- натуральное число;
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappable)
   * @return число сотрудников в организации
   */
  public int getEmployees() { return employeesCount; }

  @XmlElement(name = "organization-type", required = true)
  @Nullable
  private final OrganizationType type;

  /**
   * Геттер для получения типа организации
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappable)
   * @return тип организации
   */
  public OrganizationType getType() { return type; }

  @XmlElement(name = "official-address")
  @Nullable
  private final Address officialAddress;

  /**
   * Геттер для получения адреса организации
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappable)
   * @return адрес организации
   */
  public Address getAddress() { return officialAddress; }

  /**
   * Стадартный конструктор без параметров, создан
   * по требованию библиотеки JAXB
   * @see Organization#Organization(String, Coordinates, float, String, int, OrganizationType, Address)
   */
  public Organization() {
    name  = "Sample Organization";
    coordinates = new Coordinates();
    annualTurnover = 0;
    fullname = "sample organization pattern";
    employeesCount = 0;
    type = OrganizationType.PUBLIC;
    officialAddress = new Address();
    id = Math.abs(hashCode()) + count++;
  }

  /**
   * Основной конструктор с параметрами, используется в фабрике организаций
   * @param name название организации String
   * @param coordinates местоположение организации относительно аддреса Coordinates
   * @param annualTurnover ежегодный оборот организации float
   * @param fullname полное наименование организации String
   * @param employeesCount число сотрудников int
   * @param type тип организации OrganizationType
   * @param officialAddress оффициальный аддресс Address
   */
  public Organization(String name, Coordinates coordinates,
                      float annualTurnover, String fullname,
                      int employeesCount, OrganizationType type, Address officialAddress) {
    // проверили корректность названия
    this.name = (name.isEmpty() || (name == null))? "SampleOrganization" : name;
    // проверили корректность полного названия
    this.fullname = (fullname == null)? "" : fullname;
    // проверка корректности координат
    this.coordinates = (coordinates == null)? new Coordinates() : coordinates;
    // проверка корректности прибыли
    this.annualTurnover = annualTurnover > 0? annualTurnover : Float.MIN_VALUE;
    // проверка корректности числа сотрудников
    this.employeesCount = employeesCount > 0? employeesCount : 1;
    this.type = type;
    this.officialAddress = officialAddress;
    // организуем буферное значение, дабы не было отрицательных идентификаторов
    int buffer = hashCode() + count++;
    id = (buffer == Integer.MIN_VALUE)? --buffer : Math.abs(buffer);
  }

  /**
   * Свойство для получения
   * идентификатора объекта
   * @return идентификатор коллекции
   */
  public int getID() {return id;}

  /**
   * Метод взятия строкового представления
   * @return
   */
  @Override
  public String toString() {
    return "Organization[id: " + id + "; name: " + name + "; coordinates: " + coordinates
            + "; creationDate: " + creationDate + "; annualTurnover: " + annualTurnover
            + "; fullname: " + fullname + "; employeesCount: " + employeesCount
            + "; type: " + type + "; officialAddress: " + officialAddress + "]";
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (this == other) return true;
    if (!this.getClass().getName().equals(other.getClass().getName())) return false;
    Organization another = (Organization) other;
    return (id == another.id)
            && (employeesCount == another.employeesCount)
            && (annualTurnover == another.annualTurnover)
            && (type == another.type)
            && (name.equals(another.name))
            && (fullname.equals(another.fullname))
            && (coordinates.equals(another.coordinates))
            && ((officialAddress == null) ? false : officialAddress.equals(another.officialAddress))
            && (creationDate.isEqual(another.creationDate));
  }

  @Override
  public int hashCode() {
    return (int) (
            name.hashCode() + fullname.hashCode()
                    + (employeesCount + annualTurnover) % 2
                    + (coordinates.hashCode()
                    + ((officialAddress != null)? officialAddress.hashCode() : 0)) % 3
                    + creationDate.hashCode()
                    + ((type == null)? 0 : type.hashCode())
    );
  }

  /**
   * Тот же самый getId только getKey
   * @return id
   */
  @Override
  public Integer Key() {
    return id;
  }

  /**
   * Свойство для получения имени элемента,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappable)
   * @return название организации
   */
  @Override
  public String Name() {
    return name;
  }
}