package communication;

import Client.Client;
import Client.Servant;
import dispatching.Dispatcher;
import receiver.Receiver;

/**
 * Класс организации обращений разных частей программы друг к другу.(паттерн "Посредник")
 */
public class Mediator implements Mediating {

    private Client client;
    private Receiver receiver;
    private Dispatcher dispatcher;
    private Servant servant;

    public Mediator(){
        client = new Client(this);
        receiver = new Receiver();
        dispatcher = new Dispatcher(this);
        servant = new Servant(this);
    }

    @Override
    public Client getClient() {
        return client;
    }
    @Override
    public void notify(Component component, Segment parcel) {
        if(component == servant) dispatcher.giveOrger(parcel);
        //if(component == dispatcher)
        if(component == client && parcel.getStringData() == null) servant.order(parcel);

    }
}
