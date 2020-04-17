package communication;

import nook.Server;

/**
 * Сам посредник или управленец. Регулирует работу сервера и его модулей.
 * В связи с особенностью реализации, требует геттер, возвращающий объект, представляющий сам сервер.
 */
public interface Mediator {
  void notify(Component sender, Segment data);
  Server getMainServer();
}
