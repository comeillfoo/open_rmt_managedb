package instructions.rotten.extended;

import entities.Junker;

import java.io.Serializable;

/**
 * "Сырая" команда "replace_if_greater".
 * содержит основную информацию о команде.
 * заменяет объект новым, если одно из полей превосходит сопоставимое ему поле из старого объекта.
 */
public final class RawReplaceIfGreater extends RawReplaceIf implements Serializable {
    public static final String NAME = "replace_if_greater";
    public static final String BRIEF = "Заменяет на новое значение по ключу [key], если оно больше старого.";
    public static final String SYNTAX = NAME + " [key] {element}";
    public static final int ARGNUM = 2;

    /**
     * Конструктор, устанавливающий параметры
     * добавляемого объекта
     * @param key
     * @param junk
     */
    public RawReplaceIfGreater(Integer key, Junker junk) {
        super(key, junk);
    }
}
