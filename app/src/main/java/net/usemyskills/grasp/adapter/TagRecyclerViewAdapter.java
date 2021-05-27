package net.usemyskills.grasp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.List;

public class TagRecyclerViewAdapter<T extends Tag> extends BaseRecyclerViewAdapter<RecordGroup> {

    public TagRecyclerViewAdapter(List<RecordGroup> values, OnItemClickListener<RecordGroup> onClickSelectableListener) {
        super(values, onClickSelectableListener);
    }

    @Override
    public TagRecyclerViewAdapter<T>.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tag_list_item, parent, false);
        return new TagRecyclerViewAdapter<T>.ViewHolder(view, this.onClickSelectableListener);
    }

    public class ViewHolder extends BaseRecyclerViewAdapter<RecordGroup>.ViewHolder {
        public TextView mLabelView;

        public ViewHolder(View view, OnItemClickListener<RecordGroup> onClickTagListener) {
            super(view, onClickTagListener);
        }

        @Override
        protected void attachView(View view) {
            super.attachView(view);
            this.mLabelView = (TextView)view.findViewById(R.id.labelView);
        }

        @Override
        public void bind(RecordGroup item) {
            super.bind(item);
            this.mLabelView.setText(item.name);
        }
    }


}