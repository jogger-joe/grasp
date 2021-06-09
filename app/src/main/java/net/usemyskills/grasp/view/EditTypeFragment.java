package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.databinding.FragmentEditTypeBinding;
import net.usemyskills.grasp.model.TypeDto;
import net.usemyskills.grasp.viewmodel.TagViewModel;

public class EditTypeFragment extends Fragment {
    private FragmentEditTypeBinding binding;
    private TagViewModel tagViewModel;
    private TypeDto type;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.navController = NavHostFragment.findNavController(EditTypeFragment.this);
        this.binding = FragmentEditTypeBinding.inflate(inflater, container, false);
        this.binding.buttonTypeSave.setOnClickListener(this::onClickSave);
        this.binding.buttonTypeDelete.setOnClickListener(this::onClickDelete);
        return binding.getRoot();
    }

    public void onClickSave(View view) {
        try {
            this.type.name = this.binding.typeName.getText().toString();
            this.type.description = this.binding.typeDescription.getText().toString();
            this.type.suffix = this.binding.typeSuffix.getText().toString();
            this.tagViewModel.save(type);
            Toast.makeText(this.getContext(), R.string.save_successful, Toast.LENGTH_SHORT).show();
            this.navController.navigateUp();
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickDelete(View view) {
        try {
            this.tagViewModel.delete(this.type);
            Toast.makeText(this.getContext(), R.string.delete_successful, Toast.LENGTH_SHORT).show();
            this.navController.navigateUp();
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.tagViewModel = viewModelProvider.get(TagViewModel.class);
        this.tagViewModel.getEditTypeElement().observe(this.requireActivity(), element -> {
            this.type = new TypeDto(element);
            this.bindElement(this.type);
        });
        super.onActivityCreated(savedInstanceState);
    }

    protected void bindElement(TypeDto element) {
        this.binding.typeName.setText(element.name);
        this.binding.typeDescription.setText(element.description);
        this.binding.typeSuffix.setText(element.suffix);
    }
}