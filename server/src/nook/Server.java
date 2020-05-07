package nook;

import communication.Component;
import communication.Mediator;
import communication.Segment;

/**
 * Шаблон очень-очень абстрактного сервера, связанного с контроллером
 */
public abstract class Server implements Component, Runnable {
  protected final Mediator controller;
  public Server(Mediator controller) { this.controller = controller; }
  public void closeConnection(Segment parcel) { }
}
