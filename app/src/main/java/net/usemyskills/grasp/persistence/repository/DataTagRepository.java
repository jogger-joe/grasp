package net.usemyskills.grasp.persistence.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.DataTagDao;
import net.usemyskills.grasp.persistence.dao.DataTypeTagDao;
import net.usemyskills.grasp.persistence.entity.DataTag;
import net.usemyskills.grasp.persistence.entity.DataTypeTag;

import java.util.List;

public class DataTagRepository {
    private final DataTypeTagDao dataTypeTagDao;
    private final DataTagDao dataTagDao;
    private final List<DataTypeTag> liveDataTypeTags;
    private final List<DataTag> liveDataTags;

    public DataTagRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dataTypeTagDao = db.getDataTypeTagDao();
        this.dataTagDao = db.getDataTagDao();
        this.liveDataTypeTags = dataTypeTagDao.getAll();
        this.liveDataTags = dataTagDao.getAll();
    }

    public List<DataTag> getAllDataTags() {
        return this.liveDataTags;
    }

    public List<DataTypeTag> getAllDataTypeTags() { return this.liveDataTypeTags; }

}
