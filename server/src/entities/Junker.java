package entities;

import java.io.Serializable;

/**
 * Юнкер? Нет, ***** Мусорщик. Хранит параметры
 * передаваемых объектов.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class Junker implements Serializable {
  // ссылка на внутренние параметры
  private final Junker[] guts;
  // массив целочисленных параметров
  private final long[] digits;
  // массив действительных параметров
  private final double[] cogits;
  // массив текстовых параметров
  private final String[] lines;

  /**
   * Полновесный конструктор, устанавливающий
   * все параметры
   * сериализуемого класса.
   * @param digits все целочисленные параметры
   * @param cogits все действительные параметры
   * @param lines все строковые параметры
   * @param guts все составные параметры
   */
  public Junker(long[] digits, double[] cogits, String[] lines, Junker[] guts) {
    this.digits = digits;
    this.cogits = cogits;
    this.lines = lines;
    this.guts = guts;
  }
  // get-properties
  /**
   * Свойство получения массива под составных параметров
   * @return массив подпараметров
   */
  public Junker[] Guts() { return guts; }

  /**
   * Свойство получения целочисленных параметров
   * @return массив целочисленных параметров
   */
  public long[] Digits() { return digits; }

  /**
   * Свойство получения действительных параметров
   * @return массив действительных параметров
   */
  public double[] Cogits() { return cogits; }

  /**
   * Свойство получения строчных параметров
   * @return массив текстовых параметров
   */
  public String[] Lines() { return lines; }
}
