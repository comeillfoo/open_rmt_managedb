package сlient;

import communication.Component;
import communication.Mediating;
import communication.Segment;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public abstract class AServant implements Component {
    protected Client client;
    protected Mediating mediator;
    protected Scanner scanner;
    protected InputStream pipeIn;
    protected PrintStream pipeOut;

    public AServant(Mediating mediator) {
        this.mediator = mediator;
        pipeIn = System.in;
        pipeOut = System.out;
        scanner = new Scanner(pipeIn);
    }

    public void order(Segment parcel) { }
    public boolean setConnection() { return false;}
    public boolean resetConnection() { return false;}
    public String debrief() { return "";}
    public void notification(Segment parcel) { }
}