package dispatching.validate;

import communication.Segment;
import dataSection.CommandList;

/**
 * Звено проверки валидности введенной команды.В случае несовпадения - прерывает цепочку проверок
 */
public class CommandHandler extends DataHandler{
    private String[] notArgumentedCommandList;
    private String[] argumentedCommandList;

    //конструктор принимает Объект подразумевающий содержание списка доступных для клиента команд на сервере
    public CommandHandler(CommandList commandList){
        notArgumentedCommandList = commandList.getNotArgumentedCommandList();
        argumentedCommandList = commandList.getArgumenterCommandList();
    }

    @Override
    public boolean handle(Segment parcel) {
        String tempCommand = parcel.getStringData()[0];
        if(tempCommand.equals("")) return false;

        for(int i = 0; i < notArgumentedCommandList.length; i++){ //проверка на схожесть с командами не принимающие аргументов
            if (tempCommand.equals(notArgumentedCommandList[i]) ) {
                if (parcel.getStringData().length > 1) {
                    System.err.println("Данная команда не принимает агрументов!");
                    return false;
                }
                return true;
            }
        }

        for (int i = 0; i < argumentedCommandList.length; i++){
            if (tempCommand.equals(argumentedCommandList[i])) return super.handle(parcel);
        }

        System.err.println("Неправильно введена команда или введеная вами команда не существует!");
        return false;
    }
}
