package net.usemyskills.grasp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.RecordDao;
import net.usemyskills.grasp.persistence.dao.RecordTagsReferenceDao;
import net.usemyskills.grasp.persistence.entity.RecordTagsReference;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.persistence.entity.Record;
import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.List;

public class RecordRepository {
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

    public LiveData<List<RecordWithTypeAndTags>> getAllByGroupId(long groupId) {
        return this.recordDao.findByGroup(groupId);
    }

    public void insert(RecordWithTypeAndTags element) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            for (Tag tag: element.tags) {
                this.recordTagsReferenceDao.insert(new RecordTagsReference(element.record.recordId, tag.tagId));
            }
        });
    }

    public void update(RecordWithTypeAndTags element) {
        for (Tag tag: element.tags) {
            this.recordTagsReferenceDao.insert(new RecordTagsReference(element.record.recordId, tag.tagId));
        }
        this.recordDao.update(element.record);
    }

    public void delete(RecordWithTypeAndTags element) {
        for (Tag tag: element.tags) {
            this.recordTagsReferenceDao.delete(new RecordTagsReference(element.record.recordId, tag.tagId));
        }
        this.recordDao.delete(element.record);
    }

    public void insert(Record element) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            this.recordDao.insert(element);
        });
    }
}
