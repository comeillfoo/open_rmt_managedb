package сlient;

import communication.Component;
import communication.Mediating;
import communication.Segment;

import java.io.IOException;
import java.net.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class Client extends AClient implements Component, Runnable {
    private SocketChannel socketChannel;
    private Selector selector;

    public Client(Mediating mediator) {
        super(mediator);
    }

    public boolean connect(String hostName,int serverPort){
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(InetAddress.getByName(hostName),serverPort));
            socketChannel.configureBlocking(false);
            while (!socketChannel.finishConnect()){
                System.out.println("Waiting for connection...");
            }
            System.out.println("───────Connection is set───────");

            socketChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);

            return true;
        } catch (UnknownHostException e) {
            return false;
        } catch (SocketException e){
            System.err.println("──w─> <Server connection is lost> <─w──");
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public void run() {
        while (socketChannel.isConnected()) {
            try {
                if (selector.selectNow() == 0) continue;
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                try {
                if (key.isReadable()) {
                    mediator.notify(this, new Segment((SocketChannel) key.channel(),new String[0]));
                }
                if (key.isWritable()) {
                    mediator.notify(this, new Segment((SocketChannel) key.channel(), null));
                }
                }catch (IOException e) {
                    e.printStackTrace();
                }
                iter.remove();
            }
        }
    }





    /*
    public Client.Client(SocketChannel clientSocket, Scanner dialog) throws IOException {
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
            reply();

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
        byte[] bytes = new byte[32 * 1024];

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

     */

}
