package entities;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.xml.bind.annotation.*;
import java.time.ZonedDateTime;

// TODO: (*желательно) добавить описание полям на SQL
/**
 * Основной класс, выполняющий роль элемента коллекции
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Mappable
 */

// TODO: (*желательно) добавить описание полям на SQL
/**
 * Основной класс, выполняющий роль элемента коллекции
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Mappable
 */
@XmlRootElement(name = "organization") // установка имени тега
@XmlAccessorType(XmlAccessType.FIELD) // установить доступ по полям
public final class Organization implements Mappable<Integer> {
  // счетчик созданных объектов
  // используется при вычислении ключа
  @XmlTransient
  public static int counter = 0;

  // идентификатор элемента, уникальный
  // инициализируется автоматически
  @XmlAttribute(name = "id")
  private final int id;
  /**
   * Свойство из интерфейса, для получения
   * вычисленного ключа элемента
   * @return вычисленный ключ элемента
   */
  @Override
  public final Integer Key() { return id; }

  // название элемента
  @XmlAttribute(name = "name")
  @NotNull
  private final String name;
  /**
   * Свойство из интерфейса, для получения
   * названия элемента.
   * @return название организации
   */
  @Override
  public final String Name() { return name; }

  // координаты организации
  @XmlElement(name = "coordinates")
  @NotNull
  private final Coordinates coordinates;
  /**
   * Свойство получения координат организации,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * @return координаты организации
   */
  public Coordinates getCoordinates() { return coordinates; }

  // дата создания элемента
  // не сериализуется
  @XmlTransient
  @NotNull
  private final ZonedDateTime creationDate = ZonedDateTime.now();
  /**
   * Свойство получения даты создания организации,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * @return дата создания организации
   */
  public ZonedDateTime getCreationDate() { return creationDate; }

  // ежегодная прибыль
  @XmlAttribute(name = "annual-turnover")
  private final float annualTurnover;
  /**
   * Свойство получения ежегодной прибыли
   * организации, нужен для корректной работы
   * библиотеки сериализации JAXB
   * @return ежегодная прибыль
   */
  public float getAnnualTurnover() { return annualTurnover; }

  // полное название организации
  @XmlElement(name = "fullname")
  @NotNull
  private final String fullname;
  /**
   * Свойство получения полного названия
   * организации.
   * <small>P.S. все по ГОСТу</small>
   * @return полное название организации
   */
  public String getFullname() { return fullname; }

  // количество сотрудников в организации
  @XmlAttribute(name = "employees-count")
  private final int employeesCount;
  /**
   * Свойство получения числа сотрудником,
   * работающих в организации
   * @return число сотрудников
   */
  public int getEmployeesCount() { return employeesCount; }

  // тип организации взятый из перечисления
  @XmlElement(name = "organization-type", required = true)
  @Nullable
  private final OrganizationType type;
  /**
   * Свойство получения типа организации,
   * есть несколько базовых типов, они
   * расположены в перечислении {@link OrganizationType}
   * @return тип организации
   */
  public OrganizationType getType() { return type; }

  // официальный адрес организации
  @XmlElement(name = "official-address")
  @Nullable
  private final Address officialAddress;
  /**
   * Свойство получения официального
   * адреса организации
   * @return официальный адрес организации
   */
  public Address getAddress() { return officialAddress; }

  /**
   * Полный конструктор с подкидным дураком
   * и пионерками. Осуществляет проверки
   * корректности присваиваемых значений
   * @param name название организации
   * @param fullname полное название организации
   * @param turnover ежегодная прибыль
   * @param employees число сотрудников
   * @param type тип организации
   * @param cord координаты организации
   * @param address адрес организации
   */
  public Organization(
          String name, String fullname, float annualTurnover, int employeesCount,
          OrganizationType type, Coordinates cord, Address officialAddress) {
    // проверили корректность названия
    this.name = (name.isEmpty() || (name == null))? "SampleOrganization" : name;
    // проверили корректность полного названия
    this.fullname = (fullname == null)? "" : fullname;
    // проверка корректности координат
    coordinates = (cord == null)? new Coordinates() : cord;
    // проверка корректности прибыли
    this.annualTurnover = annualTurnover > 0? annualTurnover : Float.MIN_VALUE;
    // проверка корректности числа сотрудников
    this.employeesCount = employeesCount > 0? employeesCount : 1;
    this.type = type;
    this.officialAddress = officialAddress;
    // организуем буферное значение, дабы не было отрицательных идентификаторов
    int buffer = hashCode() + counter++;
    id = (buffer == Integer.MIN_VALUE)? --buffer : Math.abs(buffer);
  }

  /**
   * Австралопитек здешних конструкторов,
   * все значения устанавливает по умолчанию
   */
  public Organization() {
    this(null, null, 0, 0, null, null, null);
  }

  // методы Object

  /**
   * Метод проверки организаций
   * на равенство
   * @param other другая организация
   * @return равны ли
   */
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
            && ((officialAddress == null)? false : officialAddress.equals(another.officialAddress))
            && (creationDate.isEqual(another.creationDate));
    // TODO: возможны nullPointerException
  }

  /**
   * Метод преобразования
   * объекта в число. Формирует идентификатор
   * @return число отображающее объект
   */
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
   * Метод преобразования объекта в строку
   * @return строковое представление объекта
   */
  @Override
  public String toString() {
    return this.getClass().getSimpleName()
            + "[id = " + id + "; name: " + name
            + "; fullname: " + fullname
            + "; type: " + type
            + "; creationDate: " + creationDate
            + "; annualTurnover = " + annualTurnover
            + "; employeesCount = " + employeesCount
            + "; coordinates: " + coordinates
            + "; official-address: " + officialAddress + "]";
  }
}