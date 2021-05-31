package net.usemyskills.grasp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.RecordDao;
import net.usemyskills.grasp.persistence.dao.RecordTagsReferenceDao;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.persistence.entity.RecordTagsReference;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.persistence.entity.Record;
import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.List;

public class RecordRepository implements CrudRepositoryInterface<RecordWithTypeAndTags> {
    protected final RecordDao recordDao;
    protected final RecordTagsReferenceDao recordTagsReferenceDao;

    public RecordRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.recordDao = db.getRecordDao();
        this.recordTagsReferenceDao = db.getRecordTagsReferenceDao();
    }

    public LiveData<List<RecordWithTypeAndTags>> getAll() {
        return this.recordDao.getAll();
    }

    public LiveData<List<RecordWithTypeAndTags>> getAllOfGroup(long groupId) {
        return this.recordDao.findByGroup(groupId);
    }

    @Override
    public LiveData<RecordWithTypeAndTags> insert(RecordWithTypeAndTags element) {
        MutableLiveData<RecordWithTypeAndTags> insertedElement = new MutableLiveData<>();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            for (Tag tag: element.tags) {
                this.recordTagsReferenceDao.insert(new RecordTagsReference(element.record.recordId, tag.tagId));
            }
            long elementId = this.insert(element.record);
            insertedElement.postValue(this.recordDao.findById(elementId));
        });
        return insertedElement;
    }

    @Override
    public void update(RecordWithTypeAndTags element) {
        for (Tag tag: element.tags) {
            this.recordTagsReferenceDao.insert(new RecordTagsReference(element.record.recordId, tag.tagId));
        }
        this.recordDao.update(element.record);
    }

    @Override
    public void delete(RecordWithTypeAndTags element) {
        for (Tag tag: element.tags) {
            this.recordTagsReferenceDao.delete(new RecordTagsReference(element.record.recordId, tag.tagId));
        }
        this.recordDao.delete(element.record);
    }

    public long insert(Record element) {
        return this.recordDao.insert(element);
    }
}
