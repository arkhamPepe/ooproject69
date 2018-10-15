package se.chalmers.group22.gymcompanion.View.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import se.chalmers.group22.gymcompanion.R;

public class HomeFinishedFragment extends Fragment {
    private TextView textViewTimeSpent;
    private TextView textViewTotalExercises;
    private TextView textViewCompletedExercises;
    private Button btnGotoHome;

    public static HomeStartFragment newInstance() {
        HomeStartFragment fragment = new HomeStartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_finished, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.textViewTimeSpent = getView().findViewById(R.id.textViewTimeSpent);
        this.textViewCompletedExercises = getView().findViewById(R.id.textViewExercisesCompleted);
        this.textViewTotalExercises = getView().findViewById(R.id.textViewTotalExercises);
        this.btnGotoHome = getView().findViewById(R.id.btnGotoHome);
    }
}