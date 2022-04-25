package com.example.Fit_Life;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserTable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "firstName")
    private String firstName;


    @NonNull
    @ColumnInfo(name = "lastName")
    private String lastName;

    @NonNull
    @ColumnInfo(name = "age")
    private int age;

    @NonNull
    @ColumnInfo(name = "weight")
    private int weight;

    @NonNull
    @ColumnInfo(name = "heightFeet")
    private int heightFeet;

    @NonNull
    @ColumnInfo(name = "heightInches")
    private int heightInches;

    @NonNull
    @ColumnInfo(name = "sex")
    private String sex;

    @NonNull
    @ColumnInfo(name = "city")
    private String city;

    @NonNull
    @ColumnInfo(name = "state")
    private String state;

    @NonNull
    @ColumnInfo(name = "goal")
    private String goal;

    @NonNull
    @ColumnInfo(name = "activityLevel")
    private String activityLevel;

    @NonNull
    @ColumnInfo(name = "numSteps")
    private int numSteps;


    public UserTable(@NonNull String firstName, @NonNull String lastName, int age, int weight,
                     int heightFeet, int heightInches, @NonNull String sex,
                     @NonNull String city, @NonNull String state, @NonNull String goal, @NonNull String activityLevel, int numSteps){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.weight = weight;
        this.heightFeet = heightFeet;
        this.heightInches = heightInches;
        this.sex = sex;
        this.city = city;
        this.state = state;
        this.goal = goal;
        this.activityLevel = activityLevel;
        this.numSteps = numSteps;

    }


    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(int heightFeet) {
        this.heightFeet = heightFeet;
    }

    public int getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(int heightInches) {
        this.heightInches = heightInches;
    }

    @NonNull
    public String getSex() {
        return sex;
    }

    public void setSex(@NonNull String sex) {
        this.sex = sex;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @NonNull
    public String getState() {
        return state;
    }

    public void setState(@NonNull String state) {
        this.state = state;
    }

    @NonNull
    public String getGoal() {
        return goal;
    }

    public void setGoal(@NonNull String goal) {
        this.goal = goal;
    }

    @NonNull
    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(@NonNull String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setNumSteps(int numSteps) {
        this.numSteps = numSteps;
    }

    public int getNumSteps() {
        return numSteps;
    }
}
