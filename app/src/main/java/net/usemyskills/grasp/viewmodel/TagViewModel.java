package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;
import net.usemyskills.grasp.persistence.repository.TagRepository;

import java.util.List;

public class TagViewModel extends AndroidViewModel {

    private final TagRepository tagRepository;

    private final LiveData<List<Tag>> tags;
    private final LiveData<List<Type>> types;

    public TagViewModel(Application application) {
        super(application);
        this.tagRepository = new TagRepository(application);
        this.tags = this.tagRepository.getAllTags();
        this.types = this.tagRepository.getAllTypes();
    }

    public LiveData<List<Tag>> getAllDataTags() { return this.tags; }
    public LiveData<List<Type>> getAllDataTypeTags() { return this.types; }
    public Tag findTagByName(String name) { return this.tagRepository.findTagByName(name);}
    public Tag findTypeByName(String name) { return this.tagRepository.findTypeByName(name);}
}

