package se.chalmers.group22.gymcompanion.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import se.chalmers.group22.gymcompanion.View.Home.HomeActivity;
import se.chalmers.group22.gymcompanion.ViewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = new MainViewModel();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}