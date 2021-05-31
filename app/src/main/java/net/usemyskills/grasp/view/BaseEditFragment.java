package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

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
        this.init(this.getViewLifecycleOwner());
        super.onActivityCreated(savedInstanceState);
    }

    protected void init(LifecycleOwner owner) {
    }

    protected abstract void bindElement(T element);

}