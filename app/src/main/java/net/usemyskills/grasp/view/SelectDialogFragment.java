package net.usemyskills.grasp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.adapter.SelectableRecyclerViewAdapter;
import net.usemyskills.grasp.model.Selectable;


public class SelectDialogFragment<T extends Selectable> extends DialogFragment {
    private final SelectableRecyclerViewAdapter<T> selectableRecyclerViewAdapter;

    public SelectDialogFragment(SelectableRecyclerViewAdapter<T> selectableRecyclerViewAdapter) {
        this.selectableRecyclerViewAdapter = selectableRecyclerViewAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        RecyclerView mRecyclerView = new RecyclerView(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(this.selectableRecyclerViewAdapter);

        AlertDialog selectDialog = new AlertDialog.Builder(getActivity())
                .setView(mRecyclerView)
                .setPositiveButton(android.R.string.cancel,
                        (dialog, whichButton) -> dialog.dismiss()
                ).create();
        this.selectableRecyclerViewAdapter.getSelectedElement().observe(this, selectedElement -> this.dismiss());
        return selectDialog;
    }

    public MutableLiveData<T> getSelectedElement() {
        return this.selectableRecyclerViewAdapter.getSelectedElement();
    }
}
