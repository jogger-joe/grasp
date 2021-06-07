package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.TagRecyclerViewAdapter;
import net.usemyskills.grasp.databinding.FragmentRecordListBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.model.TypeDto;
import net.usemyskills.grasp.viewmodel.TagViewModel;

public class ListTypesFragment extends Fragment implements OnItemClickListener<TypeDto> {
    private TagViewModel tagViewModel;
    private TagRecyclerViewAdapter<TypeDto> tagRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentRecordListBinding binding = FragmentRecordListBinding.inflate(inflater, container, false);
        this.tagRecyclerViewAdapter = new TagRecyclerViewAdapter<>();
        binding.recordList.setAdapter(this.tagRecyclerViewAdapter);
        return binding.getRoot();
    }

    @Override
    public void onClickItem(TypeDto type) {
        this.tagViewModel.setEditTagElement(type);
        NavHostFragment.findNavController(ListTypesFragment.this)
                .navigate(R.id.action_edit_record);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.tagViewModel = viewModelProvider.get(TagViewModel.class);
        this.tagViewModel.getTypes().observe(this.requireActivity(), types -> {
            this.tagRecyclerViewAdapter.setValues(types);
        });
        super.onActivityCreated(savedInstanceState);
    }
}