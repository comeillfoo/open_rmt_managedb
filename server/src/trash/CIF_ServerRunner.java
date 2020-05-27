package trash;

import systemcore.SystemAdmin;

public final class CIF_ServerRunner {
  public static void main(String[] args) {
      // призываем админа сервера Андрея
      SystemAdmin Andrew = SystemAdmin.summon("Андрей");
      // Андрей настроил сервер
      Andrew.install("localhost", 0xdead);
      // Андрей запустил сервер
      Andrew.launch();
  }
}
