package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.model.RecordGroupDto;
import net.usemyskills.grasp.model.TagDto;
import net.usemyskills.grasp.model.TypeDto;
import net.usemyskills.grasp.repository.TagRepository;

import java.util.List;

public class TagViewModel extends AndroidViewModel {
    private final TagRepository tagRepository;
    private final LiveData<List<TagDto>> tags;
    private final LiveData<List<TypeDto>> types;
    private final MutableLiveData<TagDto> editTagElement;
    private final MutableLiveData<TypeDto> editTypeElement;
    private RecordGroupDto recordGroup;

    public TagViewModel(Application application) {
        super(application);
        this.tagRepository = new TagRepository(application);
        this.tags = this.tagRepository.getTagElements();
        this.types = this.tagRepository.getTypeElements();
        this.editTagElement = new MutableLiveData<>();
        this.editTypeElement = new MutableLiveData<>();
    }

    public void setRecordGroup(RecordGroupDto recordGroup) {
        this.recordGroup = recordGroup;
        this.reloadTags();
    }

    public void reloadTags() {
        if (this.recordGroup != null) {
            long recordGroupId = this.recordGroup.id;
            this.tagRepository.getTagsByGroupId(recordGroupId);
            this.tagRepository.getTypesByGroupId(recordGroupId);
        } else {
            this.tagRepository.getAllTags();
            this.tagRepository.getAllTypes();
        }
    }

    public LiveData<List<TagDto>> getTags() {
        return tags;
    }

    public LiveData<List<TypeDto>> getTypes() {
        return types;
    }

    public void save(TagDto tag) {
        tag.groupId = this.recordGroup != null ? this.recordGroup.id : 0;
        if (tag.id == 0) {
            this.tagRepository.insert(tag);
        } else {
            this.tagRepository.update(tag);
        }
        this.reloadTags();
    }

    public void save(TypeDto type) {
        type.groupId = this.recordGroup != null ? this.recordGroup.id : 0;
        if (type.id == 0) {
            this.tagRepository.insert(type);
        } else {
            this.tagRepository.update(type);
        }
        this.reloadTags();
    }

    public MutableLiveData<TagDto> getEditTagElement() {
        return editTagElement;
    }

    public MutableLiveData<TypeDto> getEditTypeElement() {
        return editTypeElement;
    }

    public void setEditTagElement(TagDto editElement) {
        this.editTagElement.postValue(editElement);
    }

    public void setEditTypeElement(TypeDto editElement) {
        this.editTypeElement.postValue(editElement);
    }
}

