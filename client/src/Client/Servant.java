package Client;

import communication.Component;
import communication.Mediating;
import communication.Segment;

import java.util.Scanner;

/**
 * Класс через который вызывается метод клиентского класса для создания подключения с сервером.
 * Также этот класс принимает ввод от юзера.
 */
public class Servant implements Component {
    private Client client;
    private Mediating mediator;
    private Scanner scanner;

    public Servant(Mediating mediator){
        this.mediator = mediator;
        scanner = new Scanner(System.in);
    }

    //если очень захочется,можно будет задавать подключение через аргументы командной строки.
    public boolean setConnection(){
        client = mediator.getClient();
        if (!client.connect("localhost", 0xdead)) {
            System.err.println("Server is closed");
            return false;
        } else {
            new Thread(client).start();
            return true;
        }
    }

    public void order(Segment parcel){
        try {
            Thread.sleep(50);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print(">");
        String orderData = scanner.nextLine();

        //заранее проверяем на бесполезные вводы
        if (orderData.equals("") || orderData.split(" ").length == 0) return;

        parcel.setStringData(orderData.split(" "));
        //после получения пользовательской строки происходит обращение к модулю валидации введенных данных
        mediator.notify(this,parcel);
    }

}
