package net.usemyskills.grasp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.R;

public class DataContainerViewHolder extends RecyclerView.ViewHolder {
    private final TextView dataContainerItemView;

    private DataContainerViewHolder(View itemView) {
        super(itemView);
        this.dataContainerItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        dataContainerItemView.setText(text);
    }

    public static DataContainerViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new DataContainerViewHolder(view);
    }
}
