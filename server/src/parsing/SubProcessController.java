package parsing;

import communication.*;
import instructions.concrete.ConDecree;
import instructions.rotten.RawDecree;
import czerkaloggers.customer.B_4D4_GE3;
import czerkaloggers.HawkPDroid;
import parsing.customer.bootstrapper.NakedCrateLoader;
import parsing.customer.distro.ShedBlock;
import parsing.plants.InstructionBuilder;
import parsing.plants.OrganizationBuilder;
import parsing.supplying.LilyInvoker;
import systemcore.ServerController;

/**
 * Контроллер, исполнения запросов клиента.
 * Если весь проект - это ОС, то это подсистема
 * управления процессами.
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 */
public final class SubProcessController extends Resolver {
  private final HawkPDroid<SubProcessController> RADIOMAN;

  /**
   * Конструктор, инициализирующий
   * подсистему полностью. Она состоит из
   * <ul>
   *   <li>Контроллера подсистемы</li>
   *   <li>Сутенера комманд или Invoker'a</li>
   *   <li>Сутенера коллекции или Receiver'a</li>
   *   <li>Фабрики (мороженого) комманд</li>
   *   <li>Шоколадной фабрики</li>
   *   <li>Логирующий элемент</li>
   * </ul>
   * @param m контроллер подсистемы
   */
  public SubProcessController(ServerController m) {
    super(m);
    // определяем логгер
    RADIOMAN = (HawkPDroid<SubProcessController>) B_4D4_GE3.assemble(this, B_4D4_GE3::new);
    // определяем загрузчик коллекции
    breadLoader = new NakedCrateLoader();
    // определяем ресивер коллекции
    fate = new ShedBlock(breadLoader, RADIOMAN);
    // определяем вызывателя комманд
    kael = new LilyInvoker(this);
    // определяем фабрику элементов коллекции
    plant = new OrganizationBuilder(this);
    // определяем преобразователя комманд
    wizard = new InstructionBuilder(this, plant);
  }

  /**
   * Единственный метод, отправляющий
   * команды на исполнение и пробрасывающий
   * результат их исполнения дальше по цепи.
   * <ul>
   *   <li>Достать сырую команду</li>
   *   <li>
   *     Сформировать конкретную команду
   *     посредством фабрики комманд
   *   </li>
   *   <li>Передать команду Invoker'у</li>
   *   <li>Получить отчет о работе команды</li>
   *   <li>Отправить отчет в модуль отправки</li>
   * </ul>
   * @param parcel присланные данные о клиенте и команде на исполнение
   */
  @Override
  public void parse(Segment parcel) {
    RawDecree command = (RawDecree)((ClientPackage) parcel.getData()).getCommand();
    System.out.println(command);
    notify(this, command);
  }

  /**
   * Основной метод перессылки данных между
   * компонентами модуля разбора запросов.
   * @param sender отправитель
   * @param data отправляемые данные
   */
  @Override
  public void notify(Component sender, Valuable data) {
    // если отправляет логирующий элемент,
    // то отправляем клиенту
    if (sender == RADIOMAN)
      CONTROLLER.notify(this, data); // отправили господину распорядителю
    else if (sender == this)
      wizard.make((RawDecree) data, fate); // отправили на фабрику (мороженого шутка) комманд
    else if (sender == wizard)
      kael.invoke((ConDecree) data); // отправили Invoker'у, чтобы исполнить
    // разрастается по мере увеличения числа компонент модуля
  }
}
