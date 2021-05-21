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
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.DataTagAdapter;
import net.usemyskills.grasp.model.DataDto;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;
import net.usemyskills.grasp.viewmodel.TagViewModel;

import java.util.Date;
import java.util.Locale;

public class EditDataContainerActivity extends AppCompatActivity {

    private TagViewModel tagViewModel;

    public static final String DATA_REPLY = "net.usemyskills.grasp.view.DATA";

    public static final int DATE_PICKER_DIALOG = 1337;

    private Calendar calendar;
    private TextView mDateView;
    private Spinner mTypeView;
    private Spinner mTagView;
    private EditText mValueView;

    private Date selectedDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindViewElements();
        // init viewModel
        this.tagViewModel = new ViewModelProvider(this.getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(TagViewModel.class);
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
        this.mTypeView = findViewById(R.id.type);
        this.mTagView = findViewById(R.id.tag);
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
        DataTagAdapter<Type> typesAdapter = new DataTagAdapter<>(this, R.layout.tag_item);
        this.mTypeView.setAdapter(typesAdapter);
        this.tagViewModel.getAllDataTypeTags().observe(this, typesAdapter::setDataTags);
        // init dataTag element
        DataTagAdapter<Tag> tagsAdapter = new DataTagAdapter<>(this, R.layout.tag_item);
        this.mTagView.setAdapter(tagsAdapter);
        this.tagViewModel.getAllDataTags().observe(this, tagsAdapter::setDataTags);
    }

    private void initButtons() {

        // save button
        this.findViewById(R.id.button_save).setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            try {
                Tag type = (Type)this.mTypeView.getSelectedItem();
                Tag tag = (Tag)this.mTagView.getSelectedItem();
                DataDto data = new DataDto(0, type != null ? type.getId() : 0, tag != null ? tag.getId() : 0, this.selectedDate, Integer.parseInt(this.mValueView.getText().toString()));
                data.validate();
                replyIntent.putExtra(DATA_REPLY, data);
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

}