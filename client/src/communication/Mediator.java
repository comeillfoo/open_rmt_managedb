package communication;

import dataSection.enumSection.Markers;
import dispatching.ADispatcher;
import dispatching.validators.DataHandler;
import receiver.AReceiver;
import сlient.Client;
import сlient.Servant;
import dispatching.Dispatcher;
import receiver.Receiver;
import сlient.AServant;

import java.io.IOException;

/**
 * Класс организации обращений разных частей программы друг к другу.(паттерн "Посредник")
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Mediating
 */
public class Mediator implements Mediating {
    private DataHandler dataHandler;
    private Client client;
    private AReceiver receiver;
    private ADispatcher dispatcher;
    private AServant servant;

    public Mediator() {
        client = new Client(this);
        receiver = new Receiver(this);
        dispatcher = new Dispatcher(this);
        servant = new Servant(this);
    }

    /**
     * Возвращает модуль ответственный за получение сообщений от клиента, установление объявления подключения к серверу.
     * @return AServant
     */
    public AServant getServant() {
        return servant;
    }

    /**
     * Возвращает модуль ответственный за установление/завершение подключения с сервером, и организаци принятия/отправления сообщения.
     * @return Client
     */
    @Override
    public Client getClient() {
        return client;
    }

    /**
     * Метод определющий свзязи между молдулями.Выясняет какому модулю направить обращение.
     * @param component
     * @param parcel
     * @throws IOException
     */
    @Override
    public void notify(Component component, Segment parcel) {
        if (component == servant) dispatcher.giveOrder(parcel);
        if (component == dispatcher && parcel.getMarker() == Markers.INTERRUPTED) servant.resetConnection();
        if ((component == dispatcher || component == servant) && parcel.getMarker() == Markers.STOP) client.stopAndClose();
        if (component == client && parcel.getMarker() == Markers.WRITE) servant.order(parcel);
        if (component == client && parcel.getMarker() == Markers.READ) receiver.receive(parcel);
        if (component == receiver && parcel.getMarker() == Markers.INTERRUPTED) {
            client.killSocket();
            servant.resetConnection();
        }
        if (component == receiver && parcel.getMarker() == Markers.WRITE) servant.notification(parcel);
    }
}
