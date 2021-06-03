package net.usemyskills.grasp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.model.RecordGroupDto;
import net.usemyskills.grasp.repository.RecordGroupRepository;

import java.util.List;

public class RecordGroupViewModel extends AndroidViewModel {

    private final RecordGroupRepository recordGroupRepository;
    private final LiveData<List<RecordGroupDto>> recordGroups;
    private final MutableLiveData<RecordGroupDto> editElement;

    public RecordGroupViewModel(Application application) {
        super(application);
        Log.d("GRASP_LOG", "RecordGroupViewModel");
        this.recordGroupRepository = new RecordGroupRepository(application);
        this.recordGroups = this.recordGroupRepository.getElements();
        this.recordGroupRepository.getAll();
        this.editElement = new MutableLiveData<>();
    }

    //new RecordGroup("create new", R.drawable.ic_add)

    public LiveData<List<RecordGroupDto>> getRecordGroups() {
        Log.d("GRASP_LOG", "RecordGroupViewModel.getRecordGroups");
        return this.recordGroups;
    }

    public void save(RecordGroupDto recordGroup) {
        if (recordGroup.id == 0) {
            this.recordGroupRepository.insert(recordGroup);
        } else {
            this.recordGroupRepository.update(recordGroup);
        }
    }

    public MutableLiveData<RecordGroupDto> getEditElement() {
        return editElement;
    }

    public void setEditElement(RecordGroupDto editElement) {
        this.editElement.postValue(editElement);
    }
}

