package net.usemyskills.grasp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.TypeDao;
import net.usemyskills.grasp.persistence.entity.Type;

import java.util.List;

public class TypeRepository {
     protected final TypeDao dao;
    protected final LiveData<List<Type>> liveElements;

    public TypeRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dao = db.getTypeDao();
        this.liveElements = dao.getAll();
    }

    public LiveData<List<Type>> getAll() { return this.liveElements; }
    public long insert(Type element) { return this.dao.insert(element); }
}
