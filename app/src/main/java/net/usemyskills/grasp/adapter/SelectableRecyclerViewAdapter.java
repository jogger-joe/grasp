package net.usemyskills.grasp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.databinding.FragmentSelectableListItemBinding;
import net.usemyskills.grasp.model.Selectable;

import java.util.List;

public class SelectableRecyclerViewAdapter<T extends Selectable> extends RecyclerView.Adapter<SelectableRecyclerViewAdapter<T>.ViewHolder> {
    private List<T> mValues;
    private final OnClickSelectableListener<T> onClickSelectableListener;
    private T selectedTag;

    public SelectableRecyclerViewAdapter(List<T> values) {
        this.mValues = values;
        this.onClickSelectableListener = tag -> selectedTag = tag;
    }

    public T getSelectedTag() {
        return selectedTag;
    }

    public void setValues(List<T> values) {
        this.mValues = values;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentSelectableListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), this.onClickSelectableListener);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        T item = mValues.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mLabelView;
        public T item;
        private final OnClickSelectableListener<T> onClickTagListener;

        public ViewHolder(FragmentSelectableListItemBinding binding, OnClickSelectableListener<T> onClickTagListener) {
            super(binding.getRoot());
            this.mLabelView = binding.labelView;
            this.itemView.setOnClickListener(this);
            this.onClickTagListener = onClickTagListener;
        }

        public void bind(T item) {
            this.mLabelView.setText(item.getLabel());
            this.item = item;
        }

        @Override
        public void onClick(View v) {
            this.onClickTagListener.onClickTag(this.item);
        }
    }

    public interface OnClickSelectableListener<T extends Selectable> {
        void onClickTag(T tag);
    }
}