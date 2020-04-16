import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
public class Server  {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    private OutputStream clientReply; //По заданию должен быть общение с клиентом с помощью IOStream, поэтому используются именно эти классы.
    private InputStream clientRequest;


    private ByteBuffer byteBuffer = ByteBuffer.allocate(256);

    public Server(int port) throws IOException { //TODO: прикрутить обработку разрыва соединения прокси сервера с основным сервером
        SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName("localhost"),port);
        serverSocket = new ServerSocket();
        serverSocket.bind(socketAddress);
    }

    public void start() throws IOException {
        clientSocket = serverSocket.accept();
        System.out.println("Connection has been set! \n Client ip:" + clientSocket.getInetAddress() + "\n Client port:" + clientSocket.getPort());
        //достаем потоки по которым идет общение с клиентом.
        clientReply = clientSocket.getOutputStream();
        clientRequest = clientSocket.getInputStream();
    }

    public void echo(){ //да это же эхо (PS. сука спустя 2 дня...) (PPS. упс уже спустя 3 дня)
        byte[] bytes = new byte[256];   //TODO:соблюдая модульность сервера,прикрутить проверку на схожеть с одной из команд и ничего не сломать

        try {
            clientRequest.read(bytes);
            if(bytes[0] == 0){
                return;
            }
        }catch (IOException e){
        }
        try {
            System.out.println( new String(bytes,"UTF-8"));
            clientReply.write(bytes);
            clientReply.flush();
        }catch (IOException e){
        }
    }
}
