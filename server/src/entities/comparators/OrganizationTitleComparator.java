package entities.comparators;

import entities.Organization;

import java.util.Comparator;

/**
 * Простейший сравнитель, который,
 * в отличии от пятой лабораторной сравнивает по названию,
 * а не всем полям, что и требуется по заданию.
 * Выделен в отдельный компаратор, а не оставлен в нашей сущности, чтобы
 * не было соблазна накидать туда функционала.
 * Продолжаем хайпить на SRP.
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @see Comparator
 * @see Organization
 */
public final class OrganizationTitleComparator implements Comparator<Organization> {
  /**
   * Сравниваем организации по названию, благодаря встроенному геттеру
   * @param o1 левый операнд сравнения
   * @param o2 правый операнд сравнений
   * @return целое число, которое показывает что больше/меньше
   */
  @Override
  public int compare(Organization o1, Organization o2) {
    return Comparator.comparing(Organization::Name).compare(o1, o2);
  }
}
