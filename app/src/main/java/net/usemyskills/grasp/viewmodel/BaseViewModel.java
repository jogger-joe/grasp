package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.repository.CrudRepositoryInterface;

import java.util.List;

public class BaseViewModel<T> extends AndroidViewModel {

    protected final CrudRepositoryInterface<T> repository;
    private LiveData<List<T>> entities;
    private final MutableLiveData<T> selectedEntity;

    public BaseViewModel(Application application, CrudRepositoryInterface<T> repository) {
        super(application);
        this.repository = repository;
        this.selectedEntity = new MutableLiveData<>();
    }

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

