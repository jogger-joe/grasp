package net.usemyskills.grasp.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.model.RecordDto;
import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.RecordDao;
import net.usemyskills.grasp.persistence.dao.RecordTagsReferenceDao;
import net.usemyskills.grasp.persistence.dao.TagDao;
import net.usemyskills.grasp.persistence.entity.RecordTagsReference;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.List;

public class RecordRepository {
    protected final RecordDao recordDao;
    protected final TagDao tagDao;
    protected final RecordTagsReferenceDao recordTagsReferenceDao;

    protected MutableLiveData<List<RecordDto>> elements;

    public RecordRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.recordDao = db.getRecordDao();
        this.tagDao = db.getTagDao();
        this.recordTagsReferenceDao = db.getRecordTagsReferenceDao();
        this.elements = new MutableLiveData<>();
    }

    public MutableLiveData<List<RecordDto>> getElements() {
        return elements;
    }

    public void getAll() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            List<RecordWithTypeAndTags> records = this.recordDao.getAll();
            this.elements.postValue(RecordMapper.toDto(records));
        });
    }

    public void getAllByGroupId(long groupId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            List<RecordWithTypeAndTags> records = this.recordDao.getByGroupId(groupId);
            this.elements.postValue(RecordMapper.toDto(records));
        });
    }

    public void insert(RecordDto elementDto) {
        Log.d("RecordRepository.insert", elementDto.toString() + " " + elementDto.value );
        AppDatabase.databaseWriteExecutor.execute(() -> {
            RecordWithTypeAndTags element = RecordMapper.toEntity(elementDto);
            this.tagDao.insert(element.type);
            this.recordDao.insert(element.record);
            for (Tag tag: element.tags) {
                this.tagDao.insert(tag);
                this.recordTagsReferenceDao.insert(new RecordTagsReference(element.record.recordId, tag.tagId));
            }
        });
    }

    public void delete(RecordDto elementDto) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            RecordWithTypeAndTags element = RecordMapper.toEntity(elementDto);
            for (Tag tag : element.tags) {
                this.recordTagsReferenceDao.delete(new RecordTagsReference(element.record.recordId, tag.tagId));
            }
            this.recordDao.delete(element.record);
        });
    }
}
