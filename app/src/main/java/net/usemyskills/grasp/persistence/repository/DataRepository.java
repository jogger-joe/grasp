package net.usemyskills.grasp.persistence.repository;

import android.app.Application;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.DataDao;
import net.usemyskills.grasp.persistence.entity.Data;

public class DataRepository {
    private final DataDao dataDao;

    public DataRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dataDao = db.getDataDao();
    }

    public void insert(Data data) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            this.dataDao.insert(data);
        });
    }

}
