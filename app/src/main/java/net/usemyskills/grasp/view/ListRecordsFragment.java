package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.RecordWithTypeAndTagsRecordRecyclerViewAdapter;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;
import net.usemyskills.grasp.viewmodel.RecordViewModel;

import java.util.ArrayList;

public class ListRecordsFragment extends BaseListFragment<RecordWithTypeAndTags> implements OnItemClickListener<RecordWithTypeAndTags> {
    protected RecordGroupViewModel recordGroupViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.adapter = new RecordWithTypeAndTagsRecordRecyclerViewAdapter(new ArrayList<>(), this);
        this.mColumnCount = 1;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClickItem(RecordWithTypeAndTags item) {
        
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG","onActivityCreated at " + this.getClass().toString());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.viewModel = viewModelProvider.get(RecordViewModel.class);
        this.recordGroupViewModel = viewModelProvider.get(RecordGroupViewModel.class);
        this.recordGroupViewModel.getSelectedEntity().observe(this.getViewLifecycleOwner(), recordGroup -> {
            Log.d("GRASP_LOG","getSelectedEntity observe triggered with " + recordGroup.getClass().toString());
            ((RecordViewModel) this.viewModel).loadRecordsByGroup(recordGroup.tagId);
        });
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_element) {
            this.viewModel.setSelectedEntity(new RecordWithTypeAndTags());
            NavHostFragment.findNavController(ListRecordsFragment.this)
                    .navigate(R.id.action_edit_record);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}