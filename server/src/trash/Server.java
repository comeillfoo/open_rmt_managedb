package trash;

import communication.Component;
import communication.Mediator;
import communication.wrappers.AlertBag;

/**
 * Шаблон очень-очень абстрактного сервера, связанного с контроллером
 */
public abstract class Server implements Component, Runnable {
  protected final Mediator controller;
  public Server(Mediator controller) { this.controller = controller; }

  public abstract void closeConnection(AlertBag parcel);
}
