package se.chalmers.group22.gymcompanion.ViewModel;

import lombok.Getter;
import se.chalmers.group22.gymcompanion.Enums.MUSCLE_GROUP;
import se.chalmers.group22.gymcompanion.Model.Exercises.Exercise;
import se.chalmers.group22.gymcompanion.Model.Exercises.StrengthExercise;
import se.chalmers.group22.gymcompanion.Model.Observer;
import se.chalmers.group22.gymcompanion.Model.Routine;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class MyRoutinesViewModel extends ObservableViewModel {

    @Getter
    private int selectedRoutineIndex;
    private int selectedExerciseIndex;
    private int selectedMGIndex;

    private List<MUSCLE_GROUP> muscleGroups = new ArrayList<>();

    public MyRoutinesViewModel(){
        initMuscleGroups();
    }

    public void removeExercise(String exerciseName){
        getModel().removeExerciseFromRoutine(selectedRoutineIndex,exerciseName);
        notifyObservers();
    }

    public void createRoutine(){
        getModel().createRoutine();
        notifyObservers();
    }

    public void addExercise(String exerciseName){
        getModel().addExerciseToRoutine(selectedRoutineIndex, exerciseName);

    }

    public List<Double> getRoutineExercisesDifficulty(){
        List<Double> exercisesDifficulty = new ArrayList<>();
        for (Exercise exercise : getModel().getExerciseList()){
            exercisesDifficulty.add(exercise.getDifficulty());
        }
        return exercisesDifficulty;
    }

    public List getMuscleGroups(){
        List<String> muscles = new ArrayList<>();
        for(MUSCLE_GROUP mg : muscleGroups){
            muscles.add(mg.toString().replace("_", " "));
        }
        return muscles;
    }

    /* TODO FIX CODE DUPLICATION */
    private void initMuscleGroups(){
        for(MUSCLE_GROUP mg : MUSCLE_GROUP.values()) {
            muscleGroups.add(mg);
        }
    }

    public List<String> getExerciseNamesWithMG(MUSCLE_GROUP mg){
        List<String> exerciseNames = new ArrayList<>();

        for (Exercise exercise: getModel().getExerciseList()){
            if (exercise.getMuscleGroups().contains(mg)){
                exerciseNames.add(exercise.getName());
            }
        }

        return exerciseNames;
    }

    public List<String> getSelectedRoutineExercisesNames(){
        List<String> exerciseNames = new ArrayList<>();

        for (Exercise exercise: getModel().getSelectedRoutineExercises(selectedRoutineIndex)){
            exerciseNames.add(exercise.getName());
        }

        return exerciseNames;
    }

    public List<Integer> checkIfNoRoutine(){
        List<Integer> amountlist = new ArrayList<>();
        int size = getExerciseNamesWithMG(getSelectedMuscleGroup()).size();
        for (int i = 0; i < size; i++){
            amountlist.add(0);
        }
        return amountlist;
    }

    public void setSelectedMGIndex(int index){
        selectedMGIndex = index;
        notifyObservers();
    }

    public MUSCLE_GROUP getSelectedMuscleGroup(){
        return muscleGroups.get(selectedMGIndex);
    }

    public String getSelectedRoutineExerciseAmount(){
        if (!checkIfEmptyRoutineList()){
            return Integer.toString(getModel().getUser().getRoutines().get(selectedRoutineIndex).getExercises().size());
        }
        return "";
    }

    public List<Routine> getRoutines(){
        if (!checkIfEmptyRoutineList()) {
            return getModel().getUser().getRoutines();
        }
        return new ArrayList<>();
    }

    public void setSelectedRoutineIndex(int position){
        selectedRoutineIndex = position;
        notifyObservers();
    }

    public void setSelectedExerciseIndex(int position){
        selectedExerciseIndex = position;
        notifyObservers();
    }

    public String getSelectedRoutineName(){
        if (!checkIfEmptyRoutineList()){
             return  getModel().getUser().getRoutines().get(selectedRoutineIndex).getName();
        }
        return "";
    }

    public List<Exercise> getExercises(){
        if (!checkIfEmptyExerciseList()){
            return getModel().getUser().getRoutines().get(selectedRoutineIndex).getExercises();
        }
        return new ArrayList<>();
    }

    public void setActiveRoutine(){
        if (!checkIfEmptyRoutineList()){
            getModel().setActiveRoutine(getModel().getUser().getRoutines().get(selectedRoutineIndex));

        }
    }

    public String getExerciseName(){
        if (!checkIfEmptyExerciseList()) {
            return getModel().getUser().getRoutines().get(selectedRoutineIndex).getExercises().get(selectedExerciseIndex).getName();
        }
        return "";
    }
    private Exercise getSelectedExecise(){
        return getModel().getUser().getRoutines().get(selectedRoutineIndex).getExercises().get(selectedExerciseIndex);
    }

    public int checkTypeExercise(){
        if (getSelectedExecise() instanceof StrengthExercise){
            return 1;
        }
        else {
            return 0;
        }
    }

    public String getExerciseGuide(){
        return getModel().getUser().getRoutines().get(selectedRoutineIndex).getExercises().get(selectedExerciseIndex).getVideoguide();
    }

    public String getExerciseDescription(){
        if(!checkIfEmptyExerciseList()){
            return getModel().getUser().getRoutines().get(selectedRoutineIndex).getExercises().get(selectedExerciseIndex).getDescription();

        }
        return "";
    }

    public List<Double> getStrengthExerciseKilograms(){
        if(!checkIfEmptyExerciseList()) {
            return ((StrengthExercise) getModel().getUser().getRoutines().
                    get(selectedRoutineIndex).getExercises().get(selectedExerciseIndex)).getKilograms();
        }
        return new ArrayList<>();
    }

    public List<Integer> getStrengthExerciseReps(){
        if(!checkIfEmptyExerciseList()) {
            return ((StrengthExercise) getModel().getUser().getRoutines().
                    get(selectedRoutineIndex).getExercises().get(selectedExerciseIndex)).getRepetitions();
        }
        return new ArrayList<>();
    }

    private boolean checkIfEmptyRoutineList(){
        return getModel().getUser().getRoutines().isEmpty();
    }

    private boolean checkIfEmptyExerciseList(){
        if (!checkIfEmptyRoutineList()) {
            return getModel().getUser().getRoutines().get(selectedRoutineIndex).getExercises().isEmpty();
        }
        return true;
    }
}
