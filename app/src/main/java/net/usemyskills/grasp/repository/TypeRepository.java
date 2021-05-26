package net.usemyskills.grasp.repository;

import android.app.Application;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.BaseDao;
import net.usemyskills.grasp.persistence.entity.Type;

public class TypeRepository extends BaseRepository<Type>{
    public TypeRepository(Application application) {
        super(application);
    }

    @Override
    protected BaseDao<Type> getDao(AppDatabase db) {
        return db.getTypeDao();
    }
}
