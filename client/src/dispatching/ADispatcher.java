package dispatching;

import communication.Component;
import communication.Segment;

import java.io.IOException;

/**
 * Абстрактный класс модуля отправки сообщений на сервер.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Component
 */
public abstract class ADispatcher implements Component {

    /**
     * метод в котором полученное сообщение от клиента отправляется на проверку
     * в случии валидности возвращается объект сырой команды,
     * заполненный необходимый для ее иполнения информацией.
     * @param parcel объект необходимый для пересылки информации между модулями.
     * @throws IOException
     */
    public void giveOrder(Segment parcel) throws IOException {}
}
