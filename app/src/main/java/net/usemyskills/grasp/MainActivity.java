package net.usemyskills.grasp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.usemyskills.grasp.adapter.RecordGroupRecyclerViewAdapter;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.view.ListFragment;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener<RecordGroup> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecordGroupViewModel recordGroupViewModel = new RecordGroupViewModel(getApplication());
        RecordGroupRecyclerViewAdapter recordGroupRecyclerViewAdapter = new RecordGroupRecyclerViewAdapter(new ArrayList<>(), this);
        if (savedInstanceState == null) {
            ListFragment<RecordGroup> recordGroupList = new ListFragment<>(recordGroupViewModel, recordGroupRecyclerViewAdapter);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, recordGroupList, null)
                    .commit();
        }
    }

    @Override
    public void onClickItem(RecordGroup item) {

    }
}