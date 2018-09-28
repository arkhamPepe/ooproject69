package se.chalmers.group22.gymcompanion.Model;

import lombok.Getter;
import se.chalmers.group22.gymcompanion.Enums.MUSCLE_GROUP;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Routine implements ISortable {

    private String description;
    private String name;
    private double difficulty;
    private List<MUSCLE_GROUP> muscleGroups;

    private List<Exercise> exercises;

    public Routine(String name, double difficulty){
        this(name);
        this.difficulty = difficulty;
    }

    // FOR TESTING
    public Routine(List<MUSCLE_GROUP> muscleGroups, double difficulty){
        this.muscleGroups = muscleGroups;
        this.difficulty = difficulty;
    }

    public Routine(String name){
        this();
        this.name = name;
    }

    public Routine(){
        this.exercises = new ArrayList<>();
    }

    public boolean containsMuscleGroup(MUSCLE_GROUP mg){
        return muscleGroups.contains(mg);
    }

}
