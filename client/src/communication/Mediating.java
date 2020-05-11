package communication;

import сlient.Client;

import java.io.IOException;

/**
 * Интерфэйс для объекта посредника (Mediator'а)
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public interface Mediating {
    void notify(Component component, Segment parcel) throws IOException;
    Client getClient();
}
