package communication;

import Client.Client;

public interface Mediating {
    void notify(Component component,Segment parcel);
    Client getClient();
}
