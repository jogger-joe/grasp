package net.usemyskills.grasp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.DataContainerListAdapter;
import net.usemyskills.grasp.persistence.entity.Data;
import net.usemyskills.grasp.viewmodel.DataContainerViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ListDataContainerActivity extends AppCompatActivity {

    private DataContainerViewModel dataContainerViewModel;
    public static final int NEW_DATA_CONTAINER_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_container);

        RecyclerView recyclerView = this.findViewById(R.id.recyclerView);
        final DataContainerListAdapter adapter = new DataContainerListAdapter(new DataContainerListAdapter.DataContainerDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.dataContainerViewModel = new ViewModelProvider(this.getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(DataContainerViewModel.class);
        this.dataContainerViewModel.getAll().observe(this, adapter::submitList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ListDataContainerActivity.this, EditDataContainerActivity.class);
            startActivityForResult(intent, NEW_DATA_CONTAINER_ACTIVITY_REQUEST_CODE);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == NEW_DATA_CONTAINER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
                this.dataContainerViewModel.insert(this.buildData(data));
            } else {
                throw new Exception("R.string.empty_not_saved");
            }
        } catch (Exception exception) {
            Toast.makeText(
                    getApplicationContext(),
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private Data buildData(Intent data) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
        Date date = simpleDateFormat.parse(data.getStringExtra(EditDataContainerActivity.DATE_REPLY));
        int value = data.getIntExtra(EditDataContainerActivity.VALUE_REPLY, 0);
        int dataTypeTagId = data.getIntExtra(EditDataContainerActivity.DATA_TYPE_TAG_REPLY, 0);
        int dataTagId = data.getIntExtra(EditDataContainerActivity.DATA_TAG_REPLY, 0);
        return new Data(dataTypeTagId, dataTagId, date, value);
    }

}