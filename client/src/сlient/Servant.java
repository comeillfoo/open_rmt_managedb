package сlient;

import communication.ClientPackage;
import dataSection.enumSection.Markers;
import communication.Mediating;
import communication.Segment;

import java.io.IOException;

/**
 * Класс через который вызывается метод клиентского класса для создания подключения с сервером.
 * Также этот класс принимает ввод от юзера.
 *  @author Come_1LL_F00 aka Lenar Khannanov
 *  @author Leargy aka Anton Sushkevich
 * @see AServant
 */
public class Servant extends AServant {
    /**
     * Конструктор принимающий ссылку на посредника.
     * @param mediator
     */
    public Servant(Mediating mediator) {
        super(mediator);
    }
    //если очень захочется,можно будет задавать подключение через аргументы командной строки.

    /**
     * метод установки соединения
     * @return boolean
     */
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

    /**
     * Метод, реализующий переустановку подключения к серверу.
     * @return boolean
     * @throws IOException
     */
    @Override
    public boolean resetConnection() throws IOException {
        while (true){
            try{
                client.killSocket();
            }catch (NullPointerException e) {}
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

    /**
     * Метод, принимающий сообщение от клиента и уведомление посредника для выполнения следующего действия.
     * @param parcel
     */
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

    /**
     * Метод для общения с клиентом.
     * @return String
     */
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
//    @Override
//    public void notification(Segment parcel) {
//        pipeOut.printf("Server: %f" +((ClientPackage)parcel.getClientPackage()).getReport());
//        pipeOut.printf("Server Error report:%f" + ((ClientPackage)parcel.getClientPackage()).getReport());
//        //TODO: make good notifications
//    }
}
