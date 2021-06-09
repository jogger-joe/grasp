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
import net.usemyskills.grasp.databinding.FragmentEditRecordGroupBinding;
import net.usemyskills.grasp.model.Asset;
import net.usemyskills.grasp.model.RecordGroupDto;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;

public class EditRecordGroupFragment extends Fragment {
    private FragmentEditRecordGroupBinding binding;
    private RecordGroupViewModel recordGroupViewModel;
    private RecordGroupDto recordGroup;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.navController = NavHostFragment.findNavController(EditRecordGroupFragment.this);
        this.binding = FragmentEditRecordGroupBinding.inflate(inflater, container, false);
        this.binding.buttonRecordGroupSave.setOnClickListener(this::onClickSave);
        this.binding.buttonRecordGroupDelete.setOnClickListener(this::onClickDelete);

        DialogAssetFragment dateSelectTagFragment = new DialogAssetFragment(this::updateIcon);
        this.binding.recordGroupIcon.setOnClickListener(v -> dateSelectTagFragment.show(this.getParentFragmentManager(), "dialog"));

        return binding.getRoot();
    }


    private void updateIcon(Asset asset) {
        if (this.recordGroup != null) {
            this.recordGroup.iconId = asset.id;
            this.bindElement(this.recordGroup);
        }
    }

    public void onClickSave(View view) {
        try {
            this.recordGroup.name = this.binding.recordGroupName.getText().toString();
            this.recordGroupViewModel.save(recordGroup);
            Toast.makeText(this.getContext(), R.string.save_successful, Toast.LENGTH_SHORT).show();
            this.navController.navigateUp();
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickDelete(View view) {
        try {
            this.recordGroupViewModel.delete(this.recordGroup);
            Toast.makeText(this.getContext(), R.string.delete_successful, Toast.LENGTH_SHORT).show();
            this.navController.navigateUp();
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.recordGroupViewModel = viewModelProvider.get(RecordGroupViewModel.class);
        this.recordGroupViewModel.getEditElement().observe(this.requireActivity(), element -> {
            this.recordGroup = new RecordGroupDto(element);
            this.bindElement(this.recordGroup);
        });
        super.onActivityCreated(savedInstanceState);
    }

    protected void bindElement(RecordGroupDto element) {
        this.binding.recordGroupName.setText(element.name);
        this.binding.recordGroupIcon.setImageResource(element.iconId);
    }
}