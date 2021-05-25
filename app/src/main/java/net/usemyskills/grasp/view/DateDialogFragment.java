package net.usemyskills.grasp.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateDialogFragment extends DialogFragment {
    private final Calendar calendar;
    private final MutableLiveData<Date> selectedDate;

    public DateDialogFragment(Date initialDate) {
        this.selectedDate = new MutableLiveData<>(initialDate);
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(initialDate);
    }

    public DateDialogFragment() {
        this(new Date());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(this.getContext(), (view, year, month, dayOfMonth) -> this.selectedDate.setValue(new GregorianCalendar(year, month, dayOfMonth).getTime()), this.calendar.get(Calendar.YEAR), this.calendar.get(Calendar.MONTH), this.calendar.get(Calendar.DAY_OF_MONTH));
    }

    public MutableLiveData<Date> getSelectedDate() {
        return selectedDate;
    }
}
