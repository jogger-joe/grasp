package net.usemyskills.grasp;

import android.content.Intent;

import net.usemyskills.grasp.adapter.FullRecordRecyclerViewAdapter;
import net.usemyskills.grasp.persistence.entity.FullRecord;
import net.usemyskills.grasp.persistence.entity.Record;
import net.usemyskills.grasp.view.ListFragment;
import net.usemyskills.grasp.viewmodel.RecordViewModel;

import java.util.ArrayList;

public class RecordsActivity extends BaseListActivity<FullRecord> {
    public static final String RECORD_GROUP_ID_EXTRA = "RECORD_GROUP_ID";
    private RecordViewModel recordViewModel;

    @Override
    public ListFragment<FullRecord> getListFragment() {
        this.recordViewModel = new RecordViewModel(getApplication());
        FullRecordRecyclerViewAdapter fullRecordRecyclerViewAdapter = new FullRecordRecyclerViewAdapter(new ArrayList<>(), this);
        return new ListFragment<>(this.recordViewModel, fullRecordRecyclerViewAdapter, 1);
    }

    @Override
    public void onClickItem(FullRecord item) {
    }

    public void onClickAdd() {
//        Intent intent = new Intent(MainActivity.this, EditActivity.class);
//        startActivityForResult(intent, NEW_DATA_RESULT_REQUEST_CODE);
    }

    @Override
    public void onNewDataResult(Intent data) {

    }
}