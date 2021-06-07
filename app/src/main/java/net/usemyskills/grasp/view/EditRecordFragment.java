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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.TagRecyclerViewAdapter;
import net.usemyskills.grasp.databinding.FragmentEditRecordBinding;
import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.model.TagDto;
import net.usemyskills.grasp.model.TypeDto;
import net.usemyskills.grasp.viewmodel.RecordViewModel;
import net.usemyskills.grasp.viewmodel.TagViewModel;

import java.util.Date;

public class EditRecordFragment extends Fragment implements View.OnClickListener {
    private FragmentEditRecordBinding binding;

    private RecordViewModel recordViewModel;
    private TagViewModel tagViewModel;
    private RecordDto record;

    private TagRecyclerViewAdapter<TypeDto> typeAdapter;
    private TagRecyclerViewAdapter<TagDto> tagAdapter;

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "EditRecordFragment.onCreateView");
        this.navController = NavHostFragment.findNavController(EditRecordFragment.this);

        // init bindings
        this.binding = FragmentEditRecordBinding.inflate(inflater, container, false);
        this.binding.buttonRecordSave.setOnClickListener(this);
        this.bindElement(this.record);

        // init adapter
        this.typeAdapter = new TagRecyclerViewAdapter<>();
        this.tagAdapter = new TagRecyclerViewAdapter<>();

        // init dialogs
        DialogDateFragment dateDialogFragment = new DialogDateFragment(this::updateDate);
        DialogTagFragment<TypeDto> dateSelectTypeFragment = new DialogTagFragment<>(this.typeAdapter);
        DialogTagFragment<TagDto> dateSelectTagFragment = new DialogTagFragment<>(this.tagAdapter);

        // init listener
        this.binding.recordDate.setOnClickListener(v -> dateDialogFragment.show(this.getParentFragmentManager(), "dialog"));
        this.binding.recordType.setOnClickListener(v -> dateSelectTypeFragment.show(this.getParentFragmentManager(), "dialog"));
        this.binding.recordTag.setOnClickListener(v -> dateSelectTagFragment.show(this.getParentFragmentManager(), "dialog"));

        return binding.getRoot();
    }

    private void updateDate(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d("GRASP_LOG", "EditRecordFragment.updateDate");
        this.setDate(new GregorianCalendar(year, month, dayOfMonth).getTime());
    }

    private void setDate(Date date) {
        Log.d("GRASP_LOG", "EditRecordFragment.updateDate");
        if (this.record != null) {
            this.record.date = date;
            this.bindElement(this.record);
        }
    }

    private void clickType(TypeDto type) {
        Log.d("GRASP_LOG", "EditRecordFragment.updateType " + type.toString() );
        if (type.isPlaceholder()) {
            this.tagViewModel.setEditTypeElement(type);
            this.navController.navigate(R.id.action_edit_tag);
        } else if (this.record != null) {
            this.record.type = type;
            this.bindElement(this.record);
        }
    }

    private void clickTag(TagDto tag) {
        Log.d("GRASP_LOG", "EditRecordFragment.addTag");
        if (tag.isPlaceholder()) {
            this.tagViewModel.setEditTagElement(tag);
            this.navController.navigate(R.id.action_edit_type);
        } else if (this.record != null) {
            this.record.tags.add(tag);
            this.bindElement(this.record);
        }
    }

    @Override
    public void onClick(View view) {
        Log.d("GRASP_LOG", "EditRecordFragment.onClick");
        try {
            this.recordViewModel.save(this.record);
            Toast.makeText(this.getContext(), R.string.save_successful, Toast.LENGTH_SHORT).show();
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
        this.recordViewModel.getEditElement().observe(this.requireActivity(), element -> {
            this.record = new RecordDto(element);
            this.bindElement(this.record);
        });
        this.tagViewModel = viewModelProvider.get(TagViewModel.class);
        this.tagViewModel.getTags().observe(this.requireActivity(), tags -> {
            this.tagAdapter.setValues(tags);
            this.tagAdapter.addValue(new TagDto());
        });
        this.tagViewModel.getTypes().observe(this.requireActivity(), types -> {
            this.typeAdapter.setValues(types);
            this.typeAdapter.addValue(new TypeDto());
        });
        super.onActivityCreated(savedInstanceState);
    }

    protected void bindElement(RecordDto element) {
        if (element == null) return;
        Log.d("GRASP_LOG", "EditRecordFragment.bindElement " + element);
        this.binding.recordDate.setText(element.getDateLabel());
        this.binding.recordType.setText(element.getTypeLabel());
        this.binding.recordTag.setText(element.getTagsLabel());
        this.binding.recordValue.setText(String.valueOf(element.value));
        this.binding.recordSuffix.setText(element.type.suffix);
    }
}