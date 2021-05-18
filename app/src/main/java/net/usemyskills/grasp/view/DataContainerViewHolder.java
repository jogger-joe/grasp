package net.usemyskills.grasp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.persistence.entity.DataContainer;

public class DataContainerViewHolder extends RecyclerView.ViewHolder {
    private final TextView mDateView;
    private final TextView mTypeTagView;
    private final TextView mTagView;
    private final TextView mValueView;

    private DataContainerViewHolder(View itemView) {
        super(itemView);
        this.mDateView = itemView.findViewById(R.id.dateView);
        this.mTypeTagView = itemView.findViewById(R.id.typeTagView);
        this.mTagView = itemView.findViewById(R.id.tagView);
        this.mValueView = itemView.findViewById(R.id.valueView);
    }

    public void bind(DataContainer dataContainer) {
        this.mDateView.setText(dataContainer.getDateString());
        this.mTypeTagView.setText(dataContainer.getTypeName());
        this.mTagView.setText(dataContainer.getTagName());
        this.mValueView.setText(String.valueOf(dataContainer.getValue()));
    }

    public static DataContainerViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_container_item, parent, false);
        return new DataContainerViewHolder(view);
    }
}
