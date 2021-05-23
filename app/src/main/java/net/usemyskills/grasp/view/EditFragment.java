package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.exceptions.ModelValidationException;
import net.usemyskills.grasp.model.DataDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditFragment extends Fragment {

    private TextView mDateView;
    private TextView mTypeView;
    private TextView mTagView;
    private EditText mValueView;

    FragmentManager fragmentManager;

    public EditFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static EditFragment newInstance(FragmentManager fragmentManager) {
        EditFragment fragment = new EditFragment(fragmentManager);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        // init mdateview
        this.mDateView = view.findViewById(R.id.date);
        DateDialogFragment dateDialogFragment = new DateDialogFragment();
        dateDialogFragment.getSelectedDate().observe(this, date -> this.mDateView.setText(new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY).format(date)));
        this.mDateView.setOnClickListener(v -> dateDialogFragment.show(fragmentManager, "dialog"));

        this.mTypeView = view.findViewById(R.id.type);
        this.mTagView = view.findViewById(R.id.tag);
        this.mValueView = view.findViewById(R.id.value);
        return view;
    }



    public DataDto getDto() throws ModelValidationException {

        DataDto dataDto = new DataDto(0, 0, 0, new Date(), Integer.parseInt(this.mValueView.getText().toString()));
        dataDto.validate();
        return dataDto;
    }
}