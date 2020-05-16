package czerkaloggers;

import communication.Mediator;

public interface DroidFactory {
  HawkPDroid<? extends Mediator> create(Mediator controller);
}
