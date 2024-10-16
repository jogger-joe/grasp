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
import net.usemyskills.grasp.databinding.FragmentTagListBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.model.TagDto;
import net.usemyskills.grasp.viewmodel.TagViewModel;

public class ListTagsFragment extends Fragment implements OnItemClickListener<TagDto> {
    private TagViewModel tagViewModel;
    private TagRecyclerViewAdapter<TagDto> tagRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentTagListBinding binding = FragmentTagListBinding.inflate(inflater, container, false);
        this.tagRecyclerViewAdapter = new TagRecyclerViewAdapter<>();
        this.tagRecyclerViewAdapter.setOnClickTagListener(this);
        binding.tagList.setAdapter(this.tagRecyclerViewAdapter);
        binding.addTag.setOnClickListener(v -> {
            tagViewModel.setEditTagElement(new TagDto());
            NavHostFragment.findNavController(ListTagsFragment.this).navigate(R.id.action_edit_tag);
        });
        return binding.getRoot();
    }

    @Override
    public void onClickItem(TagDto tag) {
        this.tagViewModel.setEditTagElement(tag);
        NavHostFragment.findNavController(ListTagsFragment.this).navigate(R.id.action_edit_tag);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.tagViewModel = viewModelProvider.get(TagViewModel.class);
        this.tagViewModel.getTags().observe(this.requireActivity(), tags -> {
            this.tagRecyclerViewAdapter.setValues(tags);
        });
        this.tagViewModel.reloadTags();
        super.onActivityCreated(savedInstanceState);
    }
}