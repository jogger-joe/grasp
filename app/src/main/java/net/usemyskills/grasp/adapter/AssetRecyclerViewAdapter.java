package net.usemyskills.grasp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.databinding.FragmentAssetListItemBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.model.Asset;

import java.util.List;

public class AssetRecyclerViewAdapter extends RecyclerView.Adapter<AssetRecyclerViewAdapter.ViewHolder> {
    private final OnItemClickListener<Asset> onClickTagListener;
    private final List<Asset> assets;

    public AssetRecyclerViewAdapter(List<Asset> assets, OnItemClickListener<Asset> onClickRecordListener) {
        Log.d("GRASP_LOG", "TagRecyclerViewAdapter construct");
        this.assets = assets;
        this.onClickTagListener = onClickRecordListener;
        this.notifyDataSetChanged();
    }

    @Override
    public AssetRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("GRASP_LOG", "TagRecyclerViewAdapter.onCreateViewHolder");
        return new AssetRecyclerViewAdapter.ViewHolder(FragmentAssetListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final AssetRecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d("GRASP_LOG", "TagRecyclerViewAdapter.onBindViewHolder");
        Asset asset = assets.get(position);
        holder.bind(asset);
    }

    @Override
    public int getItemCount() {
        return this.assets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public FragmentAssetListItemBinding binding;

        public ViewHolder(FragmentAssetListItemBinding binding) {
            super(binding.getRoot());
            Log.d("GRASP_LOG", "TagRecyclerViewAdapter.ViewHolder.create");
            this.binding = binding;
        }

        public void bind(Asset asset){
            Log.d("GRASP_LOG", "TagRecyclerViewAdapter.ViewHolder.bind: " + asset.toString());
            this.binding.assetIcon.setImageResource(asset.id);
            this.itemView.setOnClickListener(v -> onClickTagListener.onClickItem(asset));
        }
    }

}