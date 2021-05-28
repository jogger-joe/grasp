package net.usemyskills.grasp.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import net.usemyskills.grasp.listener.OnItemClickListener;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter<T>.ViewHolder> {
    protected List<T> mValues;
    protected final OnItemClickListener<T> onClickSelectableListener;

    public BaseRecyclerViewAdapter(List<T> values, OnItemClickListener<T> onClickSelectableListener) {
        this.mValues = values;
        this.onClickSelectableListener = onClickSelectableListener;
    }

    public void setValues(List<T> values) {
        this.mValues = values;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        T item = mValues.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public T item;
        private final OnItemClickListener<T> onClickTagListener;

        public ViewHolder(View view, OnItemClickListener<T> onClickTagListener) {
            super(view);
            this.attachView(view);
            this.itemView.setOnClickListener(this);
            this.onClickTagListener = onClickTagListener;
        }

        protected void attachView(View view) {}

        public void bind(T item) {
            this.item = item;
        }

        @Override
        public void onClick(View v) {
            this.onClickTagListener.onClickItem(this.item);
        }
    }


}