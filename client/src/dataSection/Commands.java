package dataSection;

import communication.Segment;

public interface Commands {
    String[] getArgumenterCommandList();
    String[] getNotArgumentedCommandList();
    void setList(Segment parcel);
}
