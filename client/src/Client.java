import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    private SocketChannel clientSocket;
    private OutputStream out;
    private InputStream in;
    private Scanner scanner;

    public Client(SocketChannel clientSocket) {
        this.clientSocket = clientSocket;
        out = Channels.newOutputStream(clientSocket);
        in = Channels.newInputStream(clientSocket);
    }

    public String startConversation(String message) throws IOException { //возможно метод общения с сервером будет изменен.
        byte[] bytes =message.getBytes();
        out.write(bytes);
        out.flush();
        byte[] answer = new byte[32*1024];
        in.read(answer);
        return  new String(answer,"UTF-8");
    }

    public void stopConnection() throws IOException { //задаток под exit мб...
        in.close();
        out.close();
        clientSocket.close();
    }

}
