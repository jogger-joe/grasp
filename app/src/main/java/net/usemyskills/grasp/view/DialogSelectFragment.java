package net.usemyskills.grasp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.adapter.TagRecyclerViewAdapter;
import net.usemyskills.grasp.persistence.entity.Tag;


public class DialogSelectFragment<T extends Tag> extends DialogFragment {
    private final TagRecyclerViewAdapter<T> tagRecyclerViewAdapter;

    public DialogSelectFragment(TagRecyclerViewAdapter<T> selectableRecyclerViewAdapter) {
        this.tagRecyclerViewAdapter = selectableRecyclerViewAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        RecyclerView mRecyclerView = new RecyclerView(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(this.tagRecyclerViewAdapter);

        AlertDialog selectDialog = new AlertDialog.Builder(getActivity())
                .setView(mRecyclerView)
                .setPositiveButton(android.R.string.cancel,
                        (dialog, whichButton) -> dialog.dismiss()
                ).create();
        return selectDialog;
    }
}