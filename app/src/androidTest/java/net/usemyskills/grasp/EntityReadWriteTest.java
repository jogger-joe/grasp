package net.usemyskills.grasp;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.DataContainerDao;
import net.usemyskills.grasp.persistence.dao.DataDao;
import net.usemyskills.grasp.persistence.dao.DataTagDao;
import net.usemyskills.grasp.persistence.dao.DataTypeDao;
import net.usemyskills.grasp.persistence.entity.Data;
import net.usemyskills.grasp.persistence.entity.DataContainer;
import net.usemyskills.grasp.persistence.entity.DataTag;
import net.usemyskills.grasp.persistence.entity.DataType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(AndroidJUnit4.class)
public class EntityReadWriteTest {
    private DataTagDao dataTagDao;
    private DataTypeDao dataTypeDao;
    private DataDao dataDao;
    private DataContainerDao dataContainerDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        dataTagDao = db.getDataTagDao();
        dataTypeDao = db.getDataTypeDao();
        dataDao = db.getDataDao();
        dataContainerDao = db.getDataContainerDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeDataTagAndReadAll() throws Exception {
        dataTagDao.insert(new DataTag(3,"test", "testdescription"));
        dataTagDao.getAll().observeForever(dataTags -> {
            assertThat(dataTags.size(), equalTo(1));
            DataTag singleDataTag = dataTags.get(0);
            assertThat(singleDataTag.getName(), equalTo("test"));
            assertThat(singleDataTag.getTagId(), equalTo(3));
            assertThat(singleDataTag.getDescription(), equalTo("testdescription"));
        });
    }

    @Test
    public void writeDataTagWithoutIdAndReadAll() throws Exception {
        dataTagDao.insert(new DataTag("test", "testdescription"));
        dataTagDao.getAll().observeForever(dataTags -> {
            assertThat(dataTags.size(), equalTo(1));
            DataTag singleDataTag = dataTags.get(0);
            assertThat(singleDataTag.getName(), equalTo("test"));
            assertThat(singleDataTag.getTagId(), equalTo(1));
            assertThat(singleDataTag.getDescription(), equalTo("testdescription"));
        });
    }

    @Test
    public void writeDataTypeTagAndReadAll() throws Exception {
        dataTypeDao.insert(new DataType(7,"test", "testdescription", 60, "min"));
        dataTypeDao.getAll().observeForever(dataTypeTags -> {
            assertThat(dataTypeTags.size(), equalTo(1));
            DataType singleDataType = dataTypeTags.get(0);
            assertThat(singleDataType.getName(), equalTo("test"));
            assertThat(singleDataType.getTagId(), equalTo(7));
            assertThat(singleDataType.getDescription(), equalTo("testdescription"));
            assertThat(singleDataType.getModifier(), equalTo(60));
            assertThat(singleDataType.getUnit(), equalTo("min"));
        });
    }

    @Test
    public void writeDataAndReadAllDataContainer() throws Exception {
        dataTypeDao.insert(new DataType(2,"Aphrodite", "", 60, "min"));
        dataTagDao.insert(new DataTag(1,"High"));
        dataDao.insert(new Data(1,2,1, new Date(2020-1900,11-1,22), 90));
        dataContainerDao.getAll().observeForever(dataContainers -> {
            assertThat(dataContainers.size(), equalTo(1));
            DataContainer singleDataContainer = dataContainers.get(0);
            assertThat(singleDataContainer.getTypeName(), equalTo("Aphrodite"));
            assertThat(singleDataContainer.getTagName(), equalTo("High"));
            assertThat(singleDataContainer.getDateString(), equalTo("22.11.2020"));
            assertThat(singleDataContainer.getValue(), equalTo(1.5));
        });

    }
}

