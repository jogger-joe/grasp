package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.repository.RecordGroupRepository;

public class RecordGroupViewModel extends BaseViewModel<RecordGroup> {
    public RecordGroupViewModel(Application application) {
        super(application, new RecordGroupRepository(application));
    }
}

