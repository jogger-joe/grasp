package net.usemyskills.grasp.listener;

import android.util.Log;

public class DefaultItemInteractionListener<T> implements OnItemClickListener<T> {
    @Override
    public void onClickItem(T item) {
        Log.d("DefaultItemInteractionListener: ", item.toString());
    }
}
