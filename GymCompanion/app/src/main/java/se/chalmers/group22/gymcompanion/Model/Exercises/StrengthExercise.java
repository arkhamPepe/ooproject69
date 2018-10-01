package se.chalmers.group22.gymcompanion.Model.Exercises;

import lombok.Getter;
import lombok.Setter;
import se.chalmers.group22.gymcompanion.Enums.MUSCLE_GROUP;
import se.chalmers.group22.gymcompanion.Enums.INTENSITY;

import java.util.List;

public class StrengthExercise extends Exercise {
    @Getter
    private List<Integer> repetitions;
    @Getter
    private int sets;
    @Getter
    private double kilograms;
    @Setter
    @Getter
    private INTENSITY intensity;


    public StrengthExercise(String name, double difficulty, MUSCLE_GROUP muscle_group, String description, String videoguide, List<Integer> repetitions, int sets, double kilograms, INTENSITY intensity) {
        super(name, difficulty, muscle_group, description, videoguide, intensity);
        this.repetitions = repetitions;
        this.sets = sets;
        this.kilograms = kilograms;
        this.intensity = intensity;
    }
}
