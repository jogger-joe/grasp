package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.repository.BaseRepository;
import net.usemyskills.grasp.repository.TagRepository;


public class TagViewModel extends BaseViewModel<Tag> {
    public TagViewModel(Application application) {
        super(application);
    }

    @Override
    protected BaseRepository<Tag> getRepository(Application application) {
        return new TagRepository(application);
    }
}

