package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;

public class EditRecordGroupFragment extends BaseEditFragment<RecordGroup> implements View.OnClickListener {
    private EditText recordGroupName;
    private EditText recordGroupIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("GRASP_LOG","onCreateView at " + this.getClass().toString());
        View view = inflater.inflate(R.layout.fragment_edit_record_group, container, false);
        recordGroupName = view.findViewById(R.id.record_group_name);
        recordGroupIcon = view.findViewById(R.id.record_group_icon);
        Button recordGroupSave = view.findViewById(R.id.button_record_group_save);
        recordGroupSave.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        try {
            RecordGroup recordGroup = viewModel.getSelectedEntityElement();
            recordGroup.name = recordGroupName.getText().toString();
            recordGroup.iconId = Integer.parseInt(recordGroupIcon.getText().toString());
            viewModel.insert(recordGroup);
            NavHostFragment.findNavController(EditRecordGroupFragment.this)
                    .navigate(R.id.action_finish_edit_record_group);
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "onActivityCreated at " + this.getClass().toString());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.viewModel = viewModelProvider.get(RecordGroupViewModel.class);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void bindElement(RecordGroup element) {
        this.recordGroupName.setText(element.name);
        this.recordGroupIcon.setText(String.valueOf(element.iconId));
    }
}