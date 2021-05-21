package net.usemyskills.grasp.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.List;

public class DataTagAdapter<T extends Tag> extends ArrayAdapter<T> {

    public DataTagAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void setDataTags(List<T> dataTags) {
        this.clear();
        this.addAll(dataTags);
    }

    @Override
    public int getPosition(@Nullable T item) {
        return item != null ? (int)item.getId() : 0;
    }
}
