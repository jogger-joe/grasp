package net.usemyskills.grasp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.RecordGroup;

import java.util.List;

public class RecordGroupRecyclerViewAdapter extends BaseRecyclerViewAdapter<RecordGroup> implements Filterable {

    private Filter dataFilter;

    public RecordGroupRecyclerViewAdapter(List<RecordGroup> values, OnItemClickListener<RecordGroup> onClickSelectableListener, Filter dataFilter) {
        super(values, onClickSelectableListener);
        this.dataFilter = dataFilter;
    }

    public RecordGroupRecyclerViewAdapter(List<RecordGroup> values, OnItemClickListener<RecordGroup> onClickSelectableListener) {
        super(values, onClickSelectableListener);
    }

    @Override
    public RecordGroupRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_group_list_item, parent, false);
        return new RecordGroupRecyclerViewAdapter.ViewHolder(view, this.onClickSelectableListener);
    }

    @Override
    public Filter getFilter() {
        return this.dataFilter;
    }

    public class ViewHolder extends BaseRecyclerViewAdapter<RecordGroup>.ViewHolder {
        public TextView mLabelView;
        public ImageView mIconView;

        public ViewHolder(View view, OnItemClickListener<RecordGroup> onClickTagListener) {
            super(view, onClickTagListener);
        }

        @Override
        protected void attachView(View view) {
            super.attachView(view);
            this.mLabelView = view.findViewById(R.id.labelView);
            this.mIconView = view.findViewById(R.id.iconView);
        }

        @Override
        public void bind(RecordGroup item) {
            super.bind(item);
            this.mLabelView.setText(item.name);
            this.mIconView.setImageResource(item.iconId);
        }
    }


}