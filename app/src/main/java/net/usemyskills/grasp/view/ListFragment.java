package net.usemyskills.grasp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.DataRecyclerViewAdapter;
import net.usemyskills.grasp.model.DataDto;
import net.usemyskills.grasp.viewmodel.DataViewModel;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private final DataViewModel viewModel;

    public ListFragment(DataViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static ListFragment newInstance(DataViewModel viewModel, int columnCount) {
        ListFragment fragment = new ListFragment(viewModel);
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            DataRecyclerViewAdapter adapter = new DataRecyclerViewAdapter(new ArrayList<>());
            recyclerView.setAdapter(adapter);

            viewModel.getAll().observe(this, adapter::setValues);
        }
        return view;
    }

    public void handleActivityResult(Intent data) {
        DataDto dataDto = data.getParcelableExtra(EditActivity.DATA_REPLY);
        this.viewModel.insert(dataDto);
    }
}