package systemcore;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Здесь происходит запуск этой махины
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class ServerRun {
    private static String hostname = "127.0.0.1"; // хост сервера
    private static int port = 0xdead; // порт сервера
    private static String ipPattern = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    /**
     * Основная часть, где происходит запуск
     * сервера с помощью  специального настройщика,
     * что сначала устанавливает сервер,
     * а лишь затем его запускает
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Thread serverInputThread = new Thread(
                ()->{

                }
        );
        // вызвали настройщика
        SystemAdmin AndrewBot = SystemAdmin.summon("Андрей");
        // установили сервер на нужный хост и порт
        Scanner scan = new Scanner(System.in);
        int newport = port; String input = "";
        try {
            input = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println("Что-то пошло не так, ради безопасности завершим приложение");
            System.exit(1);
        }
        // ввод ip адреса
        int tryies = 5;
        do {
            System.out.println("Введите IP адрес сервера:");
            input = scan.nextLine();
            if (!input.matches(ipPattern))
                System.out.println("Задан неправильный адрес: осталось " + tryies + " попыток на ввод");
        } while (!input.matches(ipPattern) && tryies-- != 0);
        if (tryies <= 0) {
            System.out.println("Попытки ввода адреса окончились -- сервер будет настроен на адрес " + hostname);
        } else {
            hostname = input;
        }
        tryies = 5;
        do {
            System.out.println("Введите порт сервера:");
            try {
                newport = Integer.valueOf(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный формат числа");
            }
            if (newport < 48654)
                System.out.println("Задан неправильный порт: осталось " + tryies + " попыток на ввод");
        } while (newport < 48654 && tryies-- != 0);
        if (tryies <= 0) {
            System.out.println("Попытки ввода адреса окончились -- сервер будет настроен на порт " + port);
        } else {
            port = newport;
        }
        AndrewBot.install(hostname, port);
        // запустили сервер
        AndrewBot.launch();
    }
}

