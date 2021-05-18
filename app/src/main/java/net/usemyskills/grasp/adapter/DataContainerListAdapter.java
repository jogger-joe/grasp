package net.usemyskills.grasp.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import net.usemyskills.grasp.persistence.entity.DataContainer;
import net.usemyskills.grasp.view.DataContainerViewHolder;

public class DataContainerListAdapter extends ListAdapter<DataContainer, DataContainerViewHolder> {

    public DataContainerListAdapter(@NonNull DiffUtil.ItemCallback<DataContainer> diffCallback) {
        super(diffCallback);
    }

    @Override
    public DataContainerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DataContainerViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(DataContainerViewHolder holder, int position) {
        DataContainer current = getItem(position);
        holder.bind(current);
    }

    public static class DataContainerDiff extends DiffUtil.ItemCallback<DataContainer> {

        @Override
        public boolean areItemsTheSame(@NonNull DataContainer oldItem, @NonNull DataContainer newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DataContainer oldItem, @NonNull DataContainer newItem) {
            return oldItem.getId() == newItem.getId();
        }
    }
}
