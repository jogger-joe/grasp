package net.usemyskills.grasp;

import android.content.Context;

import androidx.lifecycle.LiveData;
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
import java.util.Objects;

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
        DataTag dataTag = new DataTag(3,"test", "testdescription");
        dataTagDao.insertAll(dataTag);
        List<DataTag> allDataTags = dataTagDao.getAll();
        assertThat(allDataTags.size(), equalTo(1));
        DataTag singleDataTag = allDataTags.get(0);
        assertThat(singleDataTag.getName(), equalTo("test"));
        assertThat(singleDataTag.getTagId(), equalTo(3));
        assertThat(singleDataTag.getDescription(), equalTo("testdescription"));
    }

    @Test
    public void writeDataTagWithoutIdAndReadInList() throws Exception {
        DataTag dataTag = new DataTag("test", "testdescription");
        dataTagDao.insertAll(dataTag);
        List<DataTag> allDataTags = dataTagDao.getAll();
        assertThat(allDataTags.size(), equalTo(1));
        DataTag singleDataTag = allDataTags.get(0);
        assertThat(singleDataTag.getName(), equalTo("test"));
        assertThat(singleDataTag.getTagId(), equalTo(1));
        assertThat(singleDataTag.getDescription(), equalTo("testdescription"));
    }

    @Test
    public void writeDataTypeTagAndReadInList() throws Exception {
        DataTypeTag dataTypeTag = new DataTypeTag(7,"test", "testdescription", 60, "min");
        dataTypeTagDao.insertAll(dataTypeTag);
        List<DataTypeTag> allDataTypeTags = dataTypeTagDao.getAll();
        assertThat(allDataTypeTags.size(), equalTo(1));
        DataTypeTag singleDataTypeTag = allDataTypeTags.get(0);
        assertThat(singleDataTypeTag.getName(), equalTo("test"));
        assertThat(singleDataTypeTag.getTagId(), equalTo(7));
        assertThat(singleDataTypeTag.getDescription(), equalTo("testdescription"));
        assertThat(singleDataTypeTag.getModifier(), equalTo(60));
        assertThat(singleDataTypeTag.getUnit(), equalTo("min"));
    }

    @Test
    public void writeDataContainerAndReadCheckSingle() throws Exception {
        dataTypeTagDao.insertAll(new DataTypeTag(2,"Aphrodite", "", 60, "min"));
        dataTagDao.insertAll(new DataTag(1,"High"));
        dataDao.insertAll(new Data(1,2,1, new Date(2020-1900,11-1,22), 90));
        LiveData<List<DataContainer>> allDataContainers = dataContainerDao.getAll();
        assertThat(Objects.requireNonNull(allDataContainers.getValue()).size(), equalTo(1));
        DataContainer singleDataContainer = allDataContainers.getValue().get(0);
        assertThat(singleDataContainer.getTypeName(), equalTo("Aphrodite"));
        assertThat(singleDataContainer.getTagName(), equalTo("High"));
        assertThat(singleDataContainer.getDateString(), equalTo("22.11.2020"));
        assertThat(singleDataContainer.getValue(), equalTo(1.5));
    }
}

