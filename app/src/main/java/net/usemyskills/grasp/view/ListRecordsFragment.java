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
import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.viewmodel.RecordViewModel;

public class ListRecordsFragment extends Fragment implements OnItemClickListener<RecordDto> {
    private RecordViewModel recordViewModel;
    private RecordRecyclerViewAdapter recordRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "ListRecordsFragment.onCreateView");
        FragmentRecordListBinding binding = FragmentRecordListBinding.inflate(inflater, container, false);
        this.recordRecyclerViewAdapter = new RecordRecyclerViewAdapter(this);
        binding.recordList.setAdapter(this.recordRecyclerViewAdapter);
        return binding.getRoot();
    }

    @Override
    public void onClickItem(RecordDto item) {
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
        this.recordViewModel.getRecords().observe(this.requireActivity(), records -> {
            this.recordRecyclerViewAdapter.setValues(records);
        });
        super.onActivityCreated(savedInstanceState);
    }
}