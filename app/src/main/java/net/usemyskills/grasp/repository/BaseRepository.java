package net.usemyskills.grasp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.BaseDao;
import net.usemyskills.grasp.persistence.entity.BaseEntity;

import java.util.List;

public abstract class BaseRepository<T extends BaseEntity> {
    protected final BaseDao<T> dao;
    protected final LiveData<List<T>> liveElements;

    public BaseRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dao = this.getDao(db);
        this.liveElements = dao.getAll();
    }

    protected abstract BaseDao<T> getDao(AppDatabase db);

    public LiveData<List<T>> getAll() { return this.liveElements; }
    public long insert(T element) { return this.dao.insert(element); }

}
