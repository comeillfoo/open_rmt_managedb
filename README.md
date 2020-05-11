# remoteManageDB
  Continue of project that manage the collection of elements. 
  But now the collection is kept on the remote server, and client work with it by connection by `TCP` protocol.

## TT (En)
  * сlient.сlient app should use ***SocketChannel*** as data exchange sistem.
  * systemcore.Server app should use ***IOStream*** as data exchange sistem.
  * Network channels should be used in ***non-blocking*** mode
  * Object processing operations should be done with ***Stream API*** with lambda expression.
  * сlient.сlient must correctly handle the temporary unavailability of the server.
  * Objects should be sent to server in serialized form.
  * Objects in collection sent to the client should be sorted by name.
  * Remove `save` command from client app.
  
  ## !Attention!
  `Commands and their arguments must be class objects. The exchange of "simple" lines is not allowed. So, for the add command or its analogue, it is necessary to form an object containing the type of command and the object that should be stored in your collection.`

  ### systemcore.Server modules.
   * Connection-accepter modul
   * Request modul
   * Command-handler modul
   * Reply modul

  ### systemcore.Server responsibilities:
   * Working with the file storing collections.
   * Object collection managment.
   * Fields auto-generating managment.
   * Waiting for client connections and requests.
   * Handling clients requests.
   * Saving collections into file when app stopped or with the special command available only on server(client can't send this command).
   ### сlient.сlient responsibilities:
   * Reading comands from console.
   * Data validation.
   * Command and arguments serrialisation.
   * Sending command with arguments to the server.
   * systemcore.Server's reply handling (sending result to console).
   * `exit` command should complete working of app.
   
   ### Extra task:
`Implement logging of various stages of the server (start of work, receiving a new connection, receiving a new request, sending a response, etc.) using`**`Logback`**`.`
   
    

## ТЗ (Rus)
  * Для обмена данными на клиенте необходимо использовать ***сетевой канал***.
  * Для обмена данными на сервере необходимо использовать ***потоки ввода-вывода***.
  * Сетевые каналы должны использоваться в ***неблокирующем*** режиме.
  * Операции обработки объектов коллекции должны быть реализованы с помощью ***Stream API*** с использованием лямбда-выражений.
  * Клиент должен корректно обрабатывать временную недоступность сервера.
  * Объекты между клиентом и сервером должны передаваться в сериализованном виде.
  * Объекты в коллекции, передаваемой клиенту, должны быть отсортированы по названию.
  * Команду `save` из клиентского приложения необходимо убрать.
  ## !Внимание! 
  `Команды и их аргументы должны представлять из себя объекты классов. Недопустим обмен "простыми" строками. Так, для команды add или её аналога необходимо сформировать объект, содержащий тип команды и объект, который должен храниться в вашей коллекции.`

  ### Модули серверного приложения.
   * Модуль приёма подключений.
   * Модуль чтения запроса.
   * Модуль обработки полученных команд.
   * Модуль отправки ответов клиенту.

  ### Обязанности серверного приложения:
   * Работа с файлом, хранящим коллекцию.
   * Управление коллекцией объектов.
   * Назначение автоматически генерируемых полей объектов в коллекции.
   * Ожидание подключений и запросов от клиента.
   * Обработка полученных запросов (команд).
   * Сохранение коллекции в файл при завершении работы приложения или при исполнении специальной команды, доступной только серверу (клиент такую команду отправить не может).
   
   ### Обязанности клиентского приложения:
   * Чтение команд из консоли.
   * Валидация вводимых данных.
   * Сериализация введённой команды и её аргументов.
   * Отправка полученной команды и её аргументов на сервер.
   * Обработка ответа от сервера (вывод результата исполнения команды в консоль).
   * Команда `exit` завершает работу клиентского приложения.
   ### Дополнительное задание:
`Реализовать логирование различных этапов работы сервера (начало работы, получение нового подключения, получение нового запроса, отправка ответа и т.п.) с помощью`**`Logback`**`.`
