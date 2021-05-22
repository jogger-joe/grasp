package net.usemyskills.grasp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.viewmodel.DataViewModel;

public class ListActivity extends AppCompatActivity {
    public static final int NEW_DATA_CONTAINER_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DataViewModel viewModel = new ViewModelProvider(this.getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(DataViewModel.class);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_container, ListFragment.newInstance(viewModel, 1))
                    .commitNow();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, EditActivity.class);
            startActivityForResult(intent, NEW_DATA_CONTAINER_ACTIVITY_REQUEST_CODE);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//
//        try {
//            if (requestCode == NEW_DATA_CONTAINER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//                DataDto dataDto = data.getParcelableExtra(EditActivity.DATA_REPLY);
//                this.dataViewModel.insert(dataDto);
//            }
//        } catch (Exception exception) {
//            Toast.makeText(
//                    getApplicationContext(),
//                    exception.getMessage(),
//                    Toast.LENGTH_LONG).show();
//        }
    }

}