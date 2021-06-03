package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import net.usemyskills.grasp.adapter.TagRecyclerViewAdapter;
import net.usemyskills.grasp.databinding.FragmentTagListBinding;
import net.usemyskills.grasp.model.TagDto;

public class DialogTagFragment<T extends TagDto> extends BottomSheetDialogFragment {
    private final TagRecyclerViewAdapter<T> tagRecyclerViewAdapter;

    public DialogTagFragment(TagRecyclerViewAdapter<T> tagRecyclerViewAdapter) {
        this.tagRecyclerViewAdapter = tagRecyclerViewAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        net.usemyskills.grasp.databinding.FragmentTagListBinding binding = FragmentTagListBinding.inflate(inflater, container, false);
        binding.tagList.setAdapter(this.tagRecyclerViewAdapter);
        return binding.tagList;
    }
}