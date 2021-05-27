package net.usemyskills.grasp.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.RecordGroupRecyclerViewAdapter;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;

import java.util.ArrayList;

public class RecordGroupsFragment extends BaseListFragment<RecordGroup> implements OnItemClickListener<RecordGroup> {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.viewModel = this.getDefaultViewModelProviderFactory().create(RecordGroupViewModel.class);
        this.adapter = new RecordGroupRecyclerViewAdapter(new ArrayList<>(), this);
        this.mColumnCount = 2;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClickItem(RecordGroup item) {
        this.viewModel.setSelectedEntity(item);
        NavHostFragment.findNavController(RecordGroupsFragment.this)
                .navigate(R.id.action_record_groups_to_records);
    }
}