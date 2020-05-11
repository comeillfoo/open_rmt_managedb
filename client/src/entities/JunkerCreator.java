package entities;

import dataSection.enumSection.OrganizationType;
import entities.Junker;
import dispatching.validators.Filters;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс, организующий получение информации
 * и послеющего формирования массива данных для дополнительного параметра комманд.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class JunkerCreator {
    private Scanner scanner;
    /**
     * Конструктор, инициализирующий Scanner
     */
    public JunkerCreator() {
        scanner = new Scanner(System.in);
    }
    /**
     * Основной метод, задающий последовательность опроса клиента и последующиее формирования массивов данных доп параметра.
     * @return Junker класс содержащий доп информацию для доп. параметра.
     */
    public Junker prepareJunker() {
        Junker coordinates = null;
        Junker officialAddress = null;
        OrganizationType organizationType =null;
        float annualTurnover = 0f; int employeesCount = 0;
        String name = "";
        String fullName = "";

        name = Filters.scanLine((line) -> (!line.trim().equals("")),"Enter a string Organization.name:",scanner);
        fullName = Filters.scanLine((line) ->(true),"Enter a string Organization.fullname:",scanner);
        coordinates = getCoordinates();
        annualTurnover = Filters.scanDouble((number) -> (number > 0), "Enter a float fraction Organization.annualTurnover:",scanner).floatValue();
        employeesCount = Filters.scanLong((number) -> (number > 0), "Enter an integer Organization.employeesCount:",scanner).intValue();
        organizationType = getOrganizationType();
        officialAddress = getAddres();

        return new Junker(new long[]{(long) employeesCount},
                new double[]{(double) annualTurnover},
                new String[]{name,fullName,organizationType.toString()},
                new Junker[]{coordinates,officialAddress});
    }

    /**
     * Метод, организующий формирование массива данных о кординатах организации.
     * @return Junker класс содержащий доп информацию для доп. параметра.
     */
    protected Junker getCoordinates() {
        Long x = null;
        Double y = null;
        x = Filters.scanLong((number) -> (true),"Enter an integer Coordinates.x:",scanner);
        y = Filters.scanDouble((number) -> (number > -538),"Enter a float Coordinates.y:",scanner);
        return new Junker(new long[]{x}, new double[]{y},null, null);
    }

    /**
     * Метод, организующий формирование массива данных об адрессе организации.
     * @return Junker класс содержащий доп информацию для доп. параметра.
     */
    protected Junker getAddres() {
        String zipCode = (Filters.scanLine((cod) -> (true), "Enter a string Address.zipCode: ",scanner)).toString();
        Junker town = getLocation();
        return new Junker(null,null,new String[]{zipCode},new Junker[]{town});
    }

    /**
     * Метод, организующий формирование массива данных о месте расположения организации.
     * @return Junker класс содержащий доп информацию для доп. параметра.
     */
    protected Junker getLocation() {
        Double x = null;
        int y = 0;
        float z = 0;
        x = Filters.scanDouble((number) -> (true), "Enter a long integer Location.x: ",scanner);
        y = Filters.scanLong((number) -> (true), "Enter a long integer Location.y: ",scanner).intValue();
        z = Filters.scanDouble((number) -> (true), "Enter a double fraction Location.z: ",scanner).floatValue();
        return new Junker(new long[]{y}, new double[]{x,z},null,null);
    }

    /**
     * Метод, организующий формирование массива данных о типе организации.
     * @return Junker класс содержащий доп информацию для доп. параметра.
     */
    protected OrganizationType getOrganizationType() {
        OrganizationType organizationType = null;
        String clientSuggestion = "";
        do {
            System.out.print(OrganizationType.getValues());
            clientSuggestion = Filters.scanLine((line) -> (true),"Your organization type:",scanner);
            for (OrganizationType tempType : OrganizationType.values()) {
                if (tempType.toString().equals(clientSuggestion.toUpperCase())) {
                    organizationType = tempType;
                }
            }
            if (organizationType == null) System.err.println("Entered invalid organization type.");
        }while (organizationType == null);
        return organizationType;
    }
}

