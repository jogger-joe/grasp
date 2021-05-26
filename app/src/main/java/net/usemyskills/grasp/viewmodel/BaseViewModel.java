package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.persistence.entity.BaseEntity;
import net.usemyskills.grasp.repository.BaseRepository;

import java.util.List;

public abstract class BaseViewModel<T extends BaseEntity> extends AndroidViewModel {

    private final BaseRepository<T> repository;
    private LiveData<List<T>> entities;
    private final MutableLiveData<T> selectedEntity;

    public BaseViewModel(Application application) {
        super(application);
        this.repository = this.getRepository(application);
        this.selectedEntity = new MutableLiveData<>();
    }

    protected abstract BaseRepository<T> getRepository(Application application);

    public void insert(T data) {
        this.repository.insert(data);
    }

    public LiveData<List<T>> getEntities() {
        if (this.entities == null) {
            this.entities = this.repository.getAll();
        }
        return this.entities;
    }

    public MutableLiveData<T> getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(T selectedEntity) {
        this.selectedEntity.setValue(selectedEntity);
    }
}

