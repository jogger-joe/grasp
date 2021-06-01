package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;
import net.usemyskills.grasp.repository.TagRepository;

import java.util.List;

public class TagViewModel extends AndroidViewModel {
    private final TagRepository tagRepository;
    private final MutableLiveData<RecordGroup> recordGroup;

    private final MutableLiveData<List<Tag>> tags;
    private final MutableLiveData<List<Type>> types;

    public TagViewModel(Application application) {
        super(application);
        this.tagRepository = new TagRepository(application);
        this.recordGroup = new MutableLiveData<>();
        this.tags = new MutableLiveData<>();
        this.types = new MutableLiveData<>();
    }

    public void initObserver(LifecycleOwner owner) {
        this.recordGroup.observe(owner, recordGroup -> {
            this.tagRepository.getTagsByGroupId(recordGroup.groupId).observe(owner, this.tags::postValue);
            this.tagRepository.getTypesByGroupId(recordGroup.groupId).observe(owner, this.types::postValue);
        });
    }

    public void setRecordGroup(RecordGroup recordGroup) {
        this.recordGroup.postValue(recordGroup);
    }

    public MutableLiveData<List<Tag>> getTags() {
        return tags;
    }

    public MutableLiveData<List<Type>> getTypes() {
        return types;
    }

    public void save(Tag tag) {
        if (tag.tagId == 0) {
            this.tagRepository.insert(tag);
        } else {
            this.tagRepository.update(tag);
        }
    }

    public void save(Type type) {
        if (type.tagId == 0) {
            this.tagRepository.insert(type);
        } else {
            this.tagRepository.update(type);
        }
    }

    @Override
    protected void onCleared() {
        // @todo: remove observers
        super.onCleared();
    }
}

