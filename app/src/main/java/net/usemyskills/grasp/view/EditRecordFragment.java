package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.viewmodel.RecordViewModel;

public class EditRecordFragment extends BaseEditFragment<RecordWithTypeAndTags> implements View.OnClickListener {
    private TextView recordDate;
    private TextView recordType;
    private TextView recordTag;
    private EditText recordValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("GRASP_LOG","onCreateView at " + this.getClass().toString());
        View view = inflater.inflate(R.layout.fragment_edit_record_group, container, false);
        recordDate = view.findViewById(R.id.record_date);
        recordType = view.findViewById(R.id.record_type);
        recordTag = view.findViewById(R.id.record_tag);
        recordValue = view.findViewById(R.id.record_value);
        Button recordSave = view.findViewById(R.id.button_record_save);
        recordSave.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        try {
            RecordWithTypeAndTags record = viewModel.getSelectedEntityElement();
            viewModel.insert(record);
            NavHostFragment.findNavController(EditRecordFragment.this)
                    .navigate(R.id.action_finish_edit_record_group);
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("GRASP_LOG", exception.getMessage());
        }
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "onActivityCreated at " + this.getClass().toString());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.viewModel = viewModelProvider.get(RecordViewModel.class);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void bindElement(RecordWithTypeAndTags element) {
        RecordDto recordDto = new RecordDto(element);
        this.recordDate.setText(recordDto.date);
        this.recordType.setText(recordDto.type);
        this.recordTag.setText(recordDto.tags);
        this.recordValue.setText(recordDto.value);
    }
}