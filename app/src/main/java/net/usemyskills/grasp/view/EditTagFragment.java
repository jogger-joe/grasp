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

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.databinding.FragmentEditTagBinding;
import net.usemyskills.grasp.model.TagDto;
import net.usemyskills.grasp.viewmodel.TagViewModel;

public class EditTagFragment extends Fragment implements View.OnClickListener {
    private FragmentEditTagBinding binding;
    private TagViewModel tagViewModel;
    private TagDto tag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.onCreateView");
        this.binding = FragmentEditTagBinding.inflate(inflater, container, false);
        this.binding.buttonTagSave.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.onClick");
        try {
            this.tag.name = this.binding.tagName.getText().toString();
            this.tag.description = this.binding.tagDescription.getText().toString();
            this.tagViewModel.save(tag);
            Toast.makeText(this.getContext(), R.string.save_successful, Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.onActivityCreated");
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.tagViewModel = viewModelProvider.get(TagViewModel.class);
        this.tagViewModel.getEditTagElement().observe(this.requireActivity(), element -> {
            this.tag = new TagDto(element);
            this.bindElement(this.tag);
        });
        super.onActivityCreated(savedInstanceState);
    }

    protected void bindElement(TagDto element) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.bindElement");
        this.binding.tagName.setText(element.name);
        this.binding.tagDescription.setText(element.description);
    }
}