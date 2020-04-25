package dispatching.validate;

import communication.Segment;

/**
 * интерфейс необходимый для реализации шаблона "цепочка обязанностей"
 */
public interface Handler {
    void setNext(Handler handler);
    boolean handle(Segment parcel);
}
