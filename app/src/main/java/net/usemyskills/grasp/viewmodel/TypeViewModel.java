package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import net.usemyskills.grasp.persistence.entity.Type;
import net.usemyskills.grasp.repository.TypeRepository;

public class TypeViewModel extends BaseViewModel<Type> {
    public TypeViewModel(Application application) {
        super(application, new TypeRepository(application));
    }
}

