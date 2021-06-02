package net.usemyskills.grasp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.databinding.FragmentRecordGroupListItemBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.RecordGroup;

import java.util.ArrayList;
import java.util.List;

public class RecordGroupRecyclerViewAdapter extends RecyclerView.Adapter<RecordGroupRecyclerViewAdapter.ViewHolder> {
    private final OnItemClickListener<RecordGroup> onClickRecordListener;
    private List<RecordGroup> recordGroups;

    public RecordGroupRecyclerViewAdapter(List<RecordGroup> recordGroups, OnItemClickListener<RecordGroup> onClickRecordListener) {
        Log.d("GRASP_LOG", "RecordGroupRecyclerViewAdapter construct");
        this.recordGroups = recordGroups;
        this.onClickRecordListener = onClickRecordListener;
    }

    public RecordGroupRecyclerViewAdapter(OnItemClickListener<RecordGroup> onClickRecordListener) {
        this(new ArrayList<>(), onClickRecordListener);
    }

    public void setValues(List<RecordGroup> recordGroups) {
        Log.d("GRASP_LOG", "RecordGroupRecyclerViewAdapter.setValues: " + recordGroups.toString() );
        this.recordGroups = recordGroups;
        this.notifyDataSetChanged();
    }

    @Override
    public RecordGroupRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("GRASP_LOG", "RecordGroupRecyclerViewAdapter.onCreateViewHolder");
        return new ViewHolder(FragmentRecordGroupListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final RecordGroupRecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d("GRASP_LOG", "RecordGroupRecyclerViewAdapter.onBindViewHolder");
        RecordGroup recordGroup = recordGroups.get(position);
        holder.bind(recordGroup);
    }

    @Override
    public int getItemCount() {
        return this.recordGroups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public FragmentRecordGroupListItemBinding binding;

        public ViewHolder(FragmentRecordGroupListItemBinding binding) {
            super(binding.getRoot());
            Log.d("GRASP_LOG", "RecordGroupRecyclerViewAdapter.ViewHolder.create");
            this.binding = binding;
        }

        public void bind(RecordGroup recordGroup){
            Log.d("GRASP_LOG", "RecordGroupRecyclerViewAdapter.ViewHolder.bind: " + recordGroup.toString());
            this.binding.labelView.setText(recordGroup.name);
            this.binding.iconView.setImageResource(recordGroup.iconId);
            this.itemView.setOnClickListener(v -> onClickRecordListener.onClickItem(recordGroup));
        }
    }


}