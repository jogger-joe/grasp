package net.usemyskills.grasp.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import net.usemyskills.grasp.databinding.FragmentListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.ViewHolder> implements Filterable {

    private List<DataContainer> filteredItems;
    private List<DataContainer> allItems;

    public DataRecyclerViewAdapter(List<DataContainer> values) {
        this.setValues(values);
    }

    public void setValues(List<DataContainer> values) {
        this.filteredItems = values;
        this.allItems = new ArrayList<>(values);
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        DataContainer item = filteredItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    @Override
    public Filter getFilter() {
        return this.dataFilter;
    }

    private final Filter dataFilter = new Filter() {
        @Override
        protected Filter.FilterResults performFiltering(CharSequence constraint) {
            List<DataContainer> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(allItems);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (DataContainer item : allItems) {
                    if (item.getTagName().toLowerCase().contains(filterPattern) || item.getTypeName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            Filter.FilterResults results = new Filter.FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
            filteredItems.clear();
            filteredItems.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

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

        public void bind(DataContainer item) {
            this.mItem = item;
            this.mDateView.setText(item.getDateString());
            this.mTagsView.setText(item.getTypeName() + " " + item.getTagName());
            this.mValueView.setText(String.valueOf(item.getValue()));
        }
    }
}