package net.usemyskills.grasp.adapter;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.databinding.FragmentRecordListItemBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.model.TagDto;

import java.util.ArrayList;
import java.util.List;

public class RecordRecyclerViewAdapter extends RecyclerView.Adapter<RecordRecyclerViewAdapter.ViewHolder> {
    private final OnItemClickListener<RecordDto> onClickRecordListener;
    private List<RecordDto> records;

    public RecordRecyclerViewAdapter(List<RecordDto> recordGroups, OnItemClickListener<RecordDto> onClickRecordListener) {
        this.records = recordGroups;
        this.onClickRecordListener = onClickRecordListener;
    }

    public RecordRecyclerViewAdapter(OnItemClickListener<RecordDto> onClickRecordListener) {
        this(new ArrayList<>(), onClickRecordListener);
    }

    public void setValues(List<RecordDto> records) {
        Log.d("GRASP_LOG", "RecordRecyclerViewAdapter.setValues: " + records.toString() );
        this.records = new ArrayList<>(records);
        this.notifyDataSetChanged();
    }

    @Override
    public RecordRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecordRecyclerViewAdapter.ViewHolder(FragmentRecordListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final RecordRecyclerViewAdapter.ViewHolder holder, int position) {
        RecordDto record = records.get(position);
        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        return this.records.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public FragmentRecordListItemBinding binding;

        public ViewHolder(FragmentRecordListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(RecordDto record){
            this.binding.dateView.setText(record.getDateLabel());
            this.binding.typeView.setText(record.getTypeLabel());
            this.binding.tagsView.setText(record.getTagsLabel());
            this.binding.valueView.setText(record.getValueLabel());
            this.itemView.setOnClickListener(v -> onClickRecordListener.onClickItem(record));
        }
    }
}