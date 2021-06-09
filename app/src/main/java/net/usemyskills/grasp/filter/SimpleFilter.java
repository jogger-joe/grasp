package net.usemyskills.grasp.filter;

import android.widget.Filter;

import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.model.HasFilterableValues;

import java.util.ArrayList;
import java.util.List;

public class SimpleFilter<T extends HasFilterableValues> extends Filter {
    private final List<T> allItems;
    private final MutableLiveData<List<T>> filteredItems;

    public SimpleFilter(List<T> allItems) {
        this.allItems = allItems;
        this.filteredItems = new MutableLiveData<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        List<T> filteredList = new ArrayList<>();
        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(allItems);
        } else {
            String filterPattern = constraint.toString().toLowerCase().trim();

            for (T item : allItems) {
                for (String filterValue: item.getFilterValues()) {
                    if (filterValue.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
        }
        FilterResults results = new FilterResults();
        results.values = filteredList;

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        filteredItems.setValue((List<T>)results.values);
    }

    public MutableLiveData<List<T>> getFilteredItems() {
        return filteredItems;
    }
}
