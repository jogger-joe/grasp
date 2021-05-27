package net.usemyskills.grasp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.RecordDao;
import net.usemyskills.grasp.persistence.entity.FullRecord;
import net.usemyskills.grasp.persistence.entity.Record;

import java.util.List;

public class RecordRepository {
    protected final RecordDao dao;
    protected final LiveData<List<FullRecord>> liveElements;

    public RecordRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dao = db.getRecordDao();
        this.liveElements = dao.getAll();
    }

    public LiveData<List<FullRecord>> getAll() { return this.liveElements; }
    public long insert(Record element) { return this.dao.insert(element); }
}
