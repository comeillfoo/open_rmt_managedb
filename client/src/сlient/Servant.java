package сlient;

import communication.ClientPackage;
import communication.Markers;
import communication.Mediating;
import communication.Segment;

import java.io.IOException;

/**
 * Класс через который вызывается метод клиентского класса для создания подключения с сервером.
 * Также этот класс принимает ввод от юзера.
 *  @author Come_1LL_F00 aka Lenar Khannanov
 *  @author Leargy aka Anton Sushkevich
 */
public class Servant extends AServant {
    public Servant(Mediating mediator) {
        super(mediator);
    }
    //если очень захочется,можно будет задавать подключение через аргументы командной строки.
    @Override
    public boolean setConnection() {
        client = mediator.getClient();
        try {
            if (resetConnection()) {
                new Thread(client).start();
                return true;
            }
        }catch (IOException e) {
            new IOException("Problems with resetting connection...",e).getMessage();
        }
        return false;
    }

    @Override
    public boolean resetConnection() throws IOException {
        while (true){
            if (client.connect("localhost", 0xdead)) break;
                String answer = "";
                while (true){
                    pipeOut.println("Retry connection [y/n]?");
                    answer = debrief();
                    if (answer.equals("y")) break;
                    else if(answer.equals("n")) {
                        mediator.notify(this,new Segment(Markers.STOP));
                    }
                    else continue;
                }
        }
        return true;
    }

    @Override
    public void order(Segment parcel) {
        String orderData = debrief();
        parcel.setStringData(orderData.split(" "));
        //после получения пользовательской строки происходит обращение к модулю валидации введенных данных
        try {
            mediator.notify(this, parcel);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String debrief() {
        String stringData = "";
        while (true) {
            pipeOut.print(">");
            stringData = scanner.nextLine();
            if (stringData.equals("")) {
                continue;
            }
            else {
                break;
            }
        }
        return stringData;
    }
    @Override
    public void notification(Segment parcel) {
        pipeOut.printf("Server: %f" +((ClientPackage)parcel.getClientPackage()).getReport());
        pipeOut.printf("Server Error report:%f" + ((ClientPackage)parcel.getClientPackage()).getErrorReport());
        //TODO: make good notifications
    }
}
