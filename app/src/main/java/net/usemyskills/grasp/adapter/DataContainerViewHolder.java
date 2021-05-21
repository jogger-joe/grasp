package net.usemyskills.grasp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.persistence.entity.DataContainer;

public class DataContainerViewHolder extends RecyclerView.ViewHolder {
    private final TextView mDateView;
    private final TextView mTagsView;
    private final TextView mValueView;

    private DataContainerViewHolder(View itemView) {
        super(itemView);
        this.mDateView = itemView.findViewById(R.id.dateView);
        this.mTagsView = itemView.findViewById(R.id.tagsView);
        this.mValueView = itemView.findViewById(R.id.valueView);
    }

    public void bind(DataContainer dataContainer) {
        this.mDateView.setText(dataContainer.getDateString());
        this.mTagsView.setText(dataContainer.getTagsLabel());
        this.mValueView.setText(dataContainer.getValueLabel());
    }

    public static DataContainerViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_container_item, parent, false);
        return new DataContainerViewHolder(view);
    }
}
