package net.usemyskills.grasp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.viewmodel.DataViewModel;

public class ListActivity extends AppCompatActivity {

    public static final int NEW_DATA_RESULT_REQUEST_CODE = 1;
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        if (savedInstanceState == null) {
            DataViewModel viewModel = new ViewModelProvider(this.getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(DataViewModel.class);
            listFragment = ListFragment.newInstance(viewModel, 1);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_container, listFragment)
                    .commitNow();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, EditActivity.class);
            startActivityForResult(intent, NEW_DATA_RESULT_REQUEST_CODE);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == NEW_DATA_RESULT_REQUEST_CODE && resultCode == RESULT_OK) {
                listFragment.handleActivityResult(data);
            }
        } catch (Exception exception) {
            Toast.makeText(
                    getApplicationContext(),
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

}