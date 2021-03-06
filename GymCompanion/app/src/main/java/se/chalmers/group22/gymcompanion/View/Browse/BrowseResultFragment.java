package se.chalmers.group22.gymcompanion.View.Browse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import se.chalmers.group22.gymcompanion.Model.ViewModelObserver;
import se.chalmers.group22.gymcompanion.R;
import se.chalmers.group22.gymcompanion.ViewModel.BrowseViewModel;

import java.util.List;

/***
 * Title: BrowseResultFragment
 *
 * @author Alexander Bergsten
 * @author Marcus Svensson
 * @author Erik Bock
 * @author Augustas Eidikis
 * @author Daniel Olsson
 *
 * Created: October 18, 2018
 *
 * Purpose: Fragment connected to a xml displaying the Results from a search or filter
 * Used by: BrowseActivity.java
 * Uses: BrowseViewModel.java, SearchViewBrowse.java, BrowseMuscleGroupsFragment.java, BrowseActivity.java,
 * fragment_browse_results.xml
 */
public class BrowseResultFragment extends Fragment implements ViewModelObserver {

    private BrowseViewModel viewModel;
    private TextView currentMuscleGroup;
    private SearchViewBrowse searchView;
    private CheckBox cbxRoutines;
    private CheckBox cbxExercises;
    private Spinner dropDown;
    private ListView listView;
    public static BrowseMuscleGroupsFragment getInstance() {
        return new BrowseMuscleGroupsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_browse_results, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel = ((BrowseActivity) getActivity()).getViewModel();

        viewModel.addObserver(this);

        //************************************LISTVIEW
        BrowseResultListAdapter adapter;
        listView = getView().findViewById(R.id.listViewBrowseResult);
        adapter = new BrowseResultListAdapter(getActivity(),
                viewModel.getRoutineAndExerciseNames(),
                viewModel.getRoutineAndExerciseDifficulties(),
                viewModel.getRoutineAmountExercises(),
                0);
        listView.setAdapter(adapter);

        cbxRoutines = getView().findViewById(R.id.cbxRoutines);
        cbxExercises = getView().findViewById(R.id.cbxExercises);

        //Both of the listener onCheckedChanged methods also checks if the other checkbox is unchecked or not,
        //if the other is unchecked, there is a toast message shown and the pressed checkbox isnt getting
        //unchecked. You shouldnt be able to sort on "nothing".
        cbxRoutines.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                   if(cbxExercises.isChecked()) {
                       if(isChecked){
                           viewModel.filterRoutinesExercises(false, 1);
                           viewModel.setCbxRoutine(true);
                       } else {
                           viewModel.filterRoutinesExercises(true, 1);
                           viewModel.setCbxRoutine(false);
                       }
                   } else {
                       cbxRoutines.setChecked(true);
                       Toast.makeText(getActivity(), "You cant filter on nothing!", Toast.LENGTH_SHORT).show();
                   }
               }
           }
        );
        cbxExercises.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                   if(cbxRoutines.isChecked()){
                       if(isChecked){
                           viewModel.filterRoutinesExercises(false, 0);
                           viewModel.setCbxExercise(true);
                       } else {
                           viewModel.filterRoutinesExercises(true, 0);
                           viewModel.setCbxExercise(false);
                       }
                   } else {
                       cbxExercises.setChecked(true);
                       Toast.makeText(getActivity(), "You cant filter on nothing!", Toast.LENGTH_SHORT).show();
                   }
               }
           }
        );

        searchView = getView().findViewById(R.id.searchBar);

        searchView.setOnQueryTextListener(new SearchViewBrowse.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
            @Override
            public boolean onQueryTextSubmit(String query) {
                ((BrowseActivity)getActivity()).goToResultFromSearch(query);
                update();
                return false;
            }
        });

        //************************************ACTIONBAR
        ((BrowseActivity) getActivity()).getSupportActionBar().setTitle("Browse Results");

        //************************************DROPDOWN
        //DropDown menu from xml
        dropDown = getView().findViewById(R.id.dropdownSpinner);

        //List of items to be shown in the menu
        List<String> items = viewModel.getSortFilters();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, items);
        dropDown.setAdapter(spinnerAdapter);

        //Gets current index from viewmodel
        dropDown.setSelection(viewModel.getCurrentSortIndex());

        dropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                viewModel.setCurrentSortIndex(position);
                viewModel.sortRoutinesAndExercises(position);
            }
            public void onNothingSelected(AdapterView<?> parent)
            {
                //Nothing
            }
        });

        //************************************SEARCHBAR
        //Sets the searchbar to what was searched on startfragment (may be empty if coming from muscle group selection)
        searchView.setQuery(viewModel.getQuery(), false);
    }

    @Override
    public void onPause(){
        super.onPause();
        viewModel.removeObserver(this);
    }

    @Override
    public void update() {
        //************************************ACTIONBAR
        ((BrowseActivity) getActivity()).getSupportActionBar().setTitle("Browse Results");

        this.currentMuscleGroup = getView().findViewById(R.id.currentMuscleGroup);
        String t = "Browsing: " + viewModel.getCurrentPage();
        this.currentMuscleGroup.setText(t);

        //gets current index from the viewmodel
        dropDown.setSelection(viewModel.getCurrentSortIndex());

        //************************************LISTVIEW
        BrowseResultListAdapter adapter;
        listView = getView().findViewById(R.id.listViewBrowseResult);
        adapter = new BrowseResultListAdapter(getActivity(),
                viewModel.getRoutineAndExerciseNames(),
                viewModel.getRoutineAndExerciseDifficulties(),
                viewModel.getRoutineAmountExercises(),
                0);
        listView.setAdapter(adapter);

        //************************************SEARCHBAR
        //Sets the searchbar to what was searched on startfragment (may be empty if coming from muscle group selection)
        searchView.setQuery(viewModel.getQuery(), false);

        //************************************CHECKBOXES
        cbxExercises.setChecked(viewModel.isCbxExercise());
        cbxRoutines.setChecked(viewModel.isCbxRoutine());
    }
}
