package net.usemyskills.grasp.persistence.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.DataTagDao;
import net.usemyskills.grasp.persistence.dao.DataTypeDao;
import net.usemyskills.grasp.persistence.entity.DataTag;
import net.usemyskills.grasp.persistence.entity.DataType;

import java.util.List;

public class DataTagRepository {
    private final DataTypeDao dataTypeDao;
    private final DataTagDao dataTagDao;
    private final LiveData<List<DataType>> liveDataTypeTags;
    private final LiveData<List<DataTag>> liveDataTags;

    public DataTagRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dataTypeDao = db.getDataTypeDao();
        this.dataTagDao = db.getDataTagDao();
        this.liveDataTypeTags = dataTypeDao.getAll();
        this.liveDataTags = dataTagDao.getAll();
    }

    public LiveData<List<DataTag>> getAllDataTags() { return this.liveDataTags; }
    public LiveData<List<DataType>> getAllDataTypeTags() { return this.liveDataTypeTags; }

}
