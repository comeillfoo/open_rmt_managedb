package systemcore;

/**
 * Здесь происходит запуск этой махины
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class ServerRun {
    private static final String hostname = "localhost"; // хост сервера
    private static final int port = 0xdead; // порт сервера

    /**
     * Основная часть, где происходит запуск
     * сервера с помощью  специального настройщика,
     * что сначала устанавливает сервер,
     * а лишь затем его запускает
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        // вызвали настройщика
        SystemAdmin AndrewBot = SystemAdmin.summon("Андрей");
        // установили сервер на нужный хост и порт
        AndrewBot.install(hostname, port);
        // запустили сервер
        AndrewBot.launch();
    }
}

