package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.repository.RecordGroupRepository;

import java.util.List;

public class RecordGroupViewModel extends AndroidViewModel {

    private final RecordGroupRepository recordGroupRepository;
    private final MutableLiveData<List<RecordGroup>> recordGroups;
    private RecordGroup editElement;

    public RecordGroupViewModel(Application application) {
        super(application);
        this.recordGroupRepository = new RecordGroupRepository(application);
        this.recordGroups = new MutableLiveData<>();
    }

    public void initObserver(LifecycleOwner owner) {
        this.recordGroupRepository.getAll().observe(owner, recordGroups -> {
            recordGroups.add(new RecordGroup("create new", R.drawable.ic_add));
            this.recordGroups.postValue(recordGroups);
        });
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

    public RecordGroup getEditElement() {
        return editElement;
    }

    public void setEditElement(RecordGroup editElement) {
        this.editElement = editElement;
    }

    @Override
    protected void onCleared() {
        // @todo: remove observers
        super.onCleared();
    }
}

