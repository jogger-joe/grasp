package net.usemyskills.grasp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.RecordGroupDao;
import net.usemyskills.grasp.persistence.entity.RecordGroup;

import java.util.List;

public class RecordGroupRepository implements CrudRepositoryInterface<RecordGroup> {
    protected final RecordGroupDao dao;
    protected final LiveData<List<RecordGroup>> liveElements;

    public RecordGroupRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dao = db.getRecordGroupDao();
        this.liveElements = dao.getAll();
    }

    @Override
    public LiveData<List<RecordGroup>> getAll() {
        return this.liveElements;
    }

    @Override
    public long insert(RecordGroup element) {
        return this.dao.insert(element);
    }

    @Override
    public void update(RecordGroup element) {
        this.dao.update(element);
    }

    @Override
    public void delete(RecordGroup element) {
        this.dao.delete(element);
    }
}
