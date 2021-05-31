package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.BaseRecyclerViewAdapter;
import net.usemyskills.grasp.viewmodel.BaseViewModel;

public abstract class BaseListFragment<T> extends Fragment {
    protected BaseViewModel<T> viewModel;
    protected BaseRecyclerViewAdapter<T> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("GRASP_LOG","onCreateView at " + this.getClass().toString());
        View view = inflater.inflate(R.layout.fragment_record_group_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(this.adapter);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG","onCreateView at " + this.getClass().toString());
        this.init(this.getViewLifecycleOwner());
        super.onActivityCreated(savedInstanceState);
    }

    protected void init(LifecycleOwner owner) {
        this.viewModel.getEntities().observe(owner, values -> {
            Log.d("GRASP_LOG","getEntities observe triggered with " + values.toString());
            this.adapter.setValues(values);
        });
        this.viewModel.setOwner(this.requireActivity());
        this.viewModel.loadAll();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}