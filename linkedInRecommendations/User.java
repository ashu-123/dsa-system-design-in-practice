package linkedInRecommendations;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class represents a user of the system.
 */
public class User {

    private int id;

    private String name;

    private Set<String> skills = new HashSet<>();

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public User setSkills(Set<String> skills) {
        this.skills = skills;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
