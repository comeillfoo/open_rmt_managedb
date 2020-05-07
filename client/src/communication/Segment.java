package communication;

import instructions.rotten.RawDecree;

import java.nio.channels.SocketChannel;

/**
 * Класс, объект которого используются для перемещения информации между модулями
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
//возможно логичнее было бы освободить этот класс от socketChannel и отправлять объекты этого класса,чтобы не плодить лишний класс.
public class Segment {
    private String[] stringData;
    private RawDecree commandData;
    private communication.ClientPackage clientPackage;
    private Junker organizationData;
    private SocketChannel socketChannel;
    private Markers marker;

    public Segment(Markers marker){ this.marker = marker; }
    public Segment(SocketChannel socketChannel, Markers marker){ this(marker); this.socketChannel = socketChannel;}

    public void setMarker(Markers marker) { this.marker = marker; }
    public Markers getMarker() { return marker; }

    public void setCommandData(RawDecree commandData) { this.commandData = commandData; }
    public void setStringData(final String[] stringData) { this.stringData = stringData; }

    public void setClientPackage(communication.ClientPackage clientPackage) { this.clientPackage = clientPackage; }

    public SocketChannel getSocketChannel() { return this.socketChannel; }
    public String[] getStringData() { return stringData; }
    public RawDecree getCommandData() { return commandData;}
    public communication.ClientPackage getClientPackage() { return clientPackage;}

    public communication.ClientPackage prepareDataObject() { return new ClientPackage(commandData,organizationData,stringData[0]);}
}
