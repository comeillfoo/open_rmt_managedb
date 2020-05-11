package dispatching.validators;

import communication.Mediating;
import communication.Segment;
import exceptions.CommandSyntaxException;
import entities.Cook;
import instructions.rotten.RawDecree;

/**
 * Абстрактный класс для создания новых обработчиков.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public abstract class DataHandler implements Handler  {
    protected Handler nextHandler;
    protected Mediating mediator;
    protected Cook cook;

    /**
     * конструктор, инициилизующий модуль создания спец. объекта и пренимающий ссылку на посредника.
     * @param mediator посредник
     */
    public void setMediator(Mediating mediator){
        this.mediator = mediator;
        cook = new Cook();
    }

    /**
     * Сеттер для задание следующего звена последовательности проверок.
     * @param handler
     */
    @Override
    public void setNext(Handler handler) {
        nextHandler = handler;
    }

    /**
     * метод содержащий основную логику проверки.
     * @param parcel
     * @return RawDecree
     * @throws CommandSyntaxException
     */
    @Override
    public RawDecree handle(Segment parcel) throws CommandSyntaxException {
        return nextHandler.handle(parcel);
    }
}
