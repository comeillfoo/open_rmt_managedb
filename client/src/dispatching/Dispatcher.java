package dispatching;

import dataSection.Commands;
import dataSection.enumSection.Markers;
import communication.Mediating;
import communication.Segment;
import dataSection.CommandList;
import exceptions.CommandSyntaxException;
import dispatching.validators.ArgumentHandler;
import dispatching.validators.CommandHandler;
import dispatching.validators.Handler;
import instructions.rotten.RawDecree;
import instructions.rotten.base.RawExit;

import java.io.*;
import java.nio.ByteBuffer;

public class Dispatcher extends ADispatcher {
    private final Handler dataHandler;
    private Mediating mediator;
    private Commands commandList = new CommandList();
    private ByteArrayOutputStream byteArrayOutputStream ;
    private ObjectOutputStream objectOutputStream;

    public Dispatcher(Mediating mediator){
        this.mediator = mediator;
        //Инициализируем цепочку проверок.
        CommandHandler commandHandler = new CommandHandler(commandList);
        ArgumentHandler argumentHandler = new ArgumentHandler(commandList);
        commandHandler.setNext(argumentHandler);

        commandHandler.setMediator(mediator);
        argumentHandler.setMediator(mediator);

        dataHandler = commandHandler;
    }

    public void giveOrder(Segment parcel) throws IOException {
        RawDecree tempCommand = null;
        try {
            tempCommand = dataHandler.handle(parcel);
        }catch (CommandSyntaxException e) {
            e.getMessage();
        }
        if (tempCommand != null) {
            if (tempCommand.getClass().isInstance(new RawExit())) {
                parcel.setMarker(Markers.STOP);
                mediator.notify(this,parcel);
                return;
            }
            try {
                parcel.setCommandData(tempCommand);
                send(parcel);
            }catch (IOException e) {
                new IOException("Ошибка отправки.",e).getMessage();
            }
        }else {
            System.out.println("Для большей информации про каманды используйте команду \"help\".");
        }
    }
    //сериализуем и отправляем
    public void send(Segment parcel) throws IOException {
        byteArrayOutputStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(parcel.prepareDataObject());
        objectOutputStream.flush();
        try {
            parcel.getSocketChannel().write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
        }catch (IOException e) {
            System.err.println("─────Server connection is interrupted─────");
            parcel.setMarker(Markers.INTERRUPTED);
            mediator.notify(this,parcel);
        }finally {
            byteArrayOutputStream.close();
            objectOutputStream.close();
        }
        // TODO: Обработка разрыва подключения
    }
}
