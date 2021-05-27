package net.usemyskills.grasp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;

import java.util.List;

public class RecordWithTypeAndTagsRecordRecyclerViewAdapter extends BaseRecyclerViewAdapter<RecordWithTypeAndTags> {

    public RecordWithTypeAndTagsRecordRecyclerViewAdapter(List<RecordWithTypeAndTags> values, OnItemClickListener<RecordWithTypeAndTags> onClickSelectableListener) {
        super(values, onClickSelectableListener);
    }

    @Override
    public RecordWithTypeAndTagsRecordRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_record_list_item, parent, false);
        return new RecordWithTypeAndTagsRecordRecyclerViewAdapter.ViewHolder(view, this.onClickSelectableListener);
    }

    public class ViewHolder extends BaseRecyclerViewAdapter<RecordWithTypeAndTags>.ViewHolder {
        public TextView mDateView;
        public TextView mTypeView;
        public TextView mTagsView;
        public TextView mValueView;

        public ViewHolder(View view, OnItemClickListener<RecordWithTypeAndTags> onClickTagListener) {
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
        public void bind(RecordWithTypeAndTags item) {
            super.bind(item);
            RecordDto recordDto = new RecordDto(item);
            this.mDateView.setText(recordDto.date);
            this.mTypeView.setText(recordDto.type);
            this.mTagsView.setText(recordDto.tags);
            this.mValueView.setText(recordDto.value);
        }
    }


}