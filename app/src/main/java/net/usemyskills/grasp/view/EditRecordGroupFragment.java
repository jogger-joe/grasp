package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.databinding.FragmentEditRecordGroupBinding;
import net.usemyskills.grasp.model.RecordGroupDto;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;

public class EditRecordGroupFragment extends Fragment implements View.OnClickListener {
    private FragmentEditRecordGroupBinding binding;
    private RecordGroupViewModel recordGroupViewModel;
    private RecordGroupDto recordGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.onCreateView");
        this.binding = FragmentEditRecordGroupBinding.inflate(inflater, container, false);
        this.binding.buttonRecordGroupSave.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.onClick");
        try {
            this.recordGroup.name = this.binding.recordGroupName.getText().toString();
            this.recordGroup.iconId = Integer.parseInt(this.binding.recordGroupIcon.getText().toString());
            this.recordGroupViewModel.save(recordGroup);
            NavHostFragment.findNavController(EditRecordGroupFragment.this)
                    .navigate(R.id.action_finish_edit_record_group);
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.onActivityCreated");
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.recordGroupViewModel = viewModelProvider.get(RecordGroupViewModel.class);
        this.recordGroupViewModel.getEditElement().observe(this.requireActivity(), this::bindElement);
        super.onActivityCreated(savedInstanceState);
    }

    protected void bindElement(RecordGroupDto element) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.bindElement");
        this.recordGroup = element;
        this.binding.recordGroupName.setText(element.name);
        this.binding.recordGroupIcon.setText(String.valueOf(element.iconId));
    }
}