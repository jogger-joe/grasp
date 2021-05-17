package net.usemyskills.grasp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.DataContainerListAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = this.findViewById(R.id.recyclerview);
        final DataContainerListAdapter adapter = new DataContainerListAdapter(new DataContainerListAdapter.DataContainerDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}