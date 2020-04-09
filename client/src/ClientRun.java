import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientRun {
  public static void main(String[] args) {
    byte[] ip = null; int port = -1;
    Scanner dialog = new Scanner(System.in);
    if (args.length < 2) {
      // ввод порта и адреса через консоль
      System.out.println("java -jar {jarname}.jar [ip] [port]");
      System.out.println("e.g. java -jar program.jar 192.168.0.1 80");
      while (ip == null) {
        System.out.print("Введите IP сервера, к которому хотите подключиться: ");
        ip = parseIP(dialog.nextLine());
      }
      while (port == -1) {
        System.out.print("Введите порт, на котором работает сервер: ");
        try {
          port = Integer.parseInt(dialog.nextLine());
        } catch (InputMismatchException e) { System.err.println("Неправильный формат числа: порт - неотрицательное целое число. Попробуйте снова"); }
      }
    } else {
      // взятие порта и адреса из аргументов командной строки
      try {
        ip = parseIP(args[0]);
      } catch (InputMismatchException e) {
        System.err.println("Введенная строка не соответствует формату IPv4");
        System.exit(1);
      }
      try {
        port = Integer.parseInt(args[1]);
        if (port < 0) {
          System.err.println("Неправильно задан порт, порт - это неотрицательное целое число");
          System.exit(1);
        }
      } catch (InputMismatchException e) {
        System.err.println("Неправильный формат числа: порт - неотрицательное целое число.");
        System.exit(1);
      }
    }
    // устанавливаем подключение
  }

  private static byte[] parseIP(String line) throws InputMismatchException{
    throw new NotImplementedException();
  }
}
