package dataSection;

import java.time.ZonedDateTime;

public class Organization {/*implements Mappable<Integer> {

    private static int count = 1;

    private final int id; // generates automatically

    private final String name;

    public String getName() { return name; }

    private final Coordinates coordinates;

    public Coordinates getCoordinates() { return coordinates; }

    private final ZonedDateTime creationDate = ZonedDateTime.now();


    public ZonedDateTime getCreationDate() { return creationDate; }


    private final float annualTurnover;


    public float getAnnualTurnOver() { return annualTurnover; }

    private final String fullname;

    public String getFullname() { return fullname; }


    private final int employeesCount;


    public int getEmployees() { return employeesCount; }


    private final OrganizationType type;


    public OrganizationType getType() { return type; }

    private final Address officialAddress;


    public Address getAddress() { return officialAddress; }

    public Organization() {
        name  = "Sample Organization";
        coordinates = new Coordinates();
        annualTurnover = 0;
        fullname = "sample organization pattern";
        employeesCount = 0;
        type = OrganizationType.PUBLIC;
        officialAddress = new Address();
        id = hashCode() + count++;
    }


    public Organization(String name, Coordinates coordinates,
                        float annualTurnover, String fullname,
                        int employeesCount, OrganizationType type, Address officialAddress) {
        this.name = name;
        this.coordinates = coordinates;
        this.annualTurnover = annualTurnover;
        this.fullname = fullname;
        this.employeesCount = employeesCount;
        this.type = type;
        this.officialAddress = officialAddress;
        id = hashCode() + count++;
    }

    public int getID() {return id;}
    @Override
    public String toString() {
        return "Organization[id: " + id + "; name: " + name + "; coordinates: " + coordinates
                + "; creationDate: " + creationDate + "; annualTurnover: " + annualTurnover
                + "; fullname: " + fullname + "; employeesCount: " + employeesCount
                + "; type: " + type + "; officialAddress: " + officialAddress + "]";
    }
    @Override
    public boolean equals(Object other) {
        return true;
    }
    @Override
    public int hashCode() {
        return Math.abs((int)((name.hashCode() + fullname.hashCode()) * (annualTurnover % employeesCount) + creationDate.hashCode() + coordinates.hashCode() + type.hashCode() + officialAddress.hashCode() * 0xdead));
    }


    public Integer getKey() {
        return id;
    }
    */
}