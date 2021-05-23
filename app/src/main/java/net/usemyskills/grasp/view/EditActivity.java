package net.usemyskills.grasp.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import net.usemyskills.grasp.R;

public class EditActivity extends AppCompatActivity {
    public static final String DATA_REPLY = "net.usemyskills.grasp.data_reply";
    private EditFragment editFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        if (savedInstanceState == null) {
            this.editFragment = EditFragment.newInstance(this.getSupportFragmentManager());
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.edit_container, this.editFragment)
                    .commitNow();
        }

        this.findViewById(R.id.button_save).setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            try {
                replyIntent.putExtra(DATA_REPLY, this.editFragment.getDto());
                setResult(RESULT_OK, replyIntent);
                finish();
            } catch (Exception exception) {
                Toast.makeText(
                        getApplicationContext(),
                        exception.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
        // cancel button
        this.findViewById(R.id.button_cancel).setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            setResult(RESULT_CANCELED, replyIntent);
            finish();
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}