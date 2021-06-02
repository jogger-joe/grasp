package net.usemyskills.grasp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.repository.RecordGroupRepository;

import java.util.List;

public class RecordGroupViewModel extends AndroidViewModel {

    private final RecordGroupRepository recordGroupRepository;
    private final MutableLiveData<List<RecordGroup>> recordGroups;
    private final MutableLiveData<RecordGroup> editElement;

    public RecordGroupViewModel(Application application) {
        super(application);
        Log.d("GRASP_LOG", "RecordGroupViewModel");
        this.recordGroupRepository = new RecordGroupRepository(application);
        this.recordGroups = new MutableLiveData<>();
        this.editElement = new MutableLiveData<>();
    }

    public void loadAll(LifecycleOwner owner) {
        this.recordGroupRepository.getAll().observe(owner, this.recordGroups::postValue);
    }

    //new RecordGroup("create new", R.drawable.ic_add)

    public MutableLiveData<List<RecordGroup>> getRecordGroups() {
        Log.d("GRASP_LOG", "RecordGroupViewModel.getRecordGroups");
        return this.recordGroups;
    }

    public void save(RecordGroup recordGroup) {
        if (recordGroup.tagId == 0) {
            this.recordGroupRepository.insert(recordGroup);
        } else {
            this.recordGroupRepository.update(recordGroup);
        }
    }

    public MutableLiveData<RecordGroup> getEditElement() {
        return editElement;
    }

    public void setEditElement(RecordGroup editElement) {
        this.editElement.postValue(editElement);
    }
}

