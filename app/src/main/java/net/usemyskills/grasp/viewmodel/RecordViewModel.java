package net.usemyskills.grasp.viewmodel;

import android.app.Application;

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
    private RecordGroupDto recordGroup;

    public RecordViewModel(Application application) {
        super(application);
        this.recordRepository = new RecordRepository(application);
        this.records = this.recordRepository.getElements();
        this.editElement = new MutableLiveData<>();
    }

    public void setRecordGroup(RecordGroupDto recordGroup) {
        this.recordGroup = recordGroup;
        this.reloadRecords();
    }

    public void reloadRecords() {
        if (this.recordGroup != null) {
            this.recordRepository.getAllByGroupId(this.recordGroup.id);
        } else {
            this.recordRepository.getAll();
        }
    }

    public LiveData<List<RecordDto>> getRecords() {
        return this.records;
    }

    public void save(RecordDto record) {
        record.groupId = this.recordGroup != null ? this.recordGroup.id : 0;
        this.recordRepository.insert(record);
        this.reloadRecords();
    }

    public MutableLiveData<RecordDto> getEditElement() {
        return editElement;
    }

    public void setEditElement(RecordDto editElement) {
        this.editElement.postValue(editElement);
    }
}

