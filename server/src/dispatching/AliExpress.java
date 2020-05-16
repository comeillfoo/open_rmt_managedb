package dispatching;

import communication.Component;
import communication.Report;
import communication.wrappers.AlertBag;
import communication.Valuable;
import czerkaloggers.HawkPDroid;
import czerkaloggers.dispatching.TT_32_GE3;
import systemcore.ServerController;

import java.io.*;
import java.nio.ByteBuffer;

public final class AliExpress extends Dispatcher {
  private final HawkPDroid<AliExpress> PENTODE;

  @Override
  public void send(AlertBag postcard) {
    if ((postcard == null) || (postcard.Channel() == null)) {
      PENTODE.logboard(1, "Потеряно соединение с клиентом");
      return;
    }
    ByteArrayOutputStream bostream = new ByteArrayOutputStream();
    ObjectOutputStream objostream = null;
    // создали потоки
    try {
      objostream = new ObjectOutputStream(bostream);
    } catch (IOException outer) {
      PENTODE.logboard(10,"Не удалось создать поток десериализации");
      try {
        bostream.close();
      } catch (IOException inner) {
        PENTODE.logboard(10,"Не удалось закрыть поток");
        KAPELLMEISTER.ImmediateStop(new AlertBag(postcard.Channel(), new Report(10, "Не удалось закрыть поток")));
        System.exit(1);
      }
    }
    PENTODE.logboard(0,"Поток сериализации успешно создан");
    // отправили гавно
    try {
      objostream.writeObject(postcard.Notify());
      objostream.flush();
      postcard.Channel().write(ByteBuffer.wrap(bostream.toByteArray()));
      PENTODE.logboard(0,"Данные успешно отправлены");
    } catch (IOException outer) {
      PENTODE.logboard(10,"Не удалось получить доступ к потокам");
      KAPELLMEISTER.ImmediateStop(new AlertBag(postcard.Channel(), new Report(10, "Не удалось получить доступ к потокам")));
    } finally {
      try {
        objostream.close();
        bostream.close();
      } catch (IOException inner) {
        PENTODE.logboard(10,"Не удалось закрыть потоки");
        KAPELLMEISTER.ImmediateStop(new AlertBag(postcard.Channel(), new Report(10, "Не удалось закрыть потоки")));
        System.exit(1);
      }
    }
  }

  public AliExpress(ServerController m) {
    super(m);
    PENTODE = (HawkPDroid<AliExpress>) TT_32_GE3.assemble(this, TT_32_GE3::new);
  }

  @Override
  public void notify(Component sender, Valuable data) {
    if (sender == PENTODE)
      send((AlertBag) data);
  }
}
