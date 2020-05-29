package systemcore;

import communication.Component;
import communication.wrappers.QueryBag;
import instructions.rotten.base.RawExit;
import instructions.rotten.base.RawSave;
import parsing.SubProcessController;

import java.util.Scanner;

/**
 * Класс отввечающий за формирования команда со стороны сервера.
 *  @author Come_1LL_F00 aka Lenar Khannanov
 *  @author Leargy aka Anton Sushkevich
 */
public class ServerListener extends Thread implements Component {
    private SubProcessController subProcessController;
    private Scanner scanner = new Scanner(System.in);

    /**
     * конструктор, принимающий посреднека управления подпроцессами.
     * @param subProcessController
     */
    public ServerListener(SubProcessController subProcessController) {
        this.subProcessController = subProcessController;
    }

    @Override
    public void run() {
        while (true) {
            sendCommand(prepareCommand());
        }
    }

    /**
     * метод для формирования команды.
     * @return QueryBag
     */
    private QueryBag prepareCommand() {
        String[] tempCommand = listenForCommand().split(" ");
        switch (tempCommand[0]) {
            case RawSave.NAME: return new QueryBag(null, new RawSave());
            case RawExit.NAME: return new QueryBag(null, new RawExit());
        }
        return null;
    }

    /**
     * метод уведомление посредника о необходимости выполнить команду.
     * @param queryBag
     */
    private void sendCommand(QueryBag queryBag) {
        if (queryBag == null) return;
        subProcessController.notify(this, queryBag);
    }

    /**
     * метод получения строки с консоли сервера.
     * @return String
     */
    private String listenForCommand() {
        String order = "";
        while (true) {
            System.out.print(">");
            order = scanner.nextLine();
            if (order.equals("")) {
                continue;
            }
            else {
                break;
            }
        }
        return order;
    }
}
