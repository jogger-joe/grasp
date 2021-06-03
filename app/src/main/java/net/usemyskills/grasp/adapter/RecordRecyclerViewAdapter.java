package net.usemyskills.grasp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.databinding.FragmentRecordListItemBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.model.RecordDto;

import java.util.ArrayList;
import java.util.List;

public class RecordRecyclerViewAdapter extends RecyclerView.Adapter<RecordRecyclerViewAdapter.ViewHolder> {
    private final OnItemClickListener<RecordDto> onClickRecordListener;
    private List<RecordDto> records;
    private int newColor;

    public RecordRecyclerViewAdapter(List<RecordDto> recordGroups, OnItemClickListener<RecordDto> onClickRecordListener) {
        Log.d("GRASP_LOG", "RecordRecyclerViewAdapter construct");
        this.records = recordGroups;
        this.onClickRecordListener = onClickRecordListener;
    }

    public RecordRecyclerViewAdapter(OnItemClickListener<RecordDto> onClickRecordListener) {
        this(new ArrayList<>(), onClickRecordListener);
    }

    public void setValues(List<RecordDto> records) {
        Log.d("GRASP_LOG", "RecordRecyclerViewAdapter.setValues: " + records.toString() );
        this.records = new ArrayList<>(records);
        this.records.add(new RecordDto());
        this.notifyDataSetChanged();
    }

    @Override
    public RecordRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("GRASP_LOG", "RecordRecyclerViewAdapter.onCreateViewHolder");
        this.newColor = parent.getContext().getResources().getColor(R.color.design_default_color_secondary);
        return new RecordRecyclerViewAdapter.ViewHolder(FragmentRecordListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final RecordRecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d("GRASP_LOG", "RecordRecyclerViewAdapter.onBindViewHolder");
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
            Log.d("GRASP_LOG", "RecordRecyclerViewAdapter.ViewHolder.create");
            this.binding = binding;
        }

        public void bind(RecordDto record){
            Log.d("GRASP_LOG", "RecordGroupRecyclerViewAdapter.ViewHolder.bind: " + record.toString());
            if (record.isPlaceholder()) {
                this.binding.dateView.setText("");
                this.binding.typeView.setText(R.string.create_entry);
                this.binding.tagsView.setText("");
                this.binding.valueView.setText("");
                this.itemView.setBackgroundColor(newColor);
            } else {
                this.binding.dateView.setText(record.getDateLabel());
                this.binding.typeView.setText(record.getTypeLabel());
                this.binding.tagsView.setText(record.getTagsLabel());
                this.binding.valueView.setText(record.getValueLabel());
            }
            this.itemView.setOnClickListener(v -> onClickRecordListener.onClickItem(record));
        }
    }
}