package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.repository.TagRepository;

import java.util.List;

public class TagViewModel extends AndroidViewModel {

    private final TagRepository tagRepository;

    private final LiveData<List<Tag>> tags;

    public TagViewModel(Application application) {
        super(application);
        this.tagRepository = new TagRepository(application);
        this.tags = this.tagRepository.getAll();
    }

    public LiveData<List<Tag>> getTags() {
        return tags;
    }

    public long insertTag(Tag type) {
        return this.tagRepository.insert(type);
    }
}

