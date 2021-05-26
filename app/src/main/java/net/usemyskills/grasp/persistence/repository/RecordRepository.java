package net.usemyskills.grasp.persistence.repository;

import android.app.Application;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.BaseDao;
import net.usemyskills.grasp.persistence.entity.Record;

public class RecordRepository extends BaseRepository<Record>{
    public RecordRepository(Application application) {
        super(application);
    }

    @Override
    protected BaseDao<Record> getDao(AppDatabase db) {
        return db.getRecordDao();
    }
}
