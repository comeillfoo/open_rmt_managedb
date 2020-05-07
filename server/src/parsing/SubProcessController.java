package parsing;

import communication.ClientPackage;
import communication.Component;
import communication.Mediator;
import communication.Segment;
import instructions.rotten.RawDecree;
import logging.customer.B_4D4_GE3;
import logging.customer.HawkPDroid;
import parsing.customer.bootstrapper.NakedCrateLoader;
import parsing.customer.distro.ShedBlock;
import parsing.plants.InstructionBuilder;
import parsing.plants.OrganizationBuilder;
import parsing.supplying.LilyInvoker;

/**
 * Контроллер, исполнения запросов клиента.
 * Если весь проект - это ОС, то это подсистема
 * управления процессами.
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 */
public final class SubProcessController extends Resolver {
  private RawDecree command;
  public SubProcessController(Mediator m) {
    super(m);
    // определяем логгер
    radioman = new B_4D4_GE3(this);
    // определяем ресивер коллекции
    fate = new ShedBlock(new NakedCrateLoader("DBPATH"), radioman);
    // TODO: получить название переменной окружения от системного администратора
    // определяем вызывателя комманд
    kael = new LilyInvoker(this);
    plant = new OrganizationBuilder(this);
    // определяем преобразователя комманд
    wizard = new InstructionBuilder(this, plant);
  }
  /**
   * Единственный метод, отправляющий команды на исполнение
   * и пробрасывающий результат их исполнения дальше по цепи.
   * @param parcel
   */
  @Override
  public void parse(Segment parcel) {
    RawDecree command = (RawDecree)((ClientPackage) parcel.getData()).getCommand();
    System.out.println(command);
    // TODO: передать команду (Commander'у объекта TotalCommander'a) получить отчет (объект класса Report)
    notify(this, null);
    // возможно придется добавить компонент, который в 5 лабе был для клиента
    // чтобы тот определял команду и вызывал ее.
  }

  /**
   * Основной метод перессылки данных между
   * компонентами модуля разбора запросов.
   * @param sender отправитель
   * @param data отправляемые данные
   */
  @Override
  public void notify(Component sender, Segment data) {
    if (sender instanceof HawkPDroid)
      mediator.notify(this, new Segment(data.getClient(), data.getData())); // отправили господину распорядителю
    // разрастается по мере увеличения числа компонент модуля
  }
}
