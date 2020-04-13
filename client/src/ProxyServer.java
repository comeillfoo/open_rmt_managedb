import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;


public class ProxyServer {
    private Selector selector;

    private final int proxyPort = 6969;  //
    private ServerSocketChannel proxySC; //это пренадлежит прокси


    private Socket bs;               //
    private SocketChannel bsc;       //это все принажлежит клиенту

    private SocketChannel scToServer; //
    private Socket sToServer;         //это пренажлежит главному серверу

    private ByteBuffer requestInBytes = ByteBuffer.allocate(32*1024); //это еще возможно понадобится

    public ProxyServer() throws IOException {
        //TODO:прикрутить обработку неработы основного сервера и если клиент подключен к прокси-направить ему сообщение о недоступности сервера.
        //Инициализация запуска прокси сервера и регистрация канала его совета в селекторе
        selector = Selector.open();
        SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName("localhost"),proxyPort);
        proxySC = ServerSocketChannel.open();
        proxySC.bind(socketAddress);
        proxySC.configureBlocking(false);
        proxySC.register(selector,SelectionKey.OP_ACCEPT);
        connectToServer();
    }
    public int getProxyPort() {
        return proxyPort;
    }
    public void connectToServer() throws IOException {
        scToServer =SocketChannel.open(new InetSocketAddress(InetAddress.getByName("localhost"),2222));
    }

    public void proxyStart() throws IOException {
        while (true){
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()){

                SelectionKey key = iter.next();

                if(key.isAcceptable()){
                    register();
                }
                if(key.isReadable()){
                    readMessage(key);
                }
                iter.remove();
            }
        }
    }
    private void register() throws IOException {
        bs = proxySC.socket().accept();

        System.out.println("Proxy: connection with the client has been set! \n Client ip:" + bs.getInetAddress() + "\n Client port:" + bs.getPort());

        bs.getChannel().configureBlocking(false);
        bs.getChannel().register(selector,SelectionKey.OP_READ);

    }

    private void readMessage(SelectionKey key) throws IOException {
        byte[] bytes = new byte[32*1024];
        try {
            ((SocketChannel) key.channel()).read(ByteBuffer.wrap(bytes));
            if(key.channel().equals(scToServer)){
                System.out.println("Server reply:" + new String(bytes,"UTF-8")); //просто промежуточный контроль того,что отправляется
                ((SocketChannel) key.attachment()).write(ByteBuffer.wrap(bytes));
                ByteBuffer.wrap(bytes).clear();
                return;
            }
            System.out.println("Client request:" + new String(bytes,"UTF-8")); //просто промежуточный контроль того,что отправляется
            scToServer.write(ByteBuffer.wrap(bytes));
            scToServer.configureBlocking(false);
            scToServer.register(selector,SelectionKey.OP_READ,key.channel());

            ByteBuffer.wrap(bytes).clear();

        }catch (IOException e){
            (key.channel()).close();
        }

    }
    /*
    public ProxyServer() throws IOException {
        selector = Selector.open();

        SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName("localhost"),proxyPort);

        bssc = ServerSocketChannel.open();
        bssc.bind(socketAddress);
        bssc.configureBlocking(false);
        bssc.register(selector, SelectionKey.OP_ACCEPT);
    }



    public void mainServerConnectionSet(int mainServerPort) throws IOException {
        scToServer = SocketChannel.open(new InetSocketAddress(InetAddress.getByName("localhost"),mainServerPort)); //Устанавливаем соединение с основынм сервером
        scToServer.configureBlocking(false);
        scToServer.register(selector,SelectionKey.OP_READ);//Добавляем основной сервер на прослушивание сообщений.
    }

    public void start() throws IOException {
        System.out.println("Proxt-сервер был установлен на прослушивание подключений/сообщений");
        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {

                SelectionKey key = iter.next();
                if (key.isAcceptable()) {
                    register(selector, bssc);
                }
                if (key.isWritable()){
                    replayment(key);
                }

                if (key.isReadable()) {
                    request(key);
                }
                iter.remove();
            }
        }

    }
    private void register(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException {

        bs = serverSocketChannel.socket().accept();

        System.out.println("Connection has been set! \n Client ip:" + bs.getInetAddress() + "\n Client port:" + bs.getPort());

        bsc = bs.getChannel();
        bsc.configureBlocking(false);
        bsc.register(selector,SelectionKey.OP_READ,scToServer);

    }
    private void request(SelectionKey key) throws IOException {
        byte[] bytes = new byte[32*1024];
        ((SocketChannel)key.channel()).read(ByteBuffer.wrap(bytes));

        if (new java.lang.String(bytes,"UTF-8").replaceAll(" ","") !=""){
            replay(key,bytes);
        }else {
            ByteBuffer.wrap(bytes).clear();
        }
    }

    private void replay(SelectionKey key, byte[] bytes) throws IOException {
        if(key.attachment() == null ){
            requestInBytes.put(bytes);
            bsc.register(selector,SelectionKey.OP_WRITE,bytes);
        }else{
            ((SocketChannel) key.attachment()).write(ByteBuffer.wrap(bytes));
        }
    }

    private void replayment(SelectionKey key) throws IOException {
        ((SocketChannel)key.channel()).write(ByteBuffer.wrap((byte[]) key.attachment()));
        requestInBytes.clear();
        bsc.register(selector,SelectionKey.OP_READ,scToServer);
    }

    @Override
    public java.lang.String toString() {
        return "ProxyServer{ip:" + bs.getInetAddress().getHostAddress()+" port:" + proxyPort + "}";
    }

     */

}
