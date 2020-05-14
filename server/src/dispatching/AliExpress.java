package dispatching;

import communication.Component;
import communication.wrappers.AlertBag;
import communication.Valuable;
import czerkaloggers.HawkPDroid;
import czerkaloggers.dispatching.TT_32_GE3;
import systemcore.ServerController;

import java.io.*;
import java.nio.ByteBuffer;

public final class AliExpress extends Dispatcher {
  private final HawkPDroid<AliExpress> pentode;

  @Override
  public void send(AlertBag postcard) {
    ByteArrayOutputStream bostream = new ByteArrayOutputStream();
    ObjectOutputStream objostream = null;
    // создали потоки
    try {
      objostream = new ObjectOutputStream(bostream);
    } catch (IOException outer) {
      // TODO: log
      try {
        bostream.close();
      } catch (IOException inner) {
        // TODO: log
        System.exit(1);
      }
    }
    // отправили гавно
    try {
      objostream.writeObject(postcard.Notify());
      objostream.flush();
      postcard.Channel().write(ByteBuffer.wrap(bostream.toByteArray()));
    } catch (IOException outer) {
      // TODO: log
    } finally {
      try {
        objostream.close();
        bostream.close();
      } catch (IOException inner) {
        System.exit(1);
      }
    }
  }

  public AliExpress(ServerController m) {
    super(m);
    pentode = (HawkPDroid<AliExpress>) TT_32_GE3.assemble(this, TT_32_GE3::new);
  }

  @Override
  public void notify(Component sender, Valuable data) {
    if (sender == pentode)
      send((AlertBag) data);
  }
}
