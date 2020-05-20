package systemcore;

import communication.Component;
import communication.wrappers.QueryBag;
import instructions.rotten.base.RawSave;
import parsing.SubProcessController;

import java.util.Scanner;

public class ServerListener extends Thread implements Component {
    private SubProcessController subProcessController;
    private Scanner scanner = new Scanner(System.in);

    public ServerListener(SubProcessController subProcessController) {
        this.subProcessController = subProcessController;
    }

    @Override
    public void run() {
        while (true) {
            sendCommand(prepareCommand());
        }
    }

    private QueryBag prepareCommand() {
        String[] tempCommand = listenForCommand().split(" ");
        switch (tempCommand[0]) {
            case RawSave.NAME: return new QueryBag(null, new RawSave());
        }
        return null;
    }

    private void sendCommand(QueryBag queryBag) {
        subProcessController.notify(this, queryBag);
    }

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
