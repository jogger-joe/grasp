package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.usemyskills.grasp.adapter.RecordWithTypeAndTagsRecordRecyclerViewAdapter;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;
import net.usemyskills.grasp.viewmodel.RecordViewModel;

import java.util.ArrayList;

public class RecordsFragment extends BaseListFragment<RecordWithTypeAndTags> implements OnItemClickListener<RecordWithTypeAndTags> {
    protected RecordGroupViewModel recordGroupViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel = this.getDefaultViewModelProviderFactory().create(RecordViewModel.class);
        this.adapter = new RecordWithTypeAndTagsRecordRecyclerViewAdapter(new ArrayList<>(), this);
        this.mColumnCount = 1;
        this.recordGroupViewModel = this.getDefaultViewModelProviderFactory().create(RecordGroupViewModel.class);
//        this.recordGroupViewModel.getSelectedEntity().observe(this.getViewLifecycleOwner(), recordGroup -> this.viewModel);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClickItem(RecordWithTypeAndTags item) {

    }
}