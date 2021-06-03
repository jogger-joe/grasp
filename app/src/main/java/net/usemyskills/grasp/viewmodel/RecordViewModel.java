package net.usemyskills.grasp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.model.RecordGroupDto;
import net.usemyskills.grasp.repository.RecordRepository;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {

    private final RecordRepository recordRepository;
    private final LiveData<List<RecordDto>> records;
    private final MutableLiveData<RecordDto> editElement;

    public RecordViewModel(Application application) {
        super(application);
        this.recordRepository = new RecordRepository(application);
        this.records = this.recordRepository.getElements();
        this.recordRepository.getAll();
        this.editElement = new MutableLiveData<>();
    }

    public void setRecordGroup(RecordGroupDto recordGroup) {
        Log.d("GRASP_LOG", "RecordViewModel.setRecordGroup: " + recordGroup.toString() + recordGroup.id);
        this.recordRepository.getAllByGroupId(recordGroup.id);
    }

    public LiveData<List<RecordDto>> getRecords() {
        return this.records;
    }

    public void save(RecordDto record) {
        if (record.id == 0) {
            this.recordRepository.insert(record);
        } else {
            this.recordRepository.insert(record);
        }
    }

    public MutableLiveData<RecordDto> getEditElement() {
        return editElement;
    }

    public void setEditElement(RecordDto editElement) {
        Log.d("GRASP_LOG", "RecordViewModel.setEditElement: " + editElement.toString());
        this.editElement.postValue(editElement);
    }
}

