package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import net.usemyskills.grasp.adapter.TagRecyclerViewAdapter;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.viewmodel.RecordViewModel;
import net.usemyskills.grasp.viewmodel.TagViewModel;

import java.util.ArrayList;

public class ListTagsFragment extends BaseListFragment<Tag> implements OnItemClickListener<Tag> {
    protected RecordViewModel recordViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.adapter = new TagRecyclerViewAdapter<>(new ArrayList<>(), this);
        this.mColumnCount = 1;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClickItem(Tag item) {
        RecordWithTypeAndTags record =
                this.recordViewModel.getSelectedEntity() != null &&
                this.recordViewModel.getSelectedEntity().getValue() != null ?
                this.recordViewModel.getSelectedEntity().getValue() : new RecordWithTypeAndTags();
        record.tags.add(item);
        this.recordViewModel.insert(record);
        this.recordViewModel.setSelectedEntity(record);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "onActivityCreated at " + this.getClass().toString());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.viewModel = viewModelProvider.get(TagViewModel.class);
        this.recordViewModel = viewModelProvider.get(RecordViewModel.class);
        super.onActivityCreated(savedInstanceState);
    }
}