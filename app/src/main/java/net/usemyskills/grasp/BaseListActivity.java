package net.usemyskills.grasp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.usemyskills.grasp.listener.OnItemClickListener;
import net.usemyskills.grasp.persistence.entity.BaseEntity;
import net.usemyskills.grasp.view.ListFragment;

public abstract class BaseListActivity<T extends BaseEntity> extends AppCompatActivity implements OnItemClickListener<T> {

    public static final int NEW_DATA_RESULT_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, this.getListFragment(), null)
                    .commit();
        }

        FloatingActionButton addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(view -> this.onClickAdd());
    }

    public abstract ListFragment<T> getListFragment();

    public abstract void onClickAdd();

    public abstract void onNewDataResult(Intent data);

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == NEW_DATA_RESULT_REQUEST_CODE && resultCode == RESULT_OK) {
                this.onNewDataResult(data);
            }
        } catch (Exception exception) {
            Toast.makeText(
                    getApplicationContext(),
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}