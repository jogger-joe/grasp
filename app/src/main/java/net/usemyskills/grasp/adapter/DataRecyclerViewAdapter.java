package net.usemyskills.grasp.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import net.usemyskills.grasp.databinding.FragmentListItemBinding;
import net.usemyskills.grasp.persistence.entity.DataContainer;

import java.util.List;

public class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.ViewHolder> {

    private List<DataContainer> mValues;

    public DataRecyclerViewAdapter(List<DataContainer> values) {
        this.mValues = values;
    }

    public void setValues(List<DataContainer> values) {
        this.mValues = values;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        DataContainer item = mValues.get(position);
        holder.mItem = item;
        holder.mDateView.setText(item.getDateString());
        holder.mTagsView.setText(item.getTypeName() + " " + item.getTagName());
        holder.mValueView.setText(String.valueOf(item.getValue()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mDateView;
        public final TextView mTagsView;
        public final TextView mValueView;
        public DataContainer mItem;

        public ViewHolder(FragmentListItemBinding binding) {
            super(binding.getRoot());
            mDateView = binding.dateView;
            mTagsView = binding.tagsView;
            mValueView = binding.valueView;
        }
    }
}