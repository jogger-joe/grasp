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
import net.usemyskills.grasp.adapter.RecordGroupRecyclerViewAdapter;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;

import java.util.ArrayList;

public class ListRecordGroupsFragment extends BaseListFragment<RecordGroup> implements OnItemClickListener<RecordGroup> {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.adapter = new RecordGroupRecyclerViewAdapter(new ArrayList<>(), this);
        this.mColumnCount = 2;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClickItem(RecordGroup item) {
        this.viewModel.setSelectedEntity(item);
        NavHostFragment.findNavController(ListRecordGroupsFragment.this)
                .navigate(R.id.action_select_record_group);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "onActivityCreated at " + this.getClass().toString());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.viewModel = viewModelProvider.get(RecordGroupViewModel.class);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_element) {
            this.viewModel.setSelectedEntity(new RecordGroup());
            NavHostFragment.findNavController(ListRecordGroupsFragment.this)
                    .navigate(R.id.action_edit_record_group);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}