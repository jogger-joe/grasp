package net.usemyskills.grasp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.databinding.FragmentTagListItemBinding;
import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagRecyclerViewAdapter<T extends Tag> extends RecyclerView.Adapter<TagRecyclerViewAdapter<T>.ViewHolder> {
    private final OnItemClickListener<T> onClickTagListener;
    private List<T> tags;

    public TagRecyclerViewAdapter(List<T> tags, OnItemClickListener<T> onClickRecordListener) {
        Log.d("GRASP_LOG", "TagRecyclerViewAdapter construct");
        this.tags = tags;
        this.onClickTagListener = onClickRecordListener;
    }

    public TagRecyclerViewAdapter(OnItemClickListener<T> onClickRecordListener) {
        this(new ArrayList<>(), onClickRecordListener);
    }

    public void setValues(List<T> tags) {
        Log.d("GRASP_LOG", "TagRecyclerViewAdapter.setValues: " + tags.toString() );
        this.tags = tags;
        this.notifyDataSetChanged();
    }

    @Override
    public TagRecyclerViewAdapter<T>.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("GRASP_LOG", "TagRecyclerViewAdapter.onCreateViewHolder");
        return new TagRecyclerViewAdapter<T>.ViewHolder(FragmentTagListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final TagRecyclerViewAdapter<T>.ViewHolder holder, int position) {
        Log.d("GRASP_LOG", "TagRecyclerViewAdapter.onBindViewHolder");
        T tag = tags.get(position);
        holder.bind(tag);
    }

    @Override
    public int getItemCount() {
        return this.tags.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public FragmentTagListItemBinding binding;

        public ViewHolder(FragmentTagListItemBinding binding) {
            super(binding.getRoot());
            Log.d("GRASP_LOG", "TagRecyclerViewAdapter.ViewHolder.create");
            this.binding = binding;
        }

        public void bind(T tag){
            Log.d("GRASP_LOG", "TagRecyclerViewAdapter.ViewHolder.bind: " + tag.toString());
            this.binding.labelView.setText(tag.name);
            this.itemView.setOnClickListener(v -> onClickTagListener.onClickItem(tag));
        }
    }

}