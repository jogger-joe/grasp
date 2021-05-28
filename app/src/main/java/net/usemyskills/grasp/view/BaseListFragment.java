package net.usemyskills.grasp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.BaseRecyclerViewAdapter;
import net.usemyskills.grasp.viewmodel.BaseViewModel;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;
import net.usemyskills.grasp.viewmodel.RecordViewModel;

public abstract class BaseListFragment<T> extends Fragment {
    protected BaseViewModel<T> viewModel;
    protected BaseRecyclerViewAdapter<T> adapter;
    protected int mColumnCount = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_container, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(this.adapter);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        this.viewModel.getEntities().observe(this.getViewLifecycleOwner(), this.adapter::setValues);
        super.onActivityCreated(savedInstanceState);
    }

//    public void handleActivityResult(Intent data) {
//        DataDto dataDto = data.getParcelableExtra(EditActivity.DATA_REPLY);
//        this.viewModel.insert(dataDto);
//    }
}