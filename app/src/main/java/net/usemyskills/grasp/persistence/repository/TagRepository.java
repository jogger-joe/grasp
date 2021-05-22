package net.usemyskills.grasp.persistence.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.TagDao;
import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.List;

public class TagRepository {
    private final TagDao tagDao;
    private final LiveData<List<Tag>> liveTags;

    public TagRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.tagDao = db.getTagDao();
        this.liveTags = tagDao.getAll();
    }

    public LiveData<List<Tag>> getAll() { return this.liveTags; }
    public Tag findByName(String name) { return this.tagDao.findByName(name); }
    public long insert(Tag tag) { return this.tagDao.insert(tag); }

}
