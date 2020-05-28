package сlient;

import communication.Component;
import dataSection.enumSection.Markers;
import communication.Mediating;
import communication.Segment;

import java.io.IOException;
import java.net.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Класс модуля установления/завершение подключений, определения вектора действий.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see AClient,Component,Runnable
 */
public class Client extends AClient implements Component, Runnable {
    private SocketChannel socketChannel;
    private Selector selector;

    /**
     * Конструктор принимающий ссылку на посредника.
     * @param mediator
     */
    public Client(Mediating mediator) {
        super(mediator);
        try {
            selector = Selector.open();
        }catch (IOException ex) {/*NOP*/}
    }

    /**
     * Метод установки поключения.
     * @param hostName
     * @param serverPort
     * @return boolean
     */
    public boolean connect(String hostName,int serverPort){
        try {
            System.out.println("──────>Setting connection...");
            socketChannel = SocketChannel.open(new InetSocketAddress(InetAddress.getByName(hostName),serverPort));
            socketChannel.configureBlocking(false);
            while (!socketChannel.finishConnect()){
                System.out.println("Waiting for connection...");
            }
            System.out.println("──────>Connection is set<──────");

            socketChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);

            return true;
        } catch (UnknownHostException e) {
            return false;
        } catch (IOException e) {
            System.err.println("──────>Connection is lost< <─w──");
            return false;
        }
    }

    /**
     * Метод, закрывающий сокет(поток сокета) клиента.
     */
    public void killSocket() {
        try {
            socketChannel.close();
        }catch (IOException e) {
            new IOException("Something went wrong during closing \"socket channel\"",e);
        }
        //TODO:подумать над правильной обработкой остановки сервера.(TIME_OUT)
    }

    /**
     * Метод, использующий selector для определения дальнейшего вектора действий.
     */
    public void run() {
        while (socketChannel.isConnected()) {
            try {
                Thread.sleep(700);
            }catch (InterruptedException ex) {/*NOP*/}
            try {
                if (selector.selectNow() == 0) continue;
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                if (key.isReadable()) {
                    mediator.notify(this, new Segment((SocketChannel) key.channel(),Markers.READ));
                }
                //if connection was interrupted, the key has been deleted,so we should pass the writable check.
                if(!key.isValid()) continue;
                if (key.isWritable()) {
                    mediator.notify(this, new Segment((SocketChannel) key.channel(), Markers.WRITE));
                }
                iter.remove();
            }
        }
    }

    /**
     * Метод закрывающий сокет(поток сокета) и завершение работы приложения.
     * @throws IOException
     */
    public void stopAndClose() {
        try {
            socketChannel.shutdownInput();
            socketChannel.shutdownOutput();
            socketChannel.close();
        }catch (IOException e) {
            new IOException("Dropped an exception during closing socket.",e);
        }finally {
            System.exit(0);
        }
    }
}
