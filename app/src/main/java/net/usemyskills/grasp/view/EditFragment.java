//package net.usemyskills.grasp.view;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.lifecycle.ViewModelProvider;
//
//import net.usemyskills.grasp.R;
//import net.usemyskills.grasp.adapter.BaseRecyclerViewAdapter;
//import net.usemyskills.grasp.exceptions.ModelValidationException;
//import net.usemyskills.grasp.model.DataDto;
//import net.usemyskills.grasp.persistence.entity.Tag;
//import net.usemyskills.grasp.persistence.entity.Type;
//import net.usemyskills.grasp.viewmodel.TagViewModel;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Locale;
//
//public class EditFragment extends Fragment {
//
//    private TextView mDateView;
//    private TextView mTypeView;
//    private TextView mTagView;
//    private EditText mValueView;
//
//    private final FragmentManager fragmentManager;
//    private final TagViewModel tagViewModel;
//
//    private Type selectedType;
//    private Tag selectedTag;
//    private Date selectedDate;
//
//    public EditFragment(FragmentManager fragmentManager, ViewModelProvider viewModelProvider) {
//
//        this.fragmentManager = fragmentManager;
//        this.tagViewModel = viewModelProvider.get(TagViewModel.class);
//    }
//
//    public static EditFragment newInstance(FragmentManager fragmentManager, ViewModelProvider viewModelProvider) {
//        EditFragment fragment = new EditFragment(fragmentManager, viewModelProvider);
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_edit, container, false);
//        // init mdateview
//        this.mDateView = view.findViewById(R.id.date);
//        DateDialogFragment dateDialogFragment = new DateDialogFragment();
//        dateDialogFragment.getSelectedDate().observe(this, this::updateDate);
//        this.mDateView.setOnClickListener(v -> dateDialogFragment.show(fragmentManager, "dialog"));
//
//        BaseRecyclerViewAdapter<Type> typeAdapter = new BaseRecyclerViewAdapter<>(new ArrayList<>());
//        typeViewModel.getTypes().observe(this, typeAdapter::setValues);
//        SelectDialogFragment<Type> typeSelectDialog = new SelectDialogFragment<>(typeAdapter);
//        typeSelectDialog.getSelectedElement().observe(this, this::updateType);
//        this.mTypeView = view.findViewById(R.id.type);
//        this.mTypeView.setOnClickListener(v -> typeSelectDialog.show(fragmentManager, "dialog"));
//
//        BaseRecyclerViewAdapter<Tag> tagAdapter = new BaseRecyclerViewAdapter<>(new ArrayList<>());
//        tagViewModel.getTags().observe(this, tagAdapter::setValues);
//        SelectDialogFragment<Tag> tagSelectDialog = new SelectDialogFragment<>(tagAdapter);
//        tagSelectDialog.getSelectedElement().observe(this, this::updateTag);
//        this.mTagView = view.findViewById(R.id.tag);
//        this.mTagView.setOnClickListener(v -> tagSelectDialog.show(fragmentManager, "dialog"));
//
//
//        this.mValueView = view.findViewById(R.id.value);
//        return view;
//    }
//
//    private void updateType(Type type) {
//        this.selectedType = type;
//        this.mTypeView.setText(type.getName());
//    }
//
//    private void updateTag(Tag tag) {
//        this.selectedTag = tag;
//        this.mTagView.setText(tag.getName());
//    }
//
//    private void updateDate(Date date) {
//        this.selectedDate = date;
//        this.mDateView.setText(new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY).format(date));
//    }
//
//
//    public DataDto getDto() throws ModelValidationException {
////        int typeId = this.selectedType != null ? selectedType.getId() : 0;
////        int tagId = this.selectedTag != null ? selectedTag.getId() : 0;
////        Date date = this.selectedDate != null ? this.selectedDate : new Date();
////        DataDto dataDto = new DataDto(0, typeId, tagId, date, Integer.parseInt(this.mValueView.getText().toString()));
////        dataDto.validate();
////        return dataDto;
//        return null;
//    }
//}