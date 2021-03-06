package entity;

/**
 * Created by Kevin Khanda on 7/23/2016.
 * Represents Role entity from a database.
 * Getters and Setters included.
 */
public class Role implements DomainObject {

    private int id;
    private String name;

    public Role (int id, String name) {
        this.id = id;
        this.name = name;
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
}
