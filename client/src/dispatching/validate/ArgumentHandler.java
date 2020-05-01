package dispatching.validate;

import communication.Mediating;
import communication.Segment;
import dataSection.CommandList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Звено проверки аргументов команд
 */
public class ArgumentHandler extends DataHandler{
    private String[] argumentedCommandList;

    public ArgumentHandler(CommandList commandList){
        argumentedCommandList = commandList.getArgumenterCommandList();
    }
    public ArgumentHandler(CommandList commandList, Mediating mediator){
        super();
        this.mediator = mediator;

    }

    @Override
    public boolean handle(Segment parcel) {
        if (parcel.getStringData().length > 2){
            System.err.println("Неверно введена аргументная часть команды!");
            return false;
        }
        if (parcel.getStringData().length <= 1) {System.err.println("Команда должна содержать аргумент!"); return false;}
        Pattern pattern = Pattern.compile("/[.*/]");
        Matcher matcher = pattern.matcher(parcel.getStringData()[1]);

        try {
            if (Integer.valueOf(parcel.getStringData()[1]) >= 0) {
                if (matcher.find()) {
                    // запрос к медиатору на создание Объекта коллекции
                    return true;
                } else return true;
            }else {throw new NumberFormatException();}
        }catch (NumberFormatException e) {
            System.err.println("Аргумент должен быть целым неотрицательным числом!");
            return false;
        }

//            case "insert":
//            case "update":
//            case "remove_key":
//            case "replace_if_greater":
//            case "replace_if_lowe":
    }
}
