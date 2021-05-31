package net.usemyskills.grasp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.repository.CrudRepositoryInterface;

import java.util.List;

public class BaseViewModel<T> extends AndroidViewModel {

    protected final CrudRepositoryInterface<T> repository;
    protected MutableLiveData<List<T>> entities;
    protected MutableLiveData<T> selectedEntity;
    protected LifecycleOwner owner;

    public BaseViewModel(Application application, CrudRepositoryInterface<T> repository) {
        super(application);
        this.repository = repository;
        Log.d("GRASP_LOG",this.getClass().toString() + " created with repository " + repository.getClass().toString());
    }

    public void loadAll() {
        this.repository.getAll().observe(this.owner, entities -> this.entities.postValue(entities));
    }

    public void setOwner(LifecycleOwner owner) {
        this.owner = owner;
    }

    public void insert(T data) {
        this.repository.insert(data);
    }

    public LiveData<List<T>> getEntities() {
        if (this.entities == null) {
            this.entities = new MutableLiveData<>();
        }
        return this.entities;
    }

    public MutableLiveData<T> getSelectedEntity() {
        if (this.selectedEntity == null) {
            this.selectedEntity = new MutableLiveData<>();
        }
        return this.selectedEntity;
    }

    public T getSelectedEntityElement() throws Exception {
        if (this.getSelectedEntity() == null || this.getSelectedEntity().getValue() == null) {
            throw new Exception("no element selected");
        }
        return this.getSelectedEntity().getValue();
    }

    public void setSelectedEntity(T selectedEntity) {
        Log.d("GRASP_LOG","setSelectedEntity of type " + selectedEntity.getClass().toString());
        this.getSelectedEntity().setValue(selectedEntity);
    }
}

