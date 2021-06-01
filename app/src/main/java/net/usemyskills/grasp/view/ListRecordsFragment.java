package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.RecordRecyclerViewAdapter;
import net.usemyskills.grasp.databinding.FragmentRecordListBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.viewmodel.RecordViewModel;

import java.util.ArrayList;

public class ListRecordsFragment extends Fragment implements OnItemClickListener<RecordWithTypeAndTags> {
    private RecordViewModel recordViewModel;
    private RecordRecyclerViewAdapter recordRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "ListRecordsFragment.onCreateView");
        this.recordRecyclerViewAdapter = new RecordRecyclerViewAdapter(new ArrayList<>(), this);
        return FragmentRecordListBinding.inflate(inflater, container, false).getRoot();
    }

    @Override
    public void onClickItem(RecordWithTypeAndTags item) {
        Log.d("GRASP_LOG", "ListRecordsFragment.onClickItem");
        this.recordViewModel.setEditElement(item);
        NavHostFragment.findNavController(ListRecordsFragment.this)
                .navigate(R.id.action_edit_record);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "ListRecordsFragment.onActivityCreated");
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.recordViewModel = viewModelProvider.get(RecordViewModel.class);
        this.recordViewModel.initObserver(this.requireActivity());
        this.recordViewModel.getRecords().observe(this.requireActivity(), records -> {
            this.recordRecyclerViewAdapter.setValues(records);
            this.recordRecyclerViewAdapter.notifyDataSetChanged();
        });
        super.onActivityCreated(savedInstanceState);
    }
}