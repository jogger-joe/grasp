package net.usemyskills.grasp.persistence.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.TypeDao;
import net.usemyskills.grasp.persistence.entity.Type;

import java.util.List;

public class TypeRepository {
    private final TypeDao typeDao;
    private final LiveData<List<Type>> liveTypes;

    public TypeRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.typeDao = db.getTypeDao();
        this.liveTypes = typeDao.getAll();
    }

    public LiveData<List<Type>> getAll() { return this.liveTypes; }
    public Type findByName(String name) { return this.typeDao.findByName(name); }
    public long insert(Type type) { return this.typeDao.insert(type); }

}
