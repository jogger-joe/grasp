package net.usemyskills.grasp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.TypeDao;
import net.usemyskills.grasp.persistence.entity.Type;

import java.util.List;

public class TypeRepository implements CrudRepositoryInterface<Type> {
    protected final TypeDao dao;

    public TypeRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dao = db.getTypeDao();
    }

    public LiveData<List<Type>> getAll() {
        return this.dao.getAll();
    }

    public LiveData<List<Type>> getAllOfGroup(long groupId) {
        return this.dao.findByGroup(groupId);
    }

    @Override
    public LiveData<Type> insert(Type element) {
        MutableLiveData<Type> insertedElement = new MutableLiveData<>();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            long elementId = this.dao.insert(element);
            insertedElement.postValue(this.dao.findById(elementId));
        });
        return insertedElement;
    }

    @Override
    public void update(Type element) {
        this.dao.update(element);
    }

    @Override
    public void delete(Type element) {
        this.dao.delete(element);
    }
}
