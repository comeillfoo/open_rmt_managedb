package dispatching;

import communication.Mediating;
import communication.Segment;
import dataSection.CommandList;
import dispatching.validate.ArgumentHandler;
import dispatching.validate.CommandHandler;
import dispatching.validate.Handler;

import java.io.*;
import java.nio.ByteBuffer;

public class Dispatcher extends ADispatcher {
    private final Handler dataHandler;
    private Mediating mediator;
    private CommandList commandList = new CommandList();
    private ByteArrayOutputStream byteArrayOutputStream;
    private ObjectOutputStream objectOutputStream;
    public Dispatcher(Mediating mediator) {
        this.mediator = mediator;
        //Инициализируем цепочку проверок.
        CommandHandler commandHandler = new CommandHandler(commandList);
        ArgumentHandler argumentHandler = new ArgumentHandler(commandList);
        commandHandler.setNext(argumentHandler);

        dataHandler = commandHandler;
    }

    public void giveOrder(Segment parcel) throws IOException {
        if (parcel.getStringData()[0].equals("exit")) {
            parcel.getSocketChannel().socket().shutdownInput();
            parcel.getSocketChannel().socket().shutdownOutput();
            parcel.getSocketChannel().socket().getChannel().finishConnect();
            System.exit(0);
        }

        if(dataHandler.handle(parcel)) {
            try {
                send(parcel);
            }catch (IOException e) {
                e.printStackTrace();
                System.err.println("Какая-то ошибка отправки");
            }
        }else {
            try {
                Thread.sleep(50);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Для большей информации про каманды используйте команду \"help\".");
        }

    }

    //сериализуем и отправляем
    public void send(Segment parcel) throws IOException {
        byteArrayOutputStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(parcel.getDataObject());
        System.out.println(byteArrayOutputStream.toByteArray().length);
        try {
            parcel.getSocketChannel().write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
        }catch (IOException e) {
            System.err.println("───────Server connection is lost───────");
            mediator.notify(this,null);
        }finally {
            objectOutputStream.flush();
        }

        // TODO: Обработка разрыва подключения
    }
}
