package net.usemyskills.grasp.view;

import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.TagRecyclerViewAdapter;
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
    private TextView recordType;
    private TextView recordTag;
    private EditText recordValue;
    private TextView recordSuffix;

    private TypeViewModel typeViewModel;
    private TagViewModel tagViewModel;

    private TagRecyclerViewAdapter<Type> typeAdapter;
    private TagRecyclerViewAdapter<Tag> tagAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("GRASP_LOG","onCreateView at " + this.getClass().toString());
        View view = inflater.inflate(R.layout.fragment_edit_record, container, false);

        // init adapter
        this.typeAdapter = new TagRecyclerViewAdapter<>(this::updateType);
        this.tagAdapter = new TagRecyclerViewAdapter<>(this::addTag);

        // bind ui-elements
        recordDate = view.findViewById(R.id.record_date);
        DialogDateFragment dateDialogFragment = new DialogDateFragment(this::updateDate);
        this.recordDate.setOnClickListener(v -> dateDialogFragment.show(this.getParentFragmentManager(), "dialog"));

        recordType = view.findViewById(R.id.record_type);
        DialogSelectFragment<Type> dateSelectTypeFragment = new DialogSelectFragment<>(this.typeAdapter);
        this.recordType.setOnClickListener(v -> dateSelectTypeFragment.show(this.getParentFragmentManager(), "dialog"));

        recordTag = view.findViewById(R.id.record_tag);
        DialogSelectFragment<Tag> dateSelectTagFragment = new DialogSelectFragment<>(this.tagAdapter);
        this.recordTag.setOnClickListener(v -> dateSelectTagFragment.show(this.getParentFragmentManager(), "dialog"));

        recordValue = view.findViewById(R.id.record_value);
        recordSuffix = view.findViewById(R.id.record_suffix);

        Button recordSave = view.findViewById(R.id.button_record_save);
        recordSave.setOnClickListener(this);
        return view;
    }

    private void updateDate(DatePicker view, int year, int month, int dayOfMonth) {
        this.updateDate(new GregorianCalendar(year, month, dayOfMonth).getTime());
    }

    private void updateDate(Date date) {
        if (this.element != null) {
            this.element.setDate(date);
            this.viewModel.getSelectedEntity().postValue(this.element);
        }
    }

    private void updateType(Type type) {
        if (this.element != null) {
            this.element.setType(type);
            this.viewModel.getSelectedEntity().postValue(this.element);
        }
    }

    private void addTag(Tag tag) {
        if (this.element != null) {
            this.element.addTag(tag);
            this.viewModel.getSelectedEntity().postValue(this.element);
        }
    }

    @Override
    public void onClick(View view) {
        try {
            this.viewModel.insert(this.element);
            NavHostFragment.findNavController(EditRecordFragment.this)
                    .navigate(R.id.action_finish_edit_record);
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("GRASP_LOG", exception.getMessage());
        }
    }

    protected void init(LifecycleOwner owner) {
        Log.d("GRASP_LOG", "onActivityCreated at " + this.getClass().toString());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.viewModel = viewModelProvider.get(RecordViewModel.class);
        this.viewModel.setOwner(owner);
        this.viewModel.getSelectedEntity().observe(owner, this::bindElement);

        this.typeViewModel = viewModelProvider.get(TypeViewModel.class);
        this.typeViewModel.setOwner(owner);
        this.typeViewModel.getEntities().observe(owner, types -> {
            Log.d("GRASP_LOG", "TypeViewModel observe triggered at " + this.getClass().toString());
            this.typeAdapter.setValues(types);
        });
        this.typeViewModel.loadAll();

        this.tagViewModel = viewModelProvider.get(TagViewModel.class);
        this.tagViewModel.setOwner(owner);
        this.tagViewModel.getEntities().observe(owner, tags -> {
            Log.d("GRASP_LOG", "TagViewModel observe triggered at " + this.getClass().toString());
            this.tagAdapter.setValues(tags);
        });
        this.tagViewModel.loadAll();

        super.init(owner);
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