package instructions.rotten.extended;

import entities.Junker;
import entities.IJunked;
import instructions.rotten.IClued;
import instructions.rotten.RawCommiter;

public class RawInsert extends RawCommiter implements IClued<Integer>, IJunked<Junker> {
    public static final String NAME = "insert";
    public static final String BRIEF = "Добавляет элемент с указанным [key] в колекцию.";
    public static final String SYNTAX = NAME + " [key] {element}";
    public static final int ARGNUM = 2;
    private Integer key;

    public RawInsert setKey(Integer key) {
        this.key = key;
        return this;
    }
    @Override
    public Integer Key() {
        return key;
    }
    @Override
    public Junker getData() {
        return junker;
    }
    public RawInsert setData(Junker attachedData) {
        junker = attachedData;
        return this;
    }
}
