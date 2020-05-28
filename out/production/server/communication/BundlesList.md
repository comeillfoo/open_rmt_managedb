Число преобразований в ходе работы сервера:
1. Байты (_нужен Stream_) -> Выполняемая команда (Reader);
2. Выполняемая команда -> Запрос1 (Reader): та же Команда, но с каналом Клиента;
3. Запрос1 -> Запрос2 (Resolver): Отчет об успешности обработки с каналом Клиента;
4. Отправка Отчета Клиенту (Dispatcher);

класс Запрос1: 

    поля Клиент, Команда
   
класс Запрос2:

    поля Клиент, Отчет
    
Пока решено объеденить классы Запрос1 и Запрос2, 
в один общий Segment, который хранит и Команды и Отчеты, как Serializable.