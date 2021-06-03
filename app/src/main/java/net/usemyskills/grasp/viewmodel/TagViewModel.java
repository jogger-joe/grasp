package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.model.RecordGroupDto;
import net.usemyskills.grasp.model.TagDto;
import net.usemyskills.grasp.model.TypeDto;
import net.usemyskills.grasp.repository.TagRepository;

import java.util.List;

public class TagViewModel extends AndroidViewModel {
    private final TagRepository tagRepository;

    private final LiveData<List<TagDto>> tags;
    private final LiveData<List<TypeDto>> types;

    public TagViewModel(Application application) {
        super(application);
        this.tagRepository = new TagRepository(application);
        this.tags = this.tagRepository.getTagElements();
        this.types = this.tagRepository.getTypeElements();
    }

    public void setRecordGroup(RecordGroupDto recordGroup) {
        this.tagRepository.getTagsByGroupId(recordGroup.id);
        this.tagRepository.getTypesByGroupId(recordGroup.id);
    }

    public LiveData<List<TagDto>> getTags() {
        return tags;
    }

    public LiveData<List<TypeDto>> getTypes() {
        return types;
    }

    public void save(TagDto tag) {
        if (tag.id == 0) {
            this.tagRepository.insert(tag);
        } else {
            this.tagRepository.update(tag);
        }
    }

    public void save(TypeDto type) {
        if (type.id == 0) {
            this.tagRepository.insert(type);
        } else {
            this.tagRepository.update(type);
        }
    }
}

