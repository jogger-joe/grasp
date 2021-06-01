package net.usemyskills.grasp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import net.usemyskills.grasp.adapter.TagRecyclerViewAdapter;
import net.usemyskills.grasp.databinding.FragmentTagListBinding;
import net.usemyskills.grasp.persistence.entity.Tag;

public class DialogTagFragment<T extends Tag> extends DialogFragment {
    private final TagRecyclerViewAdapter<T> tagRecyclerViewAdapter;
    private FragmentTagListBinding binding;

    public DialogTagFragment(TagRecyclerViewAdapter<T> selectableRecyclerViewAdapter) {
        this.tagRecyclerViewAdapter = selectableRecyclerViewAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        this.binding = FragmentTagListBinding.inflate(inflater, container, false);
        binding.tagList.setAdapter(this.tagRecyclerViewAdapter);
        return binding.getRoot();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setView(this.binding.getRoot())
                .setPositiveButton(android.R.string.cancel,
                        (dialog, whichButton) -> dialog.dismiss()
                ).create();
    }
}