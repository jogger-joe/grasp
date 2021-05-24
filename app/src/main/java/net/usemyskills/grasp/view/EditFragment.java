package net.usemyskills.grasp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.adapter.SelectableRecyclerViewAdapter;
import net.usemyskills.grasp.exceptions.ModelValidationException;
import net.usemyskills.grasp.model.DataDto;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;
import net.usemyskills.grasp.viewmodel.TagViewModel;
import net.usemyskills.grasp.viewmodel.TypeViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EditFragment extends Fragment {

    private TextView mDateView;
    private TextView mTypeView;
    private TextView mTagView;
    private EditText mValueView;

    private final FragmentManager fragmentManager;
    private final TagViewModel tagViewModel;
    private final TypeViewModel typeViewModel;


    public EditFragment(FragmentManager fragmentManager, ViewModelProvider viewModelProvider) {

        this.fragmentManager = fragmentManager;
        this.tagViewModel = viewModelProvider.get(TagViewModel.class);
        this.typeViewModel = viewModelProvider.get(TypeViewModel.class);
    }

    public static EditFragment newInstance(FragmentManager fragmentManager, ViewModelProvider viewModelProvider) {
        EditFragment fragment = new EditFragment(fragmentManager, viewModelProvider);
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

        SelectableRecyclerViewAdapter<Type> typeAdapter = new SelectableRecyclerViewAdapter<>(new ArrayList<>());
        typeViewModel.getTypes().observe(this, typeAdapter::setValues);
        SelectDialogFragment<Type> typeSelectDialog = new SelectDialogFragment<>(typeAdapter);
        typeSelectDialog.getSelectedElement().observe(this, type -> this.mTypeView.setText(type.getName()));
        this.mTypeView = view.findViewById(R.id.type);
        this.mTypeView.setOnClickListener(v -> typeSelectDialog.show(fragmentManager, "dialog"));

        SelectableRecyclerViewAdapter<Tag> tagAdapter = new SelectableRecyclerViewAdapter<>(new ArrayList<>());
        tagViewModel.getTags().observe(this, tagAdapter::setValues);
        SelectDialogFragment<Tag> tagSelectDialog = new SelectDialogFragment<>(tagAdapter);
        tagSelectDialog.getSelectedElement().observe(this, tag -> this.mTagView.setText(tag.getName()));
        this.mTagView = view.findViewById(R.id.tag);
        this.mTagView.setOnClickListener(v -> tagSelectDialog.show(fragmentManager, "dialog"));


        this.mValueView = view.findViewById(R.id.value);
        return view;
    }



    public DataDto getDto() throws ModelValidationException {

        DataDto dataDto = new DataDto(0, 0, 0, new Date(), Integer.parseInt(this.mValueView.getText().toString()));
        dataDto.validate();
        return dataDto;
    }
}