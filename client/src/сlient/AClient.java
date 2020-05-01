package сlient;

import communication.Mediating;

public abstract class AClient {
    protected static Mediating mediator;
    public AClient(Mediating mediator){
        this.mediator = mediator;
    }
}
