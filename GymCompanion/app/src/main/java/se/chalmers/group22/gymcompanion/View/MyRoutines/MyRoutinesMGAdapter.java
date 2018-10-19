package se.chalmers.group22.gymcompanion.View.MyRoutines;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import se.chalmers.group22.gymcompanion.R;

import java.util.List;

public class MyRoutinesMGAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    //to store the list of muscle group names
    private final List<String> names;

    public MyRoutinesMGAdapter(Activity context, List<String> nameArrayParam){

        super(context, R.layout.listitem_musclegroup , nameArrayParam);

        this.context = context;
        this.names = nameArrayParam;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listitem_musclegroup, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView muscleGroupName = (TextView) rowView.findViewById(R.id.txtMuscleGroup);

        //this code sets the values of the objects to values from the arrays
        muscleGroupName.setText(names.get(position));

        return rowView;

    }
}
