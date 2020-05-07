package dispatching.validators;

import communication.Mediating;
import communication.Segment;
import exceptions.CommandSyntaxException;
import dataSection.Cook;
import instructions.rotten.RawDecree;

/**
 * Основной класс для создания новых обработчиков
 */
public abstract class DataHandler implements Handler  {
    protected Handler nextHandler;
    protected Mediating mediator;
    protected Cook cook;

    public void setMediator(Mediating mediator){
        this.mediator = mediator;
        cook = new Cook();
    }

    @Override
    public void setNext(Handler handler) {
        nextHandler = handler;
    }

    @Override
    public RawDecree handle(Segment parcel) throws CommandSyntaxException {
        return nextHandler.handle(parcel);
    }
}
