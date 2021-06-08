package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.RecordRecyclerViewAdapter;
import net.usemyskills.grasp.databinding.FragmentRecordListBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;
import net.usemyskills.grasp.viewmodel.RecordViewModel;

public class ListRecordsFragment extends Fragment implements OnItemClickListener<RecordDto> {
    private RecordViewModel recordViewModel;
    private RecordGroupViewModel recordGroupViewModel;
    private RecordRecyclerViewAdapter recordRecyclerViewAdapter;
    private NavController navController;
    private FragmentRecordListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.navController = NavHostFragment.findNavController(ListRecordsFragment.this);
        this.recordRecyclerViewAdapter = new RecordRecyclerViewAdapter(this);
        this.binding = FragmentRecordListBinding.inflate(inflater, container, false);
        this.binding.recordList.setAdapter(this.recordRecyclerViewAdapter);
        this.binding.addRecord.setOnClickListener(v -> {
            recordViewModel.setEditElement(new RecordDto());
            navController.navigate(R.id.action_edit_record);
        });
        this.binding.recordGroupBox.setOnClickListener(v -> {
            navController.navigate(R.id.action_edit_record_group);
        });
        this.setHasOptionsMenu(true);
        return this.binding.getRoot();
    }

    @Override
    public void onClickItem(RecordDto item) {
        this.recordViewModel.setEditElement(item);
        this.navController.navigate(R.id.action_edit_record);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.recordViewModel = viewModelProvider.get(RecordViewModel.class);
        this.recordViewModel.getRecords().observe(this.requireActivity(), records -> {
            this.recordRecyclerViewAdapter.setValues(records);
        });
        this.recordGroupViewModel = viewModelProvider.get(RecordGroupViewModel.class);
        this.recordGroupViewModel.getEditElement().observe(this.requireActivity(), recordGroup -> {
            this.binding.recordGroupIcon.setImageResource(recordGroup.iconId);
            this.binding.recordGroupName.setText(recordGroup.name);
        });
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d("GRASP: onOptionsItemSelected", item.toString());

        if (id == R.id.list_tags_button) {
            navController.navigate(R.id.action_list_tags);
            return true;
        }
        if (id == R.id.list_types_button) {
            navController.navigate(R.id.action_list_types);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}