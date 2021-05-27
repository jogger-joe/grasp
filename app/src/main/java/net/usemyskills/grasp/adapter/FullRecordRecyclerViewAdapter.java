package net.usemyskills.grasp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.FullRecord;

import java.util.List;

public class FullRecordRecyclerViewAdapter extends BaseRecyclerViewAdapter<FullRecord> {

    public FullRecordRecyclerViewAdapter(List<FullRecord> values, OnItemClickListener<FullRecord> onClickSelectableListener) {
        super(values, onClickSelectableListener);
    }

    @Override
    public FullRecordRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_list_item, parent, false);
        return new FullRecordRecyclerViewAdapter.ViewHolder(view, this.onClickSelectableListener);
    }

    public class ViewHolder extends BaseRecyclerViewAdapter<FullRecord>.ViewHolder {
        public TextView mDateView;
        public TextView mTypeView;
        public TextView mTagsView;
        public TextView mValueView;

        public ViewHolder(View view, OnItemClickListener<FullRecord> onClickTagListener) {
            super(view, onClickTagListener);
        }

        @Override
        protected void attachView(View view) {
            super.attachView(view);
            this.mDateView = view.findViewById(R.id.dateView);
            this.mTypeView = view.findViewById(R.id.typeView);
            this.mTagsView = view.findViewById(R.id.tagsView);
            this.mValueView = view.findViewById(R.id.valueView);
        }

        @Override
        public void bind(FullRecord item) {
            super.bind(item);
//            this.mDateView.setText(item.getRecord());
//            this.mTypeView.setText(item.getName());
//            this.mTagsView.setText(item.getName());
//            this.mValueView.setText(item.getName());
        }
    }


}