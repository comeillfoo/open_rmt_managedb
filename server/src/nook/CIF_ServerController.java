package nook;

import communication.Mediator;
import communication.Component;
import communication.Segment;
import sending.AliExpress;
import sending.Dispatcher;
import parsing.SubProcessController;
import parsing.Resolver;
import perusal.BookWorm;
import perusal.QueryReader;

import java.nio.channels.Selector;

//использован патерн посредник
public final class CIF_ServerController implements Mediator {
  // component list
  private final Server mainServer;
  private final QueryReader mainReader;
  private final Resolver mainParser;
  private final Dispatcher mainDispatcher;
  // pattern's method
  @Override
  public void notify(Component sender, Segment parcel) {
    if (sender == mainServer) mainReader.retrieve(parcel);
    if (sender == mainReader && parcel.getData() != null) mainParser.parse(parcel);
    if (sender == mainReader && parcel.getData() == null) mainServer.closeConnection(parcel);
    if (sender == mainParser) mainDispatcher.sendCorona(parcel);
  }
  // builders
  public CIF_ServerController(Selector selector) {
    mainServer = new CIF_Server(this, selector);
    mainReader = new BookWorm(this);
    mainParser = new SubProcessController(this);
    mainDispatcher = new AliExpress(this);
  }
  // properties and methods
  public Server getMainServer() { return mainServer; }

}
