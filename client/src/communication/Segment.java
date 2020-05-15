package communication;

import dataSection.enumSection.Markers;
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
    private ClientPackage clientPackage;
    private SocketChannel socketChannel;
    private Markers marker;

    /**
     * @param marker
     */
    public Segment(Markers marker) { this.marker = marker; }

    /**
     * @param socketChannel
     * @param marker
     */
    public Segment(SocketChannel socketChannel, Markers marker) {
        this(marker);
        this.socketChannel = socketChannel;
    }

    /**
     * озволяет
     * @param marker
     */
    public void setMarker(Markers marker) { this.marker = marker; }

    /**
     * @param stringData
     */
    public void setStringData(final String[] stringData) { this.stringData = stringData; }

    /**
     * @param commandData
     */
    public void setCommandData(RawDecree commandData) { this.commandData = commandData; }

    /**
     * @param clientPackage
     */
    public void setClientPackage(ClientPackage clientPackage) { this.clientPackage = clientPackage; }

    /**
     * @return SocketChannel
     */
    public SocketChannel getSocketChannel() { return this.socketChannel; }

    /**
     * @return ClientPackage
     */
    public ClientPackage getClientPackage() { return clientPackage; }

    /**
     * @return RawDecree
     */
    public RawDecree getCommandData() { return commandData; }

    /**
     * @return String[]
     */
    public String[] getStringData() { return stringData; }

    /**
     * @return Markers
     */
    public Markers getMarker() { return marker; }

    /**
     * @return ClientPackage
     */
    public ClientPackage prepareDataObject() { return new ClientPackage(commandData); }
}
