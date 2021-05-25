package net.usemyskills.grasp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.adapter.SelectableRecyclerViewAdapter;
import net.usemyskills.grasp.model.Selectable;


public class SelectDialogFragment<T extends Selectable> extends DialogFragment {
    private final MutableLiveData<T> selectedElement;
    private final RecyclerView.Adapter recyclerViewAdapter;

    public SelectDialogFragment(RecyclerView.Adapter recyclerViewAdapter) {
        this.recyclerViewAdapter = recyclerViewAdapter;
        this.selectedElement = new MutableLiveData<>();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        RecyclerView mRecyclerView = new RecyclerView(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(this.recyclerViewAdapter);

        return new AlertDialog.Builder(getActivity())
                .setTitle("choose")
                .setView(mRecyclerView)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if (recyclerViewAdapter instanceof SelectableRecyclerViewAdapter) {
                                    selectedElement.setValue(((SelectableRecyclerViewAdapter<T>) recyclerViewAdapter).getSelectedTag());
                                }
                            }
                        }
                ).create();
    }

    public MutableLiveData<T> getSelectedElement() {
        return selectedElement;
    }
}
