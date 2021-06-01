package net.usemyskills.grasp.view;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.RecordGroupRecyclerViewAdapter;
import net.usemyskills.grasp.databinding.FragmentRecordGroupListBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;
import net.usemyskills.grasp.viewmodel.RecordViewModel;
import net.usemyskills.grasp.viewmodel.TagViewModel;

import java.util.ArrayList;

public class ListRecordGroupsFragment extends Fragment implements OnItemClickListener<RecordGroup> {
    private RecordViewModel recordViewModel;
    private TagViewModel tagViewModel;
    private RecordGroupRecyclerViewAdapter recordGroupRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "ListRecordGroupsFragment.onCreateView");
        this.recordGroupRecyclerViewAdapter = new RecordGroupRecyclerViewAdapter(new ArrayList<>(), this);
        return FragmentRecordGroupListBinding.inflate(inflater, container, false).getRoot();
    }

    @Override
    public void onClickItem(RecordGroup item) {
        Log.d("GRASP_LOG", "ListRecordGroupsFragment.onClickItem");
        this.recordViewModel.setRecordGroup(item);
        this.tagViewModel.setRecordGroup(item);
        NavController navController = NavHostFragment.findNavController(ListRecordGroupsFragment.this);
        if (item.tagId == 0) {
            navController.navigate(R.id.action_select_record_group);
        } else {
            navController.navigate(R.id.action_edit_record_group);
        }
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "ListRecordGroupsFragment.onActivityCreated");
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.tagViewModel = viewModelProvider.get(TagViewModel.class);
        this.tagViewModel.initObserver(this.requireActivity());
        this.recordViewModel = viewModelProvider.get(RecordViewModel.class);
        this.recordViewModel.initObserver(this.requireActivity());
        RecordGroupViewModel recordGroupViewModel = viewModelProvider.get(RecordGroupViewModel.class);
        recordGroupViewModel.initObserver(this.requireActivity());
        recordGroupViewModel.getRecordGroups().observe(this.requireActivity(), recordGroups -> {
            this.recordGroupRecyclerViewAdapter.setValues(recordGroups);
            this.recordGroupRecyclerViewAdapter.notifyDataSetChanged();
        });
        super.onActivityCreated(savedInstanceState);
    }
}