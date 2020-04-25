package dispatching.validate;

import communication.Segment;

/**
 * Звено проверки аргументов команд
 */
public class ArgumentHandler extends DataHandler{

    @Override
    public boolean handle(Segment parcel) {
        if (parcel.getStringData().length <=1) {System.err.println("Команда должна содержать аргумент!"); return false;}
        switch (parcel.getStringData()[0]){
            case "insert":
            case "update":
            case "remove_key":
            case "replace_if_greater":
            case "replace_if_lowe":
                try {
                    if (Integer.valueOf(parcel.getStringData()[1]) >= 0)  return true;
                }catch (NumberFormatException e) {
                }
                System.err.println("Аргумент должен быть целым неотрицательным числом!");
                return false;
        }
        return false;
    }
}
