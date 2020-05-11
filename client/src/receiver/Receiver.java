package receiver;

import communication.ClientPackage;
import dataSection.enumSection.Markers;
import communication.Mediating;
import communication.Segment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

/**
 * Класс получатель, в метод принимает посылку от сервера и десериализует в пригодный для обработки вид.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see AReceiver
 */
public class Receiver extends AReceiver{
    private ByteArrayInputStream byteArrayInputStream;
    private ObjectInputStream objectInputStream;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(2*1024);

    public Receiver(Mediating mediator){
        super(mediator);
    }

    /**
     * метод receive() - метод класса родителя, ответственене за десериализацию.
     * @param parcel
     */
    @Override
    public void receive(Segment parcel) {
        byteBuffer.clear();
        try {
            if(parcel.getSocketChannel().read(byteBuffer) == -1) {
                throw new IOException();
            }
            byteBuffer.flip();
            byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            ClientPackage query = (ClientPackage) objectInputStream.readObject();
            parcel.setClientPackage(query);
            mediator.notify(this,parcel);
        }catch (IOException e) {
            parcel.setMarker(Markers.INTERRUPTED);
            System.err.println("─────Connection interrupted─────");
            mediator.notify(this,parcel);
            //TODO:write an handling to this type of error
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            //TODO:write an handling to this type of error
        }
    }
}
