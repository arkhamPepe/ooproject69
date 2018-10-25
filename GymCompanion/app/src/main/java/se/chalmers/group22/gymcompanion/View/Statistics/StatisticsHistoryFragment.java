package se.chalmers.group22.gymcompanion.View.Statistics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import se.chalmers.group22.gymcompanion.R;
import se.chalmers.group22.gymcompanion.View.HistoryListAdapter;
import se.chalmers.group22.gymcompanion.ViewModel.StatisticsViewModel;

import java.util.Calendar;
import java.util.List;
/***
 * Title: StatisticsHistoryFragment
 *
 * @author Alexander Bergsten
 * @author Marcus Svensson
 * @author Erik Bock
 * @author Augustas Eidikis
 * @author Daniel Olsson
 *
 * Created: October 5, 2018
 *
 * Purpose: Fragment connected to a xml displaying the Statistics History Page in the app
 */
public class StatisticsHistoryFragment extends Fragment {
    /** TEMPORARY DATA */
    /*private String[] routineNames = {"Chest demolisher", "Leg killer", "Back attack", "Arms mauler", "Murder your shoulders", "Chest demolisher",
            "Leg killer", "Back attack", "Chest demolisher", "Arms mauler", "Marathon Sprint", "Crossfit session"};
    private String[] dates = {"Monday w.42", "Tuesday w.42", "Thursday w.42", "Caturday w.42", "Monday w.43", "Tuesday w.43", "Thursday w.43", "Saturday w.43",
            "Monday w.44", "Tuesday w.44", "Thursday w.44", "Saturday w.44"};*/

    private StatisticsViewModel viewModel;

    public static StatisticsHistoryFragment newInstance() {
        StatisticsHistoryFragment fragment = new StatisticsHistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics_history, container, false);
    }

    public void onStart(){
        super.onStart();
        viewModel = ((StatisticsActivity)getActivity()).getViewModel();

        HistoryListAdapter adapter = new HistoryListAdapter(getActivity(), viewModel.getRoutineNames(), viewModel.getRoutineDatesFormatted());
        ListView listView = getListView();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                ((StatisticsActivity)getActivity()).onRoutineClick(viewModel.getRoutineDates().get(position));
            }
        });


    }

    public ListView getListView(){
        return getActivity().findViewById(R.id.listviewHistory);
    }
}
