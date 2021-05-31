package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.repository.RecordRepository;
import net.usemyskills.grasp.repository.TagRepository;

public class TagViewModel extends BaseViewModel<Tag> {
    public TagViewModel(Application application) {
        super(application, new TagRepository(application));
    }

    public void loadRecordsByGroup(long groupId) {
        if (this.repository!= null) {
            ((TagRepository) this.repository).getAllOfGroup(groupId).observe(this.owner, tags -> this.entities.postValue(tags));
        }
    }
}

