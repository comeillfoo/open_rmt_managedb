package instructions.rotten.extended;

import communication.Junker;
import instructions.rotten.IClued;
import instructions.rotten.IJunked;
import instructions.rotten.RawCommiter;

public class RawUpdate extends RawCommiter implements IClued<Integer>, IJunked<Junker> {
    public final static String NAME = "update";
    public static final String BRIEF = "Заменяет объект коллекции,соответствующий id, на новый, составленный пользователем,";
    public static final String SYNTAX = "update [id] {element}";
    public static final int ARGNUM = 0;
    private Integer id;
    private Junker junker;

    public RawUpdate setKey(Integer key) {
        this.id = id;
        return this;
    }
    @Override
    public Integer Key() { return id; }
    @Override
    public Junker getData() { return junker;}
    public RawUpdate setData(Junker attachedData) {
        junker = attachedData;
        return this;
    }
}
