package communication.wrappers;

import communication.Report;
import javafx.geometry.NodeOrientation;

import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;

/**
 * Пакет, хранящий данные, что
 * нужно отправить клиенту. В основном
 * результаты обработки запросов или
 * косяки клиента
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushevich
 * @see TunnelBag
 * @see communication.Valuable
 */
public final class AlertBag extends TunnelBag {
  // передаваемое клиенту уведомление
  private final Report NOTIFICATION;

  // конструктор с основными параметрами
  /**
   * Конструктор принимающий
   * наш this very k-nal и
   * отправляемое уведомление
   * @param channel клиентский или серверный канал
   * @param notification уведомление клиенту
   */
  public AlertBag(SocketChannel channel, Report notification) {
    super(channel);
    NOTIFICATION = (notification == null)? new Report(-1, "Пустое уведомление") : notification;
  }

  // геттер отчета
  /**
   * Свойство взятия отправляемого уведомления
   * @return отправляемый отчет
   */
  public final Report Notify() { return NOTIFICATION; }

  public final SocketChannel Channel() {
    return (SocketChannel) super.CHANNEL;
  }

}
