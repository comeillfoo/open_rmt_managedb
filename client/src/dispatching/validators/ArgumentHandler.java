package dispatching.validators;

import communication.Segment;
import dataSection.Commands;
import entities.JunkerCreator;
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
 * Звено проверки аргументов команд.Реализация паттерна "Цепочка обязанностей" (Chain of Responsibility)
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class ArgumentHandler extends DataHandler{
    private HashMap<String,RawDecree> commandMap;

    /**
     * Конструктор принимающий список команд относительно которых будет производиться проверка.
     * @param commandList
     */
    public ArgumentHandler(Commands commandList){
        commandMap = commandList.getCommandMap();
        junkerCreator = new JunkerCreator();
    }

    /**
     * Метод седержащий логику проверки аргумента.
     * @param parcel
     * @return RawDecree
     * @throws CommandSyntaxException
     */
    @Override
    public RawDecree handle(Segment parcel) throws CommandSyntaxException {
        String tempCommand = parcel.getStringData()[0];

        String stringArgument = "";
        try {
            stringArgument = parcel.getStringData()[1];
            if (parcel.getStringData().length > 2) {
                throw new CommandSyntaxException("Wrongly entered argument part of command!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandSyntaxException("Command should have at list one argument!");
        }

        Integer intArgument = null;
        try {
            intArgument = Integer.valueOf(stringArgument);
            if(intArgument < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            throw new CommandSyntaxException("Entered argument should be a positive integer!");
        }
        //паттерн регулярного выражения для определния,что для комманды необходимо получить дополнительне параметры
        Pattern argumentCommandPattern = Pattern.compile(".*\\{.+}");
        Matcher matcher = null;
        String key = "";
        for (Map.Entry<String, RawDecree> entry : commandMap.entrySet()) {
            key = entry.getKey().split(" ")[0];
            matcher = argumentCommandPattern.matcher(entry.getKey());
            if (key.equals(tempCommand)) {
                if (matcher.find()) {
                    //взываем к конструктору junker'а
                    switch (key) {
                        case RawInsert.NAME:
                            return new RawInsert().setKey(intArgument).setData(junkerCreator.prepareJunker());
                        case RawUpdate.NAME:
                            return new RawUpdate().setKey(intArgument).setData(junkerCreator.prepareJunker());
                        case RawRemoveLower.NAME:
                            return new RawRemoveLower().setData(junkerCreator.prepareJunker());
                        case RawReplaceIfLower.NAME:
                            return new RawReplaceIfLower().setKey(intArgument).setData(junkerCreator.prepareJunker());
                        case RawReplaceIfGreater.NAME:
                            return new RawReplaceIfGreater().setKey(intArgument).setData(junkerCreator.prepareJunker());
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
