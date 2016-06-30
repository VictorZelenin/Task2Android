package dev.zelenin.fragmenttest.database;

/**
 * Created by victor on 29.06.16.
 */
public class City {

    private String name;
    private String description;

    public City(){}

    public City(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
