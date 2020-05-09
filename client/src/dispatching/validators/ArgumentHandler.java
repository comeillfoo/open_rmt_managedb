package dispatching.validators;

import communication.Segment;
import dataSection.Commands;
import exceptions.CommandSyntaxException;
import instructions.rotten.RawDecree;
import instructions.rotten.extended.RawInsert;
import instructions.rotten.extended.RawRemoveKey;
import instructions.rotten.extended.RawUpdate;
import instructions.rotten.extended.RawFilterContainsName;
import instructions.rotten.extended.RawRemoveLower;
import instructions.rotten.extended.RawReplaceIfGreater;
import instructions.rotten.extended.RawReplaceIfLower;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Звено проверки аргументов команд
 */
public class ArgumentHandler extends DataHandler{
    private HashMap<String,RawDecree> commandMap;
    public ArgumentHandler(Commands commandList){
        commandMap = commandList.getCommandMap();
    }

    @Override
    public RawDecree handle(Segment parcel) throws CommandSyntaxException {
        String tempCommand = parcel.getStringData()[0];

        String stringArgument = "";
        try {
            stringArgument = parcel.getStringData()[1];
            if (parcel.getStringData().length > 2) {
                throw new CommandSyntaxException("Неверно введена аргументная часть команды!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandSyntaxException("Команда должна содержать аргумент!");
        }

        Integer intArgument = null;
        try {
            intArgument = Integer.valueOf(stringArgument);
            if(intArgument < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            throw new CommandSyntaxException("Аргумент должен быть целым неотрицательным числом!");
        }
        //паттерн регулярного выражения для определния,что для комманды необходимо получить дополнительне параметры
        Pattern argumentCommandPat = Pattern.compile(".*\\{.+}");
        Matcher matcher = null;
        String key = "";
        for (Map.Entry<String, RawDecree> entry : commandMap.entrySet()) {
            key = entry.getKey().split(" ")[0];
            matcher = argumentCommandPat.matcher(entry.getKey());
            if (key.equals(tempCommand)) {
                if (matcher.find()) {
                    //взываем к конструктору junker'а
                    switch (key) {
                        case RawInsert.NAME:
                            return new RawInsert().setKey(intArgument).setData(cook.cookMe());
                        case RawUpdate.NAME:
                            return new RawUpdate().setKey(intArgument).setData(cook.cookMe());
                        case RawRemoveLower.NAME:
                            return new RawRemoveLower().setData(cook.cookMe());
                        case RawReplaceIfLower.NAME:
                            return new RawReplaceIfLower().setKey(intArgument).setData(cook.cookMe());
                        case RawReplaceIfGreater.NAME:
                            return new RawReplaceIfGreater().setKey(intArgument).setData(cook.cookMe());
                    }
                } else {
                    switch (key) {
                        case RawRemoveKey.NAME:
                            return ((RawRemoveKey) entry.getValue()).setKey(intArgument);
                        case RawFilterContainsName.NAME:
                            return ((RawFilterContainsName) entry.getValue()).setKey(stringArgument);
                    }
                }
            }
        }
        return null;
    }
}
