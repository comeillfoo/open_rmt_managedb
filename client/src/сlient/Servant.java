package сlient;

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
            //making thread to fell asleep to reach correct sequences of System.out and System.err
//            try {
//                Thread.sleep(50);
//                System.out.println("Retry connection [y/n]?");
//                String answer = "";
//                while (true){
//                    answer = debrief();
//                    if (answer.equals("y")) break;
//                    else if(answer.equals("n")) System.exit(0);
//                    else continue;
//                }
//                //resetting the connection
//                setConnection();
//            }catch (InterruptedException e) {
//                System.err.println("Вот и прервались!");
//            }
        }
        return false;
    }

    @Override
    public boolean resetConnection() {
        while (true){
            if (client.connect("localhost", 0xdead))break;
            System.err.println("Server is closed!");
            System.out.println("Retry connection [y/n]?");
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
            System.out.print(">");
            try {
                orderData = scanner.nextLine();
            }catch (IndexOutOfBoundsException e) {
                System.err.println("Сука, блять!");
                break;
            }
            if (orderData.equals("")) {continue;}
            else {break;}
        }
        return orderData;
    }

}
