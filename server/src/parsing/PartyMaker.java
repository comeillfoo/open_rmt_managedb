package parsing;

import communication.Mediator;
import communication.Report;
import communication.Segment;
import parsing.interaction.instructions.Command;

public final class PartyMaker extends Resolver {
  public PartyMaker(Mediator m) { super(m); }
  @Override
  public void parse(Segment parcel) {
    Command command = (Command) parcel.getData();
    // TODO: передать команду (Commander'у объекта TotalCommander'a) получить отчет (объект класса Report)
    // заглушка
    Report someReport = null; // полученный отчет
    kapellmeister.notify(this, new Segment(parcel.getClient(), someReport)); // отправили господину распорядителю
  }
}
