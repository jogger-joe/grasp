package net.usemyskills.grasp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.databinding.FragmentRecordListItemBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;

import java.util.ArrayList;
import java.util.List;

public class RecordRecyclerViewAdapter extends RecyclerView.Adapter<RecordRecyclerViewAdapter.ViewHolder> {
    private final OnItemClickListener<RecordWithTypeAndTags> onClickRecordListener;
    private List<RecordWithTypeAndTags> records;

    public RecordRecyclerViewAdapter(List<RecordWithTypeAndTags> recordGroups, OnItemClickListener<RecordWithTypeAndTags> onClickRecordListener) {
        Log.d("GRASP_LOG", "RecordRecyclerViewAdapter construct");
        this.records = recordGroups;
        this.onClickRecordListener = onClickRecordListener;
    }

    public RecordRecyclerViewAdapter(OnItemClickListener<RecordWithTypeAndTags> onClickRecordListener) {
        this(new ArrayList<>(), onClickRecordListener);
    }

    public void setValues(List<RecordWithTypeAndTags> records) {
        Log.d("GRASP_LOG", "RecordRecyclerViewAdapter.setValues: " + records.toString() );
        this.records = records;
        this.notifyDataSetChanged();
    }

    @Override
    public RecordRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("GRASP_LOG", "RecordRecyclerViewAdapter.onCreateViewHolder");
        return new RecordRecyclerViewAdapter.ViewHolder(FragmentRecordListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final RecordRecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d("GRASP_LOG", "RecordRecyclerViewAdapter.onBindViewHolder");
        RecordWithTypeAndTags record = records.get(position);
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
            Log.d("GRASP_LOG", "RecordRecyclerViewAdapter.ViewHolder.create");
            this.binding = binding;
        }

        public void bind(RecordWithTypeAndTags record){
            Log.d("GRASP_LOG", "RecordGroupRecyclerViewAdapter.ViewHolder.bind: " + record.toString());
            RecordDto recordDto = new RecordDto(record);
            this.binding.dateView.setText(recordDto.date);
            this.binding.typeView.setText(recordDto.type);
            this.binding.tagsView.setText(recordDto.tags);
            this.binding.valueView.setText(recordDto.getValueLabel());
            this.itemView.setOnClickListener(v -> onClickRecordListener.onClickItem(record));
        }
    }
}