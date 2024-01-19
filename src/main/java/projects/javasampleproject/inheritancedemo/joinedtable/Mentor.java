package projects.javasampleproject.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mentor extends User {
    private double averageRating;
}
