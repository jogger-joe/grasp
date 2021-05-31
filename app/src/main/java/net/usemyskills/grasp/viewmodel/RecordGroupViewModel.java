package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.repository.RecordGroupRepository;

import java.util.List;

public class RecordGroupViewModel extends AndroidViewModel {

    private final RecordGroupRepository recordGroupRepository;

    private final MutableLiveData<List<RecordGroup>> recordGroups;

    public RecordGroupViewModel(Application application) {
        super(application);
        this.recordGroupRepository = new RecordGroupRepository(application);
        this.recordGroups = new MutableLiveData<>();
        this.recordGroupRepository.getAll().observeForever(this.recordGroups::postValue);
    }

    public MutableLiveData<List<RecordGroup>> getRecordGroups() {
        return recordGroups;
    }

    public void save(RecordGroup recordGroup) {
        if (recordGroup.tagId == 0) {
            this.recordGroupRepository.insert(recordGroup);
        } else {
            this.recordGroupRepository.update(recordGroup);
        }
    }

    @Override
    protected void onCleared() {
        // @todo: remove observers
        super.onCleared();
    }
}

