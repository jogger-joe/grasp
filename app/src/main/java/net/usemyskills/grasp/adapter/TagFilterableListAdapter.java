package net.usemyskills.grasp.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filterable;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.List;

public class TagFilterableListAdapter<T extends Tag> extends ArrayAdapter<T> implements Filterable {
    protected final OnItemClickListener<T> onClickSelectableListener;

    public TagFilterableListAdapter(Context context, OnItemClickListener<T> onClickSelectableListener) {
        super(context, R.layout.fragment_tag_list_item);
        this.onClickSelectableListener = onClickSelectableListener;
    }

    public void setValues(List<T> values) {
        Log.d("GRASP_LOG", "setValues at " + this.getClass().toString());
        this.clear();
        this.addAll(values);
    }


}