package net.usemyskills.grasp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagRecyclerViewAdapter<T extends Tag> extends BaseRecyclerViewAdapter<T> {

    public TagRecyclerViewAdapter(List<T> values, OnItemClickListener<T> onClickSelectableListener) {
        super(values, onClickSelectableListener);
    }

    public TagRecyclerViewAdapter(OnItemClickListener<T> onClickSelectableListener) {
        this(new ArrayList<>(), onClickSelectableListener);
    }

    @Override
    public TagRecyclerViewAdapter<T>.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tag_list_item, parent, false);
        return new TagRecyclerViewAdapter<T>.ViewHolder(view, this.onClickSelectableListener);
    }

    public class ViewHolder extends BaseRecyclerViewAdapter<T>.ViewHolder {
        public TextView mLabelView;

        public ViewHolder(View view, OnItemClickListener<T> onClickTagListener) {
            super(view, onClickTagListener);
        }

        @Override
        protected void attachView(View view) {
            super.attachView(view);
            this.mLabelView = view.findViewById(R.id.labelView);
        }

        @Override
        public void bind(T item) {
            super.bind(item);
            this.mLabelView.setText(item.name);
        }
    }


}