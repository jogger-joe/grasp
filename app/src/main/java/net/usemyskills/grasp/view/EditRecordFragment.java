package net.usemyskills.grasp.view;

import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.TagRecyclerViewAdapter;
import net.usemyskills.grasp.databinding.FragmentEditRecordBinding;
import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;
import net.usemyskills.grasp.viewmodel.RecordViewModel;
import net.usemyskills.grasp.viewmodel.TagViewModel;

import java.util.Date;

public class EditRecordFragment extends Fragment implements View.OnClickListener {
    private FragmentEditRecordBinding binding;

    private RecordViewModel recordViewModel;
    private RecordWithTypeAndTags record;

    private TagRecyclerViewAdapter<Type> typeAdapter;
    private TagRecyclerViewAdapter<Tag> tagAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "EditRecordFragment.onCreateView");
        // init bindings
        this.binding = FragmentEditRecordBinding.inflate(inflater, container, false);
        this.binding.buttonRecordSave.setOnClickListener(this);
        this.bindElement(this.record);

        // init dialogs
        DialogDateFragment dateDialogFragment = new DialogDateFragment(this::updateDate);
        DialogTagFragment<Type> dateSelectTypeFragment = new DialogTagFragment<>(this.typeAdapter);
        DialogTagFragment<Tag> dateSelectTagFragment = new DialogTagFragment<>(this.tagAdapter);

        // init listener
        this.binding.recordDate.setOnClickListener(v -> dateDialogFragment.show(this.getParentFragmentManager(), "dialog"));
        this.binding.recordType.setOnClickListener(v -> dateSelectTypeFragment.show(this.getParentFragmentManager(), "dialog"));
        this.binding.recordTag.setOnClickListener(v -> dateSelectTagFragment.show(this.getParentFragmentManager(), "dialog"));

        // init adapter
        this.typeAdapter = new TagRecyclerViewAdapter<>(this::updateType);
        this.tagAdapter = new TagRecyclerViewAdapter<>(this::addTag);

        return binding.getRoot();
    }

    private void updateDate(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d("GRASP_LOG", "EditRecordFragment.updateDate");
        this.updateDate(new GregorianCalendar(year, month, dayOfMonth).getTime());
    }

    private void updateDate(Date date) {
        Log.d("GRASP_LOG", "EditRecordFragment.updateDate");
        if (this.record != null) {
            this.record.setDate(date);
            this.bindElement(this.record);
        }
    }

    private void updateType(Type type) {
        Log.d("GRASP_LOG", "EditRecordFragment.updateType");
        if (this.record != null) {
            this.record.setType(type);
            this.bindElement(this.record);
        }
    }

    private void addTag(Tag tag) {
        Log.d("GRASP_LOG", "EditRecordFragment.addTag");
        if (this.record != null) {
            this.record.addTag(tag);
            this.bindElement(this.record);
        }
    }

    @Override
    public void onClick(View view) {
        Log.d("GRASP_LOG", "EditRecordFragment.onClick");
        try {
            this.recordViewModel.save(this.record);
            NavHostFragment.findNavController(EditRecordFragment.this)
                    .navigate(R.id.action_finish_edit_record);
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("GRASP_LOG", exception.getMessage());
        }
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "EditRecordFragment.onActivityCreated");
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.recordViewModel = viewModelProvider.get(RecordViewModel.class);
        this.recordViewModel.initObserver(this.requireActivity());
        this.recordViewModel.getEditElement().observe(this.requireActivity(), this::bindElement);
        TagViewModel tagViewModel = viewModelProvider.get(TagViewModel.class);
        tagViewModel.initObserver(this.requireActivity());
        tagViewModel.getTags().observe(this.requireActivity(), tags -> {
            this.tagAdapter.setValues(tags);
        });
        tagViewModel.getTypes().observe(this.requireActivity(), types -> {
            this.typeAdapter.setValues(types);
        });
        super.onActivityCreated(savedInstanceState);
    }

    protected void bindElement(RecordWithTypeAndTags element) {
        Log.d("GRASP_LOG", "EditRecordFragment.bindElement");
        RecordDto recordDto = new RecordDto(element);
        this.binding.recordDate.setText(recordDto.date);
        this.binding.recordType.setText(recordDto.type);
        this.binding.recordTag.setText(recordDto.tags);
        this.binding.recordValue.setText(recordDto.value);
        this.binding.recordSuffix.setText(recordDto.valueSuffix);
    }
}