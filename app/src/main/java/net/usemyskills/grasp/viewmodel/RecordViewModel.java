package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.repository.RecordRepository;

public class RecordViewModel extends BaseViewModel<RecordWithTypeAndTags> {
    public RecordViewModel(Application application) {
        super(application, new RecordRepository(application));
    }

    public void loadRecordsByGroup(long groupId) {
        if (groupId == 0) {
            this.entities = ((RecordRepository)this.repository).getAll();
        } else {
            this.entities = ((RecordRepository)this.repository).getAllOfGroup(groupId);
        }
    }
}

