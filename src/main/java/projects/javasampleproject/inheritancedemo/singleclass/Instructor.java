package projects.javasampleproject.inheritancedemo.singleclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instructor extends User {
private String favouriteStudent;
}
