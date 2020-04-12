import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Client {
    private Selector selector;
    private SocketChannel clientSocket;
    private OutputStream out;
    private InputStream in;

    public Client(SocketChannel clientSocket) throws IOException {
        selector = Selector.open();
        this.clientSocket = clientSocket;
        out = Channels.newOutputStream(clientSocket);
        in = Channels.newInputStream(clientSocket);
        /*
        clientSocket.configureBlocking(false);
        clientSocket.register(selector, SelectionKey.OP_READ);

         */
    }

    public String startConversation(String message) throws IOException { //возможно метод общения с сервером будет изменен.
        byte[] bytes =message.getBytes();
        out.write(bytes);
        out.flush();
        ByteBuffer.wrap(bytes).clear();

        in.read(bytes);

        return  new String(bytes,"UTF-8");
    }

    public void stopConnection() throws IOException { //задаток под exit мб...
        in.close();
        out.close();
        clientSocket.close();
    }

}
