1. final-классы: нужны, чтобы не было соблазна их расширять,
так как их и так сложно поддерживать. В этой лабораторной их
расширять не нужно, но если в следующих появится такая надобность, то
уберем.
2. final-методы: нужны, чтобы, если у классов и будет возможность наследоваться,
то наследник не сможет переопределить методы, тем самым начать передавать
в функции всякую чушь. Сильно должно помочь с 7 лабораторной на многоточности.
Реализуется концепция Immutable object (3 лекция Гаврилова про многопоточность).
3. final-поля: позволяют критически не изменять состояние объекта.
Концепция - хочешь изменить - создавай новый. Типичный пример - класс java.lang.String.