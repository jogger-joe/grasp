package net.usemyskills.grasp;

import android.content.Intent;

import net.usemyskills.grasp.adapter.RecordGroupRecyclerViewAdapter;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.view.ListFragment;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;

import java.util.ArrayList;

public class RecordGroupsActivity extends BaseListActivity<RecordGroup> {
    @Override
    public ListFragment<RecordGroup> getListFragment() {
        RecordGroupViewModel recordGroupViewModel = new RecordGroupViewModel(getApplication());
        RecordGroupRecyclerViewAdapter recordGroupRecyclerViewAdapter = new RecordGroupRecyclerViewAdapter(new ArrayList<>(), this);
        return new ListFragment<>(recordGroupViewModel, recordGroupRecyclerViewAdapter, 2);
    }

    @Override
    public void onClickItem(RecordGroup item) {
        Intent intent = new Intent(this, RecordsActivity.class);
        intent.putExtra(RecordsActivity.RECORD_GROUP_ID_EXTRA, item.tagId);
        startActivityForResult(intent, 0);
    }

    public void onClickAdd() {
//        Intent intent = new Intent(MainActivity.this, EditActivity.class);
//        startActivityForResult(intent, NEW_DATA_RESULT_REQUEST_CODE);
    }

    @Override
    public void onNewDataResult(Intent data) {

    }
}