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
import androidx.navigation.fragment.NavHostFragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.AssetRecyclerViewAdapter;
import net.usemyskills.grasp.databinding.FragmentEditRecordGroupBinding;
import net.usemyskills.grasp.model.Asset;
import net.usemyskills.grasp.model.RecordGroupDto;
import net.usemyskills.grasp.service.AssetProvider;
import net.usemyskills.grasp.viewmodel.RecordGroupViewModel;

public class EditRecordGroupFragment extends Fragment implements View.OnClickListener {
    private FragmentEditRecordGroupBinding binding;
    private RecordGroupViewModel recordGroupViewModel;
    private RecordGroupDto recordGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.onCreateView");
        this.binding = FragmentEditRecordGroupBinding.inflate(inflater, container, false);
        this.binding.buttonRecordGroupSave.setOnClickListener(this);

        DialogAssetFragment dateSelectTagFragment = new DialogAssetFragment(new AssetRecyclerViewAdapter(AssetProvider.getAvailableIcons(), this::updateIcon));
        this.binding.recordGroupIcon.setOnClickListener(v -> dateSelectTagFragment.show(this.getParentFragmentManager(), "dialog"));

        return binding.getRoot();
    }


    private void updateIcon(Asset asset) {
        Log.d("GRASP_LOG", "EditRecordFragment.updateIcon " + asset.toString() );
        if (this.recordGroup != null) {
            this.recordGroup.iconId = asset.id;
            this.bindElement(this.recordGroup);
        }
    }

    @Override
    public void onClick(View view) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.onClick");
        try {
            this.recordGroup.name = this.binding.recordGroupName.getText().toString();
            this.recordGroupViewModel.save(recordGroup);
            Toast.makeText(this.getContext(), R.string.save_successful, Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Toast.makeText(this.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.onActivityCreated");
        ViewModelProvider viewModelProvider = new ViewModelProvider(this.requireActivity());
        this.recordGroupViewModel = viewModelProvider.get(RecordGroupViewModel.class);
        this.recordGroupViewModel.getEditElement().observe(this.requireActivity(), element -> {
            this.recordGroup = new RecordGroupDto(element);
            this.bindElement(this.recordGroup);
        });
        super.onActivityCreated(savedInstanceState);
    }

    protected void bindElement(RecordGroupDto element) {
        Log.d("GRASP_LOG", "EditRecordGroupFragment.bindElement");
        this.binding.recordGroupName.setText(element.name);
        this.binding.recordGroupIcon.setImageResource(element.iconId);
    }
}