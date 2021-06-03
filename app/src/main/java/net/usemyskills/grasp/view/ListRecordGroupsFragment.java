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
import net.usemyskills.grasp.model.RecordGroupDto;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;
import net.usemyskills.grasp.viewmodel.RecordViewModel;
import net.usemyskills.grasp.viewmodel.TagViewModel;

public class ListRecordGroupsFragment extends Fragment implements OnItemClickListener<RecordGroupDto> {
    private RecordViewModel recordViewModel;
    private TagViewModel tagViewModel;
    private RecordGroupViewModel recordGroupViewModel;
    private RecordGroupRecyclerViewAdapter recordGroupRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "ListRecordGroupsFragment.onCreateView");
        FragmentRecordGroupListBinding binding = FragmentRecordGroupListBinding.inflate(inflater, container, false);
        this.recordGroupRecyclerViewAdapter = new RecordGroupRecyclerViewAdapter(this);
        binding.recordGroupList.setAdapter(this.recordGroupRecyclerViewAdapter);
        return binding.getRoot();
    }

    @Override
    public void onClickItem(RecordGroupDto recordGroup) {
        Log.d("GRASP_LOG", "ListRecordGroupsFragment.onClickItem: " + recordGroup.name + "(" + recordGroup.id + ")");
        NavController navController = NavHostFragment.findNavController(ListRecordGroupsFragment.this);
        if (recordGroup.isPlaceholder()) {
            this.recordGroupViewModel.setEditElement(recordGroup);
            navController.navigate(R.id.action_edit_record_group);
        } else {
            this.recordViewModel.setRecordGroup(recordGroup);
            this.tagViewModel.setRecordGroup(recordGroup);
            navController.navigate(R.id.action_select_record_group);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "ListRecordGroupsFragment.onActivityCreated");
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.tagViewModel = viewModelProvider.get(TagViewModel.class);
        this.recordViewModel = viewModelProvider.get(RecordViewModel.class);
        this.recordGroupViewModel = viewModelProvider.get(RecordGroupViewModel.class);
        this.recordGroupViewModel.getRecordGroups().observe(this.requireActivity(), recordGroups -> {
            Log.d("GRASP_LOG", "ListRecordGroupsFragment.recordGroupViewModel.getRecordGroups().observe: " + recordGroups.toString());
            this.recordGroupRecyclerViewAdapter.setValues(recordGroups);
        });
        super.onActivityCreated(savedInstanceState);
    }
}