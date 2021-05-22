package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.exceptions.ModelValidationException;
import net.usemyskills.grasp.model.DataDto;

import java.util.Date;

public class EditFragment extends Fragment {

    private TextView mDateView;
    private EditText mTypeView;
    private EditText mTagView;
    private EditText mValueView;

    public EditFragment() {
    }

    public static EditFragment newInstance() {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        this.mDateView = view.findViewById(R.id.date);
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