package net.usemyskills.grasp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.DataTagAdapter;
import net.usemyskills.grasp.persistence.entity.DataTag;
import net.usemyskills.grasp.persistence.entity.DataTypeTag;
import net.usemyskills.grasp.viewmodel.DataTagViewModel;

import java.util.List;

public class NewDataContainerActivity extends AppCompatActivity {

    private DataTagViewModel dataTagViewModel;

    public static final String DATE_REPLY = "net.usemyskills.grasp.view.DATE_REPLY";
    public static final String DATA_TYPE_TAG_REPLY = "net.usemyskills.grasp.view.DATA_TYPE_TAG_REPLY";
    public static final String DATA_TAG_REPLY = "net.usemyskills.grasp.view.DATA_TAG_REPLY";
    public static final String VALUE_REPLY = "net.usemyskills.grasp.view.VALUE_REPLY";

    private EditText mDateView;
    private Spinner mDataTypeTagView;
    private Spinner mDataTagView;
    private EditText mValueView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data_container);
        this.mDateView = findViewById(R.id.date);
        this.mDataTypeTagView = findViewById(R.id.data_type_tag);
        this.mDataTagView = findViewById(R.id.data_tag);
        this.mValueView = findViewById(R.id.value);

        this.dataTagViewModel = new ViewModelProvider(this.getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(DataTagViewModel.class);

        DataTagAdapter<DataTypeTag> dataTypeTagsAdapter = new DataTagAdapter<>(this, R.layout.data_tag_item);
        this.mDataTypeTagView.setAdapter(dataTypeTagsAdapter);
        this.dataTagViewModel.getAllDataTypeTags().observe(this, dataTypeTagsAdapter::setDataTags);

        DataTagAdapter<DataTag> dataTagsAdapter = new DataTagAdapter<>(this, R.layout.data_tag_item);
        this.mDataTagView.setAdapter(dataTagsAdapter);
        this.dataTagViewModel.getAllDataTags().observe(this, dataTagsAdapter::setDataTags);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mDateView.getText()) ||
                TextUtils.isEmpty(mValueView.getText()) ||
                mDataTypeTagView.getSelectedItemId() == 0) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra(DATE_REPLY, mDateView.getText().toString());
                replyIntent.putExtra(DATA_TYPE_TAG_REPLY, mDataTypeTagView.getSelectedItemId());
                replyIntent.putExtra(DATA_TAG_REPLY, mDataTagView.getSelectedItemId());
                replyIntent.putExtra(VALUE_REPLY, mValueView.getText().toString());
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

    }

}