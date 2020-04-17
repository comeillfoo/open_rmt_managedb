package nook;

import communication.SystemAdmin;

import java.io.IOException;

public final class CIF_ServerRunner {
  public static void main(String[] args) {
    try {
      // призываем админа сервера Андрея
      SystemAdmin Andrew = SystemAdmin.summon();
      // Андрей настроил сервер
      Andrew.install("localhost", 0xdead);
      // Андрей запустил сервер
      Andrew.launch();
    } catch (IOException e) {}
  }
}
