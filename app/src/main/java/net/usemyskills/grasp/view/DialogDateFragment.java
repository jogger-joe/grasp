package net.usemyskills.grasp.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class DialogDateFragment extends DialogFragment {
    private final Calendar calendar;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    public DialogDateFragment(Date initialDate, DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(initialDate);
        this.onDateSetListener = onDateSetListener;
    }

    public DialogDateFragment(DatePickerDialog.OnDateSetListener onDateSetListener) {
        this(new Date(), onDateSetListener);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(this.getContext(), this.onDateSetListener, this.calendar.get(Calendar.YEAR), this.calendar.get(Calendar.MONTH), this.calendar.get(Calendar.DAY_OF_MONTH));
    }
}
