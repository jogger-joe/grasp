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

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public DataContainerRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        DataContainerDao dataContainerDao = db.getDataContainerDao();
        this.dataDao = db.getDataDao();
        this.liveData = dataContainerDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<DataContainer>> getAll() {
        return this.liveData;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(DataContainer dataContainer) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            this.dataDao.insert(dataContainer.getData());
        });
    }
    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Data data) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            this.dataDao.insert(data);
        });
    }

}
