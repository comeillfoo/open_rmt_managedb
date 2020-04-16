import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Client {
    private Scanner dialog;
    private Selector selector;
    private SocketChannel clientSocket;

    public Client(SocketChannel clientSocket, Scanner dialog) throws IOException {
        //TODO: прикрутить обработку неработы главного сервера или прокси
        this.dialog = dialog;
        selector = Selector.open();
        this.clientSocket = clientSocket;

        clientSocket.configureBlocking(false);
        clientSocket.register(selector,SelectionKey.OP_READ);
        //переведение сокет-канала в неблокируемый режим исправило ошибку с получением нулевых байтов от сервера
        //однако на прокси и на сервер сообщения доходили в нормальном виде.
    }
    public void startClient() throws IOException {
        while (true){
            System.out.print("> ");
            reply(); //TODO:при вводе пустого сообщения "" клиентский поток зависает,но при этом никакой ошибки не падает Исправить!

            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectionKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                if (key.isReadable()){
                    request(key);
                }
                iter.remove();
            }
        }

    }

    public void request(SelectionKey key) throws IOException { //возможно метод общения с сервером будет изменен.
        byte[] bytes = new byte[32 * 1024];                      //TODO:подумать над чтением команд и их серриализацией.

        ((SocketChannel) key.channel()).read(ByteBuffer.wrap(bytes));

        System.out.println("Server:" + new String(bytes,"UTF-8"));

        ByteBuffer.wrap(bytes).clear();

    }
    public void reply() throws IOException{
        byte[] bytes = dialog.nextLine().getBytes();
        clientSocket.write(ByteBuffer.wrap(bytes));

        ByteBuffer.wrap(bytes).clear();
    }

    public void stopConnection() throws IOException { //задаток под exit мб...
        clientSocket.shutdownInput();
        clientSocket.shutdownOutput();
        clientSocket.close();
    }

}
