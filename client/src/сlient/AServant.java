package сlient;

import communication.Component;
import communication.Mediating;
import communication.Segment;

import java.util.Scanner;

public abstract class AServant implements Component {
    protected Client client;
    protected Mediating mediator;
    protected Scanner scanner;

    public AServant(Mediating mediator) {
        this.mediator = mediator;
        scanner = new Scanner(System.in);
    }

    public void order(Segment parcel) { }
    public boolean setConnection() { return false;}
    public boolean resetConnection() { return false;}
    public String debrief() { return "";}
}