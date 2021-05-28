package net.usemyskills.grasp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.repository.CrudRepositoryInterface;

import java.util.List;

public class BaseViewModel<T> extends AndroidViewModel {

    protected final CrudRepositoryInterface<T> repository;
    protected LiveData<List<T>> entities;
    private final MutableLiveData<T> selectedEntity;

    public BaseViewModel(Application application, CrudRepositoryInterface<T> repository) {
        super(application);
        this.repository = repository;
        this.selectedEntity = new MutableLiveData<>();
        Log.d("GRASP_LOG",this.getClass().toString() + " created with repository " + repository.getClass().toString());
    }

    public void insert(T data) {
        this.repository.insert(data);
    }

    public LiveData<List<T>> getEntities() {
        if (this.entities == null) {
            Log.d("GRASP_LOG","entities empty, initializing");
            this.entities = this.repository.getAll();
        }
        return this.entities;
    }

    public MutableLiveData<T> getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(T selectedEntity) {
        Log.d("GRASP_LOG","setSelectedEntity of type " + selectedEntity.getClass().toString());
        this.selectedEntity.setValue(selectedEntity);
    }
}

