package net.usemyskills.grasp.view;

import android.os.Bundle;
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
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.navController = NavHostFragment.findNavController(ListRecordGroupsFragment.this);
        this.recordGroupRecyclerViewAdapter = new RecordGroupRecyclerViewAdapter(this);
        FragmentRecordGroupListBinding binding = FragmentRecordGroupListBinding.inflate(inflater, container, false);
        binding.recordGroupList.setAdapter(this.recordGroupRecyclerViewAdapter);
        binding.addRecordGroup.setOnClickListener(v -> {
            recordGroupViewModel.setEditElement(new RecordGroupDto());
            navController.navigate(R.id.action_add_record_group);
        });
        return binding.getRoot();
    }

    @Override
    public void onClickItem(RecordGroupDto recordGroup) {
        this.recordGroupViewModel.setEditElement(recordGroup);
        this.tagViewModel.setRecordGroup(recordGroup);
        this.navController.navigate(R.id.action_select_record_group);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.tagViewModel = viewModelProvider.get(TagViewModel.class);
        this.recordViewModel = viewModelProvider.get(RecordViewModel.class);
        this.recordGroupViewModel = viewModelProvider.get(RecordGroupViewModel.class);
        this.recordGroupViewModel.getRecordGroups().observe(this.requireActivity(), recordGroups -> {
            this.recordGroupRecyclerViewAdapter.setValues(recordGroups);
        });
        super.onActivityCreated(savedInstanceState);
    }
}