package parsing.customer;

import entities.Mappable;

/**
 * Лакмусовая бумажка этого казино,
 * цветами не блещет, но хотя бы да/нет сказать может
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 */
@FunctionalInterface
public interface Indicator {
  /**
   * Мы требовательные, потому это будет
   * проверять данное чудо на соответсвие нашим требованиям
   * @param subject избранник на тесты
   * @return отказ или согласие (соответствие условию или нет)
   */
  boolean verify(Mappable<Integer> subject);
}
