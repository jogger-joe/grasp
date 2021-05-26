package net.usemyskills.grasp.repository;

import android.app.Application;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.BaseDao;
import net.usemyskills.grasp.persistence.entity.Tag;

public class TagRepository extends BaseRepository<Tag> {
    public TagRepository(Application application) {
        super(application);
    }

    @Override
    protected BaseDao<Tag> getDao(AppDatabase db) {
        return db.getTagDao();
    }
}
