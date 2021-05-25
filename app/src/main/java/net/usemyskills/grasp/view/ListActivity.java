package net.usemyskills.grasp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.usemyskills.grasp.R;

public class ListActivity extends AppCompatActivity {

    public static final int NEW_DATA_RESULT_REQUEST_CODE = 1;
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        if (savedInstanceState == null) {

            listFragment = ListFragment.newInstance(
                    new ViewModelProvider(this.getViewModelStore(),ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())), 1);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_container, listFragment)
                    .commitNow();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, EditActivity.class);
            startActivityForResult(intent, NEW_DATA_RESULT_REQUEST_CODE);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(this.listFragment);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == NEW_DATA_RESULT_REQUEST_CODE && resultCode == RESULT_OK) {
                listFragment.handleActivityResult(data);
            }
        } catch (Exception exception) {
            Toast.makeText(
                    getApplicationContext(),
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

}