package communication;

import сlient.Client;

import java.io.IOException;

/**
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public interface Mediating {
    void notify(Component component, Segment parcel) throws IOException;
    Client getClient();
}
