package net.usemyskills.grasp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.DataTagAdapter;
import net.usemyskills.grasp.persistence.entity.DataTag;
import net.usemyskills.grasp.persistence.entity.DataType;
import net.usemyskills.grasp.viewmodel.DataTagViewModel;

import java.util.Date;
import java.util.Locale;

public class EditDataContainerActivity extends AppCompatActivity {

    private DataTagViewModel dataTagViewModel;

    public static final String DATE_REPLY = "net.usemyskills.grasp.view.DATE_REPLY";
    public static final String DATA_TYPE_TAG_REPLY = "net.usemyskills.grasp.view.DATA_TYPE_TAG_REPLY";
    public static final String DATA_TAG_REPLY = "net.usemyskills.grasp.view.DATA_TAG_REPLY";
    public static final String VALUE_REPLY = "net.usemyskills.grasp.view.VALUE_REPLY";

    public static final int DATE_PICKER_DIALOG = 1337;

    private Calendar calendar;
    private TextView mDateView;
    private AutoCompleteTextView mDataTypeTagView;
    private MultiAutoCompleteTextView mDataTagsView;
    private EditText mValueView;

    private Date selectedDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindViewElements();
        // init viewModel
        this.dataTagViewModel = new ViewModelProvider(this.getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(DataTagViewModel.class);
        this.initCalendar();
        this.initAdapter();
        this.initButtons();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DATE_PICKER_DIALOG) {
            return new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    updateSelectedDate(new GregorianCalendar(year-1900, month, dayOfMonth).getTime());
                }
            }, calendar.get(Calendar.YEAR+1900), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        } else {
            return super.onCreateDialog(id);
        }
    }

    private void bindViewElements() {
        this.setContentView(R.layout.activity_edit_data_container);
        this.mDateView = findViewById(R.id.date);
        this.mDataTypeTagView = findViewById(R.id.data_type_tag);
        this.mDataTagsView = findViewById(R.id.data_tag);
        this.mValueView = findViewById(R.id.value);
    }

    private void initCalendar() {
        this.updateSelectedDate(null);
        calendar = Calendar.getInstance();
        calendar.setTime(this.selectedDate);
        this.mDateView.setOnClickListener(v -> showDialog(DATE_PICKER_DIALOG));
    }

    private void updateSelectedDate(Date date) {
        if (date == null) {
            date = new Date();
        }
        this.selectedDate = date;
        this.mDateView.setText(new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY).format(date));
    }

    private void initAdapter() {
        // init dataTypeTag element
        DataTagAdapter<DataType> dataTypeTagsAdapter = new DataTagAdapter<>(this, R.layout.data_tag_item);
        this.mDataTypeTagView.setAdapter(dataTypeTagsAdapter);
        this.dataTagViewModel.getAllDataTypeTags().observe(this, dataTypeTagsAdapter::setDataTags);
        // init dataTag element
        DataTagAdapter<DataTag> dataTagsAdapter = new DataTagAdapter<>(this, R.layout.data_tag_item);
        this.mDataTagsView.setAdapter(dataTagsAdapter);
        this.mDataTagsView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        this.dataTagViewModel.getAllDataTags().observe(this, dataTagsAdapter::setDataTags);
    }

    private void initButtons() {
        // save button
        this.findViewById(R.id.button_save).setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mDateView.getText()) ||
                    TextUtils.isEmpty(mValueView.getText()) ||
                    this.mDataTypeTagView.getListSelection() == 0) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra(DATE_REPLY, this.mDateView.getText().toString());
                replyIntent.putExtra(DATA_TYPE_TAG_REPLY, this.mDataTypeTagView.getListSelection());
                replyIntent.putExtra(DATA_TAG_REPLY, this.mDataTagsView.getListSelection());
                replyIntent.putExtra(VALUE_REPLY, this.mValueView.getText().toString());
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
        // cancel button
        this.findViewById(R.id.button_cancel).setOnClickListener(view -> {
            finish();
        });
    }

}