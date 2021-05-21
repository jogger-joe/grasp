package net.usemyskills.grasp.persistence.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.TagDao;
import net.usemyskills.grasp.persistence.dao.TypeDao;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;

import java.util.List;

public class TagRepository {
    private final TypeDao typeDao;
    private final TagDao tagDao;
    private final LiveData<List<Type>> liveDataTypeTags;
    private final LiveData<List<Tag>> liveDataTags;

    public TagRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.typeDao = db.getTypeDao();
        this.tagDao = db.getTagDao();
        this.liveDataTypeTags = typeDao.getAll();
        this.liveDataTags = tagDao.getAll();
    }

    public LiveData<List<Tag>> getAllTags() { return this.liveDataTags; }
    public LiveData<List<Type>> getAllTypes() { return this.liveDataTypeTags; }
    public Tag findTagByName(String name) { return this.tagDao.findByName(name); }
    public Type findTypeByName(String name) { return this.typeDao.findByName(name); }
    public long insertTag(Tag tag) { return this.tagDao.insert(tag); }
    public long insertType(Type type) { return this.typeDao.insert(type); }

}
