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
import net.usemyskills.grasp.databinding.FragmentEditTagBinding;
import net.usemyskills.grasp.model.TagDto;
import net.usemyskills.grasp.viewmodel.TagViewModel;

public class EditTagFragment extends Fragment {
    private FragmentEditTagBinding binding;
    private TagViewModel tagViewModel;
    private TagDto tag;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.navController = NavHostFragment.findNavController(EditTagFragment.this);
        this.binding = FragmentEditTagBinding.inflate(inflater, container, false);
        this.binding.buttonTagSave.setOnClickListener(this::onClickSave);
        this.binding.buttonTagDelete.setOnClickListener(this::onClickDelete);
        return binding.getRoot();
    }

    public void onClickSave(View view) {
        try {
            this.tag.name = this.binding.tagName.getText().toString();
            this.tag.description = this.binding.tagDescription.getText().toString();
            this.tagViewModel.save(this.tag);
            Toast.makeText(this.getContext(), R.string.save_successful, Toast.LENGTH_SHORT).show();
            this.navController.navigateUp();
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickDelete(View view) {
        try {
            this.tagViewModel.delete(this.tag);
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
        this.tagViewModel.getEditTagElement().observe(this.requireActivity(), element -> {
            this.tag = new TagDto(element);
            this.bindElement(this.tag);
        });
        super.onActivityCreated(savedInstanceState);
    }

    protected void bindElement(TagDto element) {
        this.binding.tagName.setText(element.name);
        this.binding.tagDescription.setText(element.description);
    }
}