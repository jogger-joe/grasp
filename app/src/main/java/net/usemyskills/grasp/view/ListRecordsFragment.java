package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import net.usemyskills.grasp.adapter.RecordViewAdapter;
import net.usemyskills.grasp.databinding.FragmentRecordListBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;
import net.usemyskills.grasp.viewmodel.RecordViewModel;
import net.usemyskills.grasp.viewmodel.TagViewModel;

import java.util.ArrayList;

public class ListRecordsFragment extends Fragment implements OnItemClickListener<RecordWithTypeAndTags> {
    private FragmentRecordListBinding binding;
    private RecordViewModel recordViewModel;
    private RecordViewAdapter recordViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.recordViewAdapter = new RecordViewAdapter(new ArrayList<>(), this);
        this.binding = FragmentRecordListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onClickItem(RecordWithTypeAndTags item) {
        
    }


    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG","onCreateView at " + this.getClass().toString());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        TagViewModel tagViewModel = viewModelProvider.get(TagViewModel.class);
        this.recordViewModel = viewModelProvider.get(RecordViewModel.class);
        this.recordViewModel.getEntities().observe(this.requireActivity(), values -> {
            Log.d("GRASP_LOG","getEntities observe triggered with " + values.toString());
            this.recordViewAdapter.setValues(values);
        });
        this.recordViewModel.setOwner(this.requireActivity());


        viewModelProvider.get(RecordGroupViewModel.class).getSelectedEntity().observe(this.getViewLifecycleOwner(), recordGroup -> {
            Log.d("GRASP_LOG","getSelectedEntity observe triggered with " + recordGroup.getClass().toString());
            this.recordViewModel.loadRecordsByGroup(recordGroup.tagId);
            this.recordViewModel.setCurrentRecordGroupId(recordGroup.tagId);
            viewModelProvider.get(TagViewModel.class).setRecordGroup(recordGroup);
        });
        super.onActivityCreated(savedInstanceState);
    }
}