package net.usemyskills.grasp;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.DataContainerDao;
import net.usemyskills.grasp.persistence.dao.DataDao;
import net.usemyskills.grasp.persistence.dao.DataTagDao;
import net.usemyskills.grasp.persistence.dao.DataTypeTagDao;
import net.usemyskills.grasp.persistence.entity.Data;
import net.usemyskills.grasp.persistence.entity.DataContainer;
import net.usemyskills.grasp.persistence.entity.DataTag;
import net.usemyskills.grasp.persistence.entity.DataTypeTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(AndroidJUnit4.class)
public class EntityReadWriteTest {
    private DataTagDao dataTagDao;
    private DataTypeTagDao dataTypeTagDao;
    private DataDao dataDao;
    private DataContainerDao dataContainerDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        dataTagDao = db.getDataTagDao();
        dataTypeTagDao = db.getDataTypeTagDao();
        dataDao = db.getDataDao();
        dataContainerDao = db.getDataContainerDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeDataTagAndReadInList() throws Exception {
        DataTag dataTag = new DataTag(1,"test");
        dataTagDao.insertAll(dataTag);
        List<DataTag> allDataTags = dataTagDao.getAll();
        assertThat(allDataTags.size(), equalTo(1));
    }

    @Test
    public void writeDataTypeTagAndReadInList() throws Exception {
        DataTypeTag dataTypeTag = new DataTypeTag(2,"test");
        dataTypeTagDao.insertAll(dataTypeTag);
        List<DataTypeTag> allDataTypeTags = dataTypeTagDao.getAll();
        assertThat(allDataTypeTags.size(), equalTo(1));
    }

    @Test
    public void writeDataContainerAndReadInList() throws Exception {
        dataTagDao.insertAll(new DataTag(1,"test"));
        dataTypeTagDao.insertAll(new DataTypeTag(2,"test"));
        dataDao.insertAll(new Data(1,2,1, new Date(), 123));
        List<DataContainer> allDataContainers = dataContainerDao.getAll();
        assertThat(allDataContainers.size(), equalTo(1));
    }
}

