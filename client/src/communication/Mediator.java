package communication;

import dispatching.ADispatcher;
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
 */
public class Mediator implements Mediating {
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

    public AServant getServant() {
        return servant;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public void notify(Component component, Segment parcel) throws IOException {
        if(component == servant) dispatcher.giveOrder(parcel);
        if(component == dispatcher && parcel == null) servant.resetConnection();
        if(component == client && parcel.getStringData() == null) {
            servant.order(parcel);
        }else {
            receiver.receive(parcel);
        }
        if(component == receiver) servant.notification(parcel);
    }
}
