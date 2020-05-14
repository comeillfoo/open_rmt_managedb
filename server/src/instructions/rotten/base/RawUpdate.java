package instructions.rotten.base;

import entities.Junker;
import instructions.rotten.IClued;
import instructions.rotten.IJunked;
import instructions.rotten.RawCommitter;

public final class RawUpdate extends RawCommitter implements IClued, IJunked {
    public final static String NAME = "update";
    public static final String BRIEF = "Заменяет объект коллекции,соответствующий id, на новый, составленный пользователем,";
    public static final String SYNTAX = "update [id] {element}";
    public static final int ARGNUM = 0;
    private final Integer ID;

    public RawUpdate(Integer id, Junker junk) {
        super(junk);
        ID = id;
    }

    @Override
    public final Integer Key() { return ID; }

    @Override
    public final Junker Params() { return JUNK;}
}
