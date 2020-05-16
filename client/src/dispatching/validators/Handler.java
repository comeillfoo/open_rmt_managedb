package dispatching.validators;

import communication.Segment;
import exceptions.CommandSyntaxException;
import instructions.rotten.RawDecree;

/**
 * Интерфейс необходимый для реализации шаблона "цепочка обязанностей"
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 */
public interface Handler {
    /**
     * метод задающее следующее звено проверки.
     * @param handler
     */
    void setNext(Handler handler);

    /**
     * метод содержащий основную логику проверки.
     * @param parcel объект необходимый для пересылки информации между модулями.
     * @return RawDecree объект сырой команды.
     * @throws CommandSyntaxException эксепшн, если во премя проверки выявлено нарушение.
     */
    RawDecree handle(Segment parcel) throws CommandSyntaxException;
}
