package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.repository.RecordRepository;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {
    private final RecordRepository recordRepository;

    private final MutableLiveData<RecordGroup> recordGroup;
    private final MutableLiveData<List<RecordWithTypeAndTags>> records;

    public RecordViewModel(Application application) {
        super(application);
        this.recordRepository = new RecordRepository(application);
        this.recordGroup = new MutableLiveData<>();
        this.records = new MutableLiveData<>();
        // add trigger for setting recordGroup
        this.recordGroup.observeForever(recordGroup -> {
            this.recordRepository.getAllByGroupId(recordGroup.groupId).observeForever(this.records::postValue);
        });
    }

    public void setRecordGroup(RecordGroup recordGroup) {
        this.recordGroup.postValue(recordGroup);
    }

    public MutableLiveData<List<RecordWithTypeAndTags>> getTags() {
        return records;
    }

    public void save(RecordWithTypeAndTags recordWithTypeAndTags) {
        if (recordWithTypeAndTags.record.recordId == 0) {
            this.recordRepository.insert(recordWithTypeAndTags);
        } else {
            this.recordRepository.update(recordWithTypeAndTags);
        }
    }

    @Override
    protected void onCleared() {
        // @todo: remove observers
        super.onCleared();
    }
}

