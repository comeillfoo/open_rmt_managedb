package dispatching.validate;

import communication.Mediating;
import communication.Segment;

/**
 * Основной класс для создания новых обработчиков
 */
public abstract class DataHandler implements Handler {
    protected Handler nextHandler;
    protected Mediating mediator;

    @Override
    public void setNext(Handler handler) {
        nextHandler = handler;
    }

    @Override
    public boolean handle(Segment parcel) {
        return nextHandler.handle(parcel);
    }
}
