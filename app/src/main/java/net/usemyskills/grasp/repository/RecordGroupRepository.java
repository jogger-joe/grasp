package net.usemyskills.grasp.repository;

import android.app.Application;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.BaseDao;
import net.usemyskills.grasp.persistence.entity.RecordGroup;

public class RecordGroupRepository extends BaseRepository<RecordGroup>{
    public RecordGroupRepository(Application application) {
        super(application);
    }

    @Override
    protected BaseDao<RecordGroup> getDao(AppDatabase db) {
        return db.getRecordGroupDao();
    }
}
