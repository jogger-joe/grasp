package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.TagFilterableListAdapter;
import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;
import net.usemyskills.grasp.viewmodel.RecordViewModel;
import net.usemyskills.grasp.viewmodel.TagViewModel;
import net.usemyskills.grasp.viewmodel.TypeViewModel;

import java.util.Date;

public class EditRecordFragment extends BaseEditFragment<RecordWithTypeAndTags> implements View.OnClickListener {
    private TextView recordDate;
    private AutoCompleteTextView recordType;
    private MultiAutoCompleteTextView recordTag;
    private EditText recordValue;
    private TextView recordSuffix;

    private TagFilterableListAdapter<Type> typeAdapter;
    private TagFilterableListAdapter<Tag> tagAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("GRASP_LOG","onCreateView at " + this.getClass().toString());
        View view = inflater.inflate(R.layout.fragment_edit_record, container, false);
        recordDate = view.findViewById(R.id.record_date);
        DateDialogFragment dateDialogFragment = new DateDialogFragment();
        dateDialogFragment.getSelectedDate().observe(this.getViewLifecycleOwner(), this::updateDate);
        this.recordDate.setOnClickListener(v -> dateDialogFragment.show(this.getParentFragmentManager(), "dialog"));
        recordType = view.findViewById(R.id.record_type);
        recordType.setAdapter(this.typeAdapter);
        recordTag = view.findViewById(R.id.record_tag);
        recordTag.setAdapter(this.tagAdapter);
        recordValue = view.findViewById(R.id.record_value);
        recordSuffix = view.findViewById(R.id.record_suffix);
        Button recordSave = view.findViewById(R.id.button_record_save);
        recordSave.setOnClickListener(this);
        return view;
    }

    private void updateDate(Date date) {
        if (this.element != null) {
            this.element.setDate(date);
            this.viewModel.getSelectedEntity().postValue(this.element);
        }
    }

    @Override
    public void onClick(View view) {
        try {
            this.viewModel.insert(this.element);
            NavHostFragment.findNavController(EditRecordFragment.this)
                    .navigate(R.id.action_finish_edit_record_group);
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("GRASP_LOG", exception.getMessage());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "onActivityCreated at " + this.getClass().toString());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.viewModel = viewModelProvider.get(RecordViewModel.class);
        this.typeAdapter = new TagFilterableListAdapter<>(this.getContext(), item -> {
            if (this.element != null) {
                this.element.setType(item);
                this.viewModel.getSelectedEntity().postValue(this.element);
            }
        });
        viewModelProvider.get(TypeViewModel.class).getEntities().observe(this.getViewLifecycleOwner(), types -> {
            Log.d("GRASP_LOG", "TypeViewModel observe triggered at " + this.getClass().toString());
            this.typeAdapter.setValues(types);
            this.typeAdapter.notifyDataSetChanged();
        });
        this.tagAdapter = new TagFilterableListAdapter<>(this.getContext(), item -> {
            if (this.element != null) {
                this.element.addTag(item);
                this.viewModel.getSelectedEntity().postValue(this.element);
            }
        });
        viewModelProvider.get(TagViewModel.class).getEntities().observe(this.getViewLifecycleOwner(), tags -> {
            Log.d("GRASP_LOG", "TagViewModel observe triggered at " + this.getClass().toString());
            this.tagAdapter.setValues(tags);
            this.tagAdapter.notifyDataSetChanged();
        });
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void bindElement(RecordWithTypeAndTags element) {
        this.element = element;
        RecordDto recordDto = new RecordDto(element);
        this.recordDate.setText(recordDto.date);
        this.recordType.setText(recordDto.type);
        this.recordTag.setText(recordDto.tags);
        this.recordValue.setText(recordDto.value);
        this.recordSuffix.setText(recordDto.valueSuffix);
    }
}