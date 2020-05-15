package dispatching.validators;

import communication.Segment;
import dataSection.Commands;
import entities.JunkerCreator;
import exceptions.CommandSyntaxException;
import instructions.rotten.base.*;
import instructions.rotten.extended.*;
import instructions.rotten.RawDecree;

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
    private final HashMap<String,String> commandMap;

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
           // throw new CommandSyntaxException("Command should have at list one argument!");
        }

        switch (parcel.getStringData()[0]) {
            case RawExecuteScript.NAME: return new RawExecuteScript(stringArgument);
            case RawFilterContainsName.NAME: return new RawFilterContainsName(stringArgument);
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
        for (Map.Entry<String, String> entry : commandMap.entrySet()) {
            key = entry.getKey().split(" ")[0];
            matcher = argumentCommandPattern.matcher(entry.getKey());
            if (key.equals(tempCommand)) {
                if (matcher.find()) {
                    //взываем к конструктору junker'а
                    switch (key) {
                        case RawInsert.NAME:
                            return new RawInsert(intArgument,junkerCreator.prepareJunker());
                        case RawUpdate.NAME:
                            return new RawUpdate(intArgument,junkerCreator.prepareJunker());
                        case RawRemoveLower.NAME:
                            return new RawRemoveLower(junkerCreator.prepareJunker());
                        case RawReplaceIfLower.NAME:
                            return new RawReplaceIfLower(intArgument,junkerCreator.prepareJunker());
                        case RawReplaceIfGreater.NAME:
                            return new RawReplaceIfGreater(intArgument,junkerCreator.prepareJunker());
                    }
                } else {
                    switch (key) {
                        case RawRemoveKey.NAME:
                            return new RawRemoveKey(intArgument);

                    }
                }
            }
        }
        return null;
    }
}
