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
import java.util.GregorianCalendar;
import java.util.concurrent.CountDownLatch;

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
        final CountDownLatch signal = new CountDownLatch(1);
        dataTagDao.getAll().observeForever(dataTags -> {
            assertThat(dataTags.size(), equalTo(1));
            DataTag singleDataTag = dataTags.get(0);
            assertThat(singleDataTag.getName(), equalTo("test"));
            assertThat(singleDataTag.getId(), equalTo(3));
            assertThat(singleDataTag.getDescription(), equalTo("testdescription"));
            signal.countDown();
        });
        dataTagDao.insert(new DataTag(3,"test", "testdescription"));
        signal.await();
    }

    @Test
    public void writeDataTagWithoutIdAndReadAll() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        dataTagDao.getAll().observeForever(dataTags -> {
            assertThat(dataTags.size(), equalTo(1));
            DataTag singleDataTag = dataTags.get(0);
            assertThat(singleDataTag.getName(), equalTo("test"));
            assertThat(singleDataTag.getId(), equalTo(1));
            assertThat(singleDataTag.getDescription(), equalTo("testdescription"));
            signal.countDown();
        });
        dataTagDao.insert(new DataTag("test", "testdescription"));
        signal.await();
    }

    @Test
    public void writeDataTypeTagAndReadAll() throws Exception {
        dataTypeDao.getAll().observeForever(dataTypeTags -> {
            assertThat(dataTypeTags.size(), equalTo(1));
            DataType singleDataType = dataTypeTags.get(0);
            assertThat(singleDataType.getName(), equalTo("test"));
            assertThat(singleDataType.getId(), equalTo(7));
            assertThat(singleDataType.getDescription(), equalTo("testdescription"));
            assertThat(singleDataType.getUnit(), equalTo("min"));
        });
        dataTypeDao.insert(new DataType(7,"test", "testdescription", "min"));
    }

    @Test
    public void writeDataAndReadAllDataContainer() throws Exception {
        dataContainerDao.getAll().observeForever(dataContainers -> {
            assertThat(dataContainers.size(), equalTo(1));
            DataContainer singleDataContainer = dataContainers.get(0);
            assertThat(singleDataContainer.getTypeName(), equalTo("Aphrodite"));
            assertThat(singleDataContainer.getTagName(), equalTo("High"));
            assertThat(singleDataContainer.getDateString(), equalTo("22.11.2020"));
            assertThat(singleDataContainer.getValue(), equalTo(90));
        });
        dataTypeDao.insert(new DataType(2,"Aphrodite", "", "min"));
        dataTagDao.insert(new DataTag(1,"High", ""));
        dataDao.insert(new Data(1,2,1, new GregorianCalendar(2020-1900,11-1,22).getTime(), 90));

    }
}

