package communication;

/**
 * Сам посредник или управленец. Регулирует работу сервера и его модулей,
 * или модулей и их компонентов.
 */
public interface Mediator {
  void notify(Component sender, Segment data);
}
