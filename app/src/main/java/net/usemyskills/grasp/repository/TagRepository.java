package net.usemyskills.grasp.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.model.TagDto;
import net.usemyskills.grasp.model.TypeDto;
import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.TagDao;
import net.usemyskills.grasp.persistence.dao.TypeDao;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;

import java.util.List;

public class TagRepository {
    protected final TagDao tagDao;
    protected final TypeDao typeDao;

    protected final MutableLiveData<List<TypeDto>> typeElements;
    protected final MutableLiveData<List<TagDto>> tagElements;

    public TagRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.tagDao = db.getTagDao();
        this.typeDao = db.getTypeDao();
        this.typeElements = new MutableLiveData<>();
        this.tagElements = new MutableLiveData<>();
    }

    public MutableLiveData<List<TypeDto>> getTypeElements() {
        return typeElements;
    }

    public MutableLiveData<List<TagDto>> getTagElements() {
        return tagElements;
    }

    public void getAllTags() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            List<Tag> tags = this.tagDao.getAll();
            this.tagElements.postValue(TagMapper.toDto(tags));
        });
    }

    public void getAllTypes() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            List<Type> types = this.typeDao.getAll();
            this.typeElements.postValue(TypeMapper.toDto(types));
        });
    }

    public void getTagsByGroupId(long groupId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            List<Tag> tags = this.tagDao.findByGroup(groupId);
            this.tagElements.postValue(TagMapper.toDto(tags));
        });
    }

    public void getTypesByGroupId(long groupId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            List<Type> types = this.typeDao.findByGroup(groupId);
            this.typeElements.postValue(TypeMapper.toDto(types));
        });
    }

    public void insert(TagDto element) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            this.tagDao.insert(TagMapper.toEntity(element));
        });
    }

    public void insert(TypeDto element) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            this.typeDao.insert(TypeMapper.toEntity(element));
        });
    }

    public void update(TagDto element) {
        this.tagDao.update(TagMapper.toEntity(element));
    }

    public void update(TypeDto element) {
        this.typeDao.update(TypeMapper.toEntity(element));
    }

    public void delete(TagDto element) {
        this.tagDao.delete(TagMapper.toEntity(element));
    }

    public void delete(TypeDto element) {
        this.typeDao.delete(TypeMapper.toEntity(element));
    }
}
