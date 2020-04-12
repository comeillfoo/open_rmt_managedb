import com.sun.org.apache.bcel.internal.generic.Select;
import org.ietf.jgss.ChannelBinding;
import sun.nio.ch.ChannelInputStream;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Server  {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    private OutputStream clientReply; //По заданию должен быть общение с клиентом с помощью IOStream, поэтому используются именно эти классы.
    private InputStream clientRequest;


    private ByteBuffer byteBuffer = ByteBuffer.allocate(256);

    public Server(int port) throws IOException {
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

    public void echo() throws SocketException { //да это же эхо (сука спустя 2 дня...)
        byte[] bytes = new byte[256];

        try {
            clientRequest.read(bytes);
        }catch (IOException e){
        }
        try {
            System.out.println(new String(bytes,"UTF-8"));
            clientReply.write(bytes);
            clientReply.flush();
            System.out.println();
            ByteBuffer.wrap(bytes).clear();
        }catch (IOException e){
        }
    }




        /*clientRequest.read();

      //System.out.println("message has recieved!:" + requestInBytes);//пока-что смотри,что наш сервак получает сообщения.
      //System.out.println(" " + new String(requestInBytes,"UTF-8")); //дешифруем по бырому.


      replayToClient.write(requestInBytes);

      replayToClient.flush();

         */
    }
