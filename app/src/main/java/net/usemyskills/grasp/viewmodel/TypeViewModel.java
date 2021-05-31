package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import net.usemyskills.grasp.persistence.entity.Type;
import net.usemyskills.grasp.repository.TypeRepository;

public class TypeViewModel extends BaseViewModel<Type> {
    public TypeViewModel(Application application) {
        super(application, new TypeRepository(application));
    }

    public void loadRecordsByGroup(long groupId) {
        if (this.repository!= null) {
            ((TypeRepository)this.repository).getAllOfGroup(groupId).observe(this.owner, types -> this.entities.postValue(types));
        }
    }
}

