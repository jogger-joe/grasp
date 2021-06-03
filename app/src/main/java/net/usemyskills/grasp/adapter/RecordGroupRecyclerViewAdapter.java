package net.usemyskills.grasp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.databinding.FragmentRecordGroupListItemBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.model.RecordGroupDto;

import java.util.ArrayList;
import java.util.List;

public class RecordGroupRecyclerViewAdapter extends RecyclerView.Adapter<RecordGroupRecyclerViewAdapter.ViewHolder> {
    private final OnItemClickListener<RecordGroupDto> onClickRecordListener;
    private List<RecordGroupDto> recordGroups;

    public RecordGroupRecyclerViewAdapter(List<RecordGroupDto> recordGroups, OnItemClickListener<RecordGroupDto> onClickRecordListener) {
        Log.d("GRASP_LOG", "RecordGroupRecyclerViewAdapter construct");
        this.recordGroups = recordGroups;
        this.onClickRecordListener = onClickRecordListener;
    }

    public RecordGroupRecyclerViewAdapter(OnItemClickListener<RecordGroupDto> onClickRecordListener) {
        this(new ArrayList<>(), onClickRecordListener);
    }

    public void setValues(List<RecordGroupDto> recordGroups) {
        Log.d("GRASP_LOG", "RecordGroupRecyclerViewAdapter.setValues: " + recordGroups.toString() );
        this.recordGroups = new ArrayList<>(recordGroups);
        this.recordGroups.add(new RecordGroupDto("create new", R.drawable.ic_add));
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
        RecordGroupDto recordGroup = recordGroups.get(position);
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

        public void bind(RecordGroupDto recordGroup){
            Log.d("GRASP_LOG", "RecordGroupRecyclerViewAdapter.ViewHolder.bind: " + recordGroup.toString());
            this.binding.labelView.setText(recordGroup.name);
            this.binding.iconView.setImageResource(recordGroup.iconId);
            this.itemView.setOnClickListener(v -> onClickRecordListener.onClickItem(recordGroup));
        }
    }


}