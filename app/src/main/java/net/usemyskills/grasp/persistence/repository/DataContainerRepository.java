package net.usemyskills.grasp.persistence.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.DataContainerDao;
import net.usemyskills.grasp.persistence.dao.DataDao;
import net.usemyskills.grasp.persistence.entity.Data;
import net.usemyskills.grasp.persistence.entity.DataContainer;

import java.util.List;

public class DataContainerRepository {
    private final DataDao dataDao;
    private final LiveData<List<DataContainer>> liveData;

    public DataContainerRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        DataContainerDao dataContainerDao = db.getDataContainerDao();
        this.dataDao = db.getDataDao();
        this.liveData = dataContainerDao.getAll();
    }

    public LiveData<List<DataContainer>> getAll() {
        return this.liveData;
    }

    public void insert(DataContainer dataContainer) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            this.dataDao.insert(dataContainer.getData());
        });
    }

    public void insert(Data data) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            this.dataDao.insert(data);
        });
    }

}
