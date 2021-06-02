package net.usemyskills.grasp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.repository.RecordRepository;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {
    private final RecordRepository recordRepository;

    private final MutableLiveData<List<RecordWithTypeAndTags>> records;
    private final MutableLiveData<RecordWithTypeAndTags> editElement;

    public RecordViewModel(Application application) {
        super(application);
        this.recordRepository = new RecordRepository(application);
        this.records = new MutableLiveData<>();
        this.editElement = new MutableLiveData<>();
    }

    public void setRecordGroup(RecordGroup recordGroup, LifecycleOwner owner) {
        Log.d("GRASP_LOG", "RecordViewModel.setRecordGroup: " + recordGroup.toString());
        this.recordRepository.getAllByGroupId(recordGroup.groupId).observe(owner, this.records::postValue);
    }

    public MutableLiveData<List<RecordWithTypeAndTags>> getRecords() {
        return records;
    }

    public void save(RecordWithTypeAndTags recordWithTypeAndTags) {
        if (recordWithTypeAndTags.record.recordId == 0) {
            this.recordRepository.insert(recordWithTypeAndTags);
        } else {
            this.recordRepository.update(recordWithTypeAndTags);
        }
    }

    public MutableLiveData<RecordWithTypeAndTags> getEditElement() {
        return editElement;
    }

    public void setEditElement(RecordWithTypeAndTags editElement) {
        Log.d("GRASP_LOG", "RecordViewModel.setEditElement: " + editElement.toString());
        this.editElement.postValue(editElement);
    }
}

