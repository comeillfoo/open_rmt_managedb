package communication;

import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Класс, объект которого используются для перемещения информации между модулями
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
//возможно логичнее было бы освободить этот класс от socketChannel и отправлять объекты этого класса,чтобы не плодить лишний класс.
public class Segment {
    private String[] stringData;
    private Serializable dataObject;
    private SocketChannel socketChannel;

    public Segment(String[] stringData){
        this.stringData = stringData;
    }
    public Segment(SocketChannel socketChannel,String[] stringData){ this(stringData); this.socketChannel = socketChannel;}

    public void setDataObject(final Serializable dataObject) {
        this.dataObject = dataObject;
    }
    public void setStringData(final String[] stringData) {
        this.stringData = stringData;
    }

    public Serializable getDataObject() { return new ClientPackage(stringData, dataObject); }
    public SocketChannel getSocketChannel() { return this.socketChannel; }
    public String[] getStringData() {
        return stringData;
    }
}
