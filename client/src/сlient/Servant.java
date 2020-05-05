package сlient;

import communication.ClientPackage;
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
        if (resetConnection()) {
            new Thread(client).start();
            return true;
        }
        return false;
    }

    @Override
    public boolean resetConnection() {
        while (true){
            if (client.connect("localhost", 0xdead))break;
            pipeOut.println("Server is closed!");
            pipeOut.println("Retry connection [y/n]?");
                String answer = "";
                while (true){
                    answer = debrief();
                    if (answer.equals("y")) break;
                    else if(answer.equals("n")) System.exit(0);
                    else continue;
                }
        }
        return true;
    }

    @Override
    public void order(Segment parcel) {
        String orderData = debrief();
        System.out.println(orderData);
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
        String orderData = "";
        while (true) {
            pipeOut.print(">");
            orderData = scanner.nextLine();
            if (orderData.equals("")) {
                continue;
            }
            else {
                break;
            }
        }
        return orderData;
    }
    @Override
    public void notification(Segment parcel) {
        pipeOut.printf("Server: %f" +((ClientPackage)parcel.getDataObject()).getReport());
        pipeOut.printf("Server Error report:%f" + ((ClientPackage)parcel.getDataObject()).getErroReport());
        //TODO: make good notifications
    }
}
