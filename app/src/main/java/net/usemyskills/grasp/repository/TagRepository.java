package net.usemyskills.grasp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.TagDao;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.List;

public class TagRepository implements CrudRepositoryInterface<Tag> {
    protected final TagDao dao;
    protected final LiveData<List<Tag>> liveElements;

    public TagRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dao = db.getTagDao();
        this.liveElements = dao.getAll();
    }

    @Override
    public LiveData<List<Tag>> getAll() {
        return this.liveElements;
    }

    @Override
    public LiveData<Tag> insert(Tag element) {
        MutableLiveData<Tag> insertedElement = new MutableLiveData<>();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            long elementId = this.dao.insert(element);
            insertedElement.postValue(this.dao.findById(elementId));
        });
        return insertedElement;
    }

    @Override
    public void update(Tag element) {
        this.dao.update(element);
    }

    @Override
    public void delete(Tag element) {
        this.dao.delete(element);
    }
}
