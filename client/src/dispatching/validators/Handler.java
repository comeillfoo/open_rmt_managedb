package dispatching.validators;

import communication.Segment;
import exceptions.CommandSyntaxException;
import instructions.rotten.RawDecree;

/**
 * интерфейс необходимый для реализации шаблона "цепочка обязанностей"
 */
public interface Handler {
    void setNext(Handler handler);
    RawDecree handle(Segment parcel) throws CommandSyntaxException;
}
