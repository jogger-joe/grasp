package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.entity.Type;
import net.usemyskills.grasp.persistence.repository.TypeRepository;

import java.util.List;

public class TypeViewModel extends AndroidViewModel {

    private final TypeRepository typeRepository;

    private final LiveData<List<Type>> types;

    public TypeViewModel(Application application) {
        super(application);
        this.typeRepository = new TypeRepository(application);
        this.types = this.typeRepository.getAll();
    }

    public LiveData<List<Type>> getTypes() {
        return types;
    }

    public long insertType(Type type) {
        return this.typeRepository.insert(type);
    }
}

