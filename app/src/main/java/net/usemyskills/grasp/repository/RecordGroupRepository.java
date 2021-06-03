package net.usemyskills.grasp.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.model.RecordGroupDto;
import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.RecordGroupDao;
import net.usemyskills.grasp.persistence.entity.RecordGroup;

import java.util.List;

public class RecordGroupRepository {
    protected final RecordGroupDao dao;
    protected final MutableLiveData<List<RecordGroupDto>> elements;

    public RecordGroupRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dao = db.getRecordGroupDao();
        this.elements = new MutableLiveData<>();
    }

    public MutableLiveData<List<RecordGroupDto>> getElements() {
        return elements;
    }

    public void getAll() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            List<RecordGroup> recordGroups = this.dao.getAll();
            this.elements.postValue(RecordGroupMapper.toDto(recordGroups));
        });
    }

    public void insert(RecordGroupDto element) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            this.dao.insert(RecordGroupMapper.toEntity(element));
        });
    }

    public void update(RecordGroupDto element) {
        this.dao.update(RecordGroupMapper.toEntity(element));
    }

    public void delete(RecordGroupDto element) {
        this.dao.delete(RecordGroupMapper.toEntity(element));
    }
}
