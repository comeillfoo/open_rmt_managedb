package сlient;

import communication.Component;
import communication.Mediating;
import communication.Segment;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Абстрактный класс задающий основу для класса взаимодействия с клиентом.
 * Также этот класс принимает ввод от юзера.
 *  @author Come_1LL_F00 aka Lenar Khannanov
 *  @author Leargy aka Anton Sushkevich
 * @see Component
 */
public abstract class AServant implements Component {
    protected Client client;
    protected final Mediating mediator;
    protected final Scanner scanner;
    protected final InputStream pipeIn;
    protected final PrintStream pipeOut;

    /**
     * Конструктор принимающий ссылку на посредника, инициализирующий точки In и Out, а также Scanner.
     * @param mediator
     */
    public AServant(Mediating mediator) {
        this.mediator = mediator;
        pipeIn = System.in;
        pipeOut = System.out;
        scanner = new Scanner(pipeIn);
    }
    /**
     * Метод, принимающий сообщение от клиента и уведомление посредника для выполнения следующего действия.
     * @param parcel
     */
    public void order(Segment parcel) { }
    /**
     * метод установки соединения
     * @return boolean
     */
    public boolean setConnection() { return false;}
    /**
     * Метод, реализующий переустановку подключения к серверу.
     * @return boolean
     * @throws IOException
     */
    public boolean resetConnection() { return false;}
    /**
     * Метод для общения с клиентом.
     * @return String
     */
    public String debrief() { return "";}
    public void notification(Segment parcel) { }
}