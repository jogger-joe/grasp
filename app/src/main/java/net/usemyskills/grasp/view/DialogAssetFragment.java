package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import net.usemyskills.grasp.adapter.AssetRecyclerViewAdapter;
import net.usemyskills.grasp.databinding.FragmentAssetListBinding;

public class DialogAssetFragment extends BottomSheetDialogFragment {
    private final AssetRecyclerViewAdapter assetRecyclerViewAdapter;

    public DialogAssetFragment(AssetRecyclerViewAdapter assetRecyclerViewAdapter) {
        this.assetRecyclerViewAdapter = assetRecyclerViewAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAssetListBinding binding = FragmentAssetListBinding.inflate(inflater, container, false);
        binding.assetIconList.setAdapter(this.assetRecyclerViewAdapter);
        return binding.assetIconList;
    }
}