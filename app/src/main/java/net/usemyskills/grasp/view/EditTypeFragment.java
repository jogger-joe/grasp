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
import net.usemyskills.grasp.databinding.FragmentEditTypeBinding;
import net.usemyskills.grasp.model.TypeDto;
import net.usemyskills.grasp.viewmodel.TagViewModel;

public class EditTypeFragment extends Fragment implements View.OnClickListener {
    private FragmentEditTypeBinding binding;
    private TagViewModel tagViewModel;
    private TypeDto type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.onCreateView");
        this.binding = FragmentEditTypeBinding.inflate(inflater, container, false);
        this.binding.buttonTagSave.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.onClick");
        try {
            this.type.name = this.binding.typeName.getText().toString();
            this.type.description = this.binding.typeDescription.getText().toString();
            this.type.suffix = this.binding.typeSuffix.getText().toString();
            this.tagViewModel.save(type);
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
        this.tagViewModel.getEditTypeElement().observe(this.requireActivity(), element -> {
            this.type = new TypeDto(element);
            this.bindElement(this.type);
        });
        super.onActivityCreated(savedInstanceState);
    }

    protected void bindElement(TypeDto element) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.bindElement");
        this.binding.typeName.setText(element.name);
        this.binding.typeDescription.setText(element.description);
        this.binding.typeSuffix.setText(element.suffix);
    }
}