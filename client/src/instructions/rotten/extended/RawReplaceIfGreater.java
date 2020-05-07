package instructions.rotten.extended;

import communication.Junker;
import instructions.rotten.IClued;
import instructions.rotten.IJunked;
import instructions.rotten.RawCommiter;
import instructions.rotten.RawDecree;

public class RawReplaceIfGreater extends RawCommiter implements IClued<Integer>,IJunked<Junker> {
    public static final String NAME = "replace_if_greater";
    public static final String BRIEF = "Находит элементы коллекции по заданному имени";
    public static final String SYNTAX = NAME + " [name]";
    public static final int ARGNUM = 2;
    private Integer key;
    private Junker junker;

    @Override
    public Junker getData() {
        return junker;
    }

    public RawReplaceIfGreater setData(Junker attachedData) {
        junker = attachedData;
        return this;
    }
    @Override
    public Integer Key() {
        return null;
    }

    public RawReplaceIfGreater setKey(Integer key) {
        this.key = key;
        return this;
    }
}
