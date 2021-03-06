package entity;


/**
 * Created by Kevin Khanda on 7/23/2016.
 * Represents entity Program from a database.
 */
public class Program implements DomainObject {

    private int id;
    private String name;
    private String organisation;
    private String owner;

    public Program(String name, int id, String organisation, String owner) {
        this.name = name;
        this.id = id;
        this.organisation = organisation;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
