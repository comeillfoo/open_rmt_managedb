import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
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
    try (
        SocketChannel suck = SocketChannel.open(new InetSocketAddress(InetAddress.getByAddress(ip), port));
        Scanner receiver = new Scanner(suck);
        PrintWriter sender = new PrintWriter(Channels.newOutputStream(suck), true);
    ) {

      System.out.print("Введите сообщение: ");
      String message = dialog.nextLine();
      // реализуем эхо
      // отправляем байты на сервер
      sender.println(message);
      // принимаем результат
      while (dialog.nextInt() != 0) {
        dialog.nextLine();
        if (receiver.hasNextLine())
          message = receiver.nextLine();
        System.out.println(message);
        System.out.print("Введите сообщение: ");
        message = dialog.nextLine();
        sender.println(message);
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static byte[] parseIP(String ip) throws InputMismatchException {
    String[] bytes = ip.split("\\.");
    if (bytes == null || bytes.length != 4) throw new InputMismatchException("Неправильный формат IPv4");
    else {
      byte[] result = new byte[4];
      try {
        int i = 0;
        for (String b : bytes) {
          int number = Integer.parseInt(b);
          if (number > 255 || number < 0) throw new InputMismatchException("Неправильный формат IPv4. Число не лежит в диапазоне [0; 255]");
          result[i++] = (byte) number;
        }
      } catch (InputMismatchException e) {
        if (!e.getMessage().equals("Неправильный формат IPv4. Число не лежит в диапазоне [0; 255]"))
          throw new InputMismatchException("Неправильный формат IPv4. Найдены недопустимые символы.");
        else throw e;
      }
      return result;
    }
  }
}
