package dispatching;

import communication.Component;
import communication.Mediating;
import communication.Segment;
import dataSection.CommandList;
import dispatching.validate.ArgumentHandler;
import dispatching.validate.CommandHandler;
import dispatching.validate.DataHandler;

import java.io.*;
import java.nio.ByteBuffer;

public class Dispatcher implements Component {
    private DataHandler dataHandler;
    private Mediating mediator;
    private CommandList commandList = new CommandList();
    public Dispatcher(Mediating mediator){
        this.mediator = mediator;
        //Инициализируем цепочку проверок.
        CommandHandler commandHandler = new CommandHandler(commandList);
        ArgumentHandler argumentHandler = new ArgumentHandler();
        commandHandler.setNext(argumentHandler);

        dataHandler = commandHandler;
    }
    public void giveOrger(Segment parcel) {
        if (parcel.getStringData()[0].equals("exit")) {
            System.exit(0);
        }

        if(dataHandler.handle(parcel)){
            try {
                send(parcel);
            }catch (IOException e){
                e.printStackTrace();
                System.err.println("Какая-то ошибка отправки");
            }
        }else {
            try {
                Thread.sleep(50);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Для большей информации про каманды используйте команду \"help\".");
        }

    }

    //сериализуем и отправляем
    public void send(Segment parcel) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(parcel.getDataObject());
        System.out.println(byteArrayOutputStream.toByteArray().length);
        parcel.getSocketChannel().write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));

        // TODO: Обработка разрыва подключения
    }
}
