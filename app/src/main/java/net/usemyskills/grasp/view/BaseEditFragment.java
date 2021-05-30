package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.viewmodel.BaseViewModel;

public abstract class BaseEditFragment<T> extends Fragment {
    protected BaseViewModel<T> viewModel;
    protected T element;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG","onCreateView at " + this.getClass().toString());
        this.viewModel.setOwner(this.requireActivity());
        this.viewModel.getSelectedEntity().observe(this.getViewLifecycleOwner(), this::bindElement);
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract void bindElement(T element);

}