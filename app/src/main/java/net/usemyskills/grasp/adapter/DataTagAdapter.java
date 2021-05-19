package net.usemyskills.grasp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.usemyskills.grasp.persistence.entity.DataTag;

import java.util.List;

public class DataTagAdapter<T extends DataTag> extends ArrayAdapter<T> implements SpinnerAdapter {

    public DataTagAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void setDataTags(List<T> dataTags) {
        this.clear();
        this.addAll(dataTags);
    }

    @Override
    public int getPosition(@Nullable T item) {
        return item != null ? item.getTagId() : 0;
    }
}
