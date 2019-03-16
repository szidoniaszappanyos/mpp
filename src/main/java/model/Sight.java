package model;

import repository.HasID;

import java.util.Objects;

public class Sight implements HasID<Integer> {
    private int id;
    private String name;
    private String description;

    public Sight(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Sight() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sight sight = (Sight) o;
        return id == sight.id &&
                Objects.equals(name, sight.name) &&
                Objects.equals(description, sight.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Sight{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
