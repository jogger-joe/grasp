package net.usemyskills.grasp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.TagDao;
import net.usemyskills.grasp.persistence.dao.TypeDao;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;

import java.util.List;


public class TagRepository {
    protected final TagDao tagDao;
    protected final TypeDao typeDao;

    public TagRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.tagDao = db.getTagDao();
        this.typeDao = db.getTypeDao();
    }

    public LiveData<List<Tag>> getAllTags() {
        return this.tagDao.getAll();
    }

    public LiveData<List<Type>> getAllTypes() {
        return this.typeDao.getAll();
    }

    public LiveData<List<Tag>> getTagsByGroupId(long groupId) {
        return this.tagDao.findByGroup(groupId);
    }

    public LiveData<List<Type>> getTypesByGroupId(long groupId) {
        return this.typeDao.findByGroup(groupId);
    }

    public void insert(Tag element) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            this.tagDao.insert(element);
        });
    }

    public void insert(Type element) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            this.typeDao.insert(element);
        });
    }

    public void update(Tag element) {
        this.tagDao.update(element);
    }

    public void update(Type element) {
        this.typeDao.update(element);
    }

    public void delete(Tag element) {
        this.tagDao.delete(element);
    }

    public void delete(Type element) {
        this.typeDao.delete(element);
    }
}
