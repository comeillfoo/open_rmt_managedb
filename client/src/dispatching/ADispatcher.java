package dispatching;

import communication.Component;
import communication.Segment;

import java.io.IOException;

public abstract class ADispatcher implements Component {

    public void giveOrder(Segment parcel) throws IOException {}
}
