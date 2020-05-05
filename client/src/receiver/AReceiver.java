package receiver;

import communication.Component;
import communication.Mediating;
import communication.Segment;

import java.io.IOException;

public abstract class AReceiver implements Component {
    protected Mediating mediator;
    public AReceiver(Mediating mediator) {
        this.mediator = mediator;
    }

    public void receive(Segment parcel) throws IOException { }
}
