package dispatching.validate;

import communication.Segment;

/**
 * Основной класс для создания новых обработчиков
 */
public class DataHandler implements Handler{
    private Handler nextHandler;

    @Override
    public void setNext(Handler handler) {
        nextHandler = handler;
    }

    @Override
    public boolean handle(Segment parcel) {
        return nextHandler.handle(parcel);
    }
}
