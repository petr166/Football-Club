package footballClub.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Player object class
 */

public class Player {

    // fields
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final IntegerProperty age;
    private final IntegerProperty team;


    // constructors
    public Player() {
        this(null, null);
    }

    public Player(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.age = new SimpleIntegerProperty(0);
        this.team = new SimpleIntegerProperty(2);
    }

    public Player(String firstName, String lastName, int age, int team) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.age = new SimpleIntegerProperty(age);
        this.team = new SimpleIntegerProperty(team);
    }


    // setters, getters
    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public int getTeam() {
        return team.get();
    }

    public IntegerProperty teamProperty() {
        return team;
    }

    public void setTeam(int team) {
        this.team.set(team);
    }


    // toString method
    @Override
    public String toString() {
        return "Player{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", age=" + age +
                ", team=" + team +
                '}';
    }
}
