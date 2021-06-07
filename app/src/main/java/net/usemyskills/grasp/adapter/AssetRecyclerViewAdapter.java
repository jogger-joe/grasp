package net.usemyskills.grasp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.databinding.FragmentAssetListItemBinding;
import net.usemyskills.grasp.listener.DefaultItemInteractionListener;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.model.Asset;
import net.usemyskills.grasp.service.AssetProvider;

import java.util.List;

public class AssetRecyclerViewAdapter extends RecyclerView.Adapter<AssetRecyclerViewAdapter.ViewHolder> {
    private OnItemClickListener<Asset> onClickAssetListener;
    private final List<Asset> assets;

    public AssetRecyclerViewAdapter() {
        this(new DefaultItemInteractionListener<>());
    }

    public AssetRecyclerViewAdapter(OnItemClickListener<Asset> onClickAssetListener) {
        this.assets = AssetProvider.getAvailableIcons();
        this.onClickAssetListener = onClickAssetListener;
        this.notifyDataSetChanged();
    }

    public void setOnClickAssetListener(OnItemClickListener<Asset> onClickRecordListener) {
        this.onClickAssetListener = onClickRecordListener;
    }

    @Override
    public AssetRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AssetRecyclerViewAdapter.ViewHolder(FragmentAssetListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final AssetRecyclerViewAdapter.ViewHolder holder, int position) {
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
            this.binding = binding;
        }

        public void bind(Asset asset){
            this.binding.assetIcon.setImageResource(asset.id);
            this.itemView.setOnClickListener(v -> onClickAssetListener.onClickItem(asset));
        }
    }

}