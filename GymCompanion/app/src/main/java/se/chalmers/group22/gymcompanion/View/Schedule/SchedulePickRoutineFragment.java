package se.chalmers.group22.gymcompanion.View.Schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import se.chalmers.group22.gymcompanion.R;
import se.chalmers.group22.gymcompanion.View.ScheduleListAdapter;
import se.chalmers.group22.gymcompanion.ViewModel.ScheduleViewModel;

public class SchedulePickRoutineFragment extends Fragment {

    private ScheduleViewModel viewModel;

    public static SchedulePickRoutineFragment getInstance() {
        return new SchedulePickRoutineFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule_pick_routine, container, false);
    }

    public void onStart(){
        super.onStart();

        viewModel = ((ScheduleActivity)getActivity()).getViewModel();

        ScheduleListAdapter adapter = new ScheduleListAdapter(getActivity(), viewModel.getRoutineNames(), viewModel.getRoutineDifficulties(), viewModel.getRoutineExercisesAmounts());
        ListView listView = getView().findViewById(R.id.listviewSchedulePick);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                ((ScheduleActivity)getActivity()).scheduleRoutine();
            }
        });
    }

}