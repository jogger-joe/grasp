package net.usemyskills.grasp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.BaseRecyclerViewAdapter;
import net.usemyskills.grasp.persistence.entity.BaseEntity;
import net.usemyskills.grasp.viewmodel.BaseViewModel;

public class ListFragment<T extends BaseEntity> extends Fragment {
    private final BaseViewModel<T> viewModel;
    private final BaseRecyclerViewAdapter<T> adapter;
    private final int mColumnCount;

    public ListFragment(BaseViewModel<T> viewModel, BaseRecyclerViewAdapter<T> adapter, int mColumnCount) {
        this.viewModel = viewModel;
        this.adapter = adapter;
        this.mColumnCount = mColumnCount;
    }

    public ListFragment(BaseViewModel<T> viewModel, BaseRecyclerViewAdapter<T> adapter) {
        this(viewModel, adapter, 1);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_list_fragment, container, false);
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
            this.viewModel.getEntities().observe(this, this.adapter::setValues);
        }
        return view;
    }

//    public void handleActivityResult(Intent data) {
//        DataDto dataDto = data.getParcelableExtra(EditActivity.DATA_REPLY);
//        this.viewModel.insert(dataDto);
//    }
}