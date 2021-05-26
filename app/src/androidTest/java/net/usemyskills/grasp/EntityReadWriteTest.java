package net.usemyskills.grasp;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.RecordDao;
import net.usemyskills.grasp.persistence.dao.TagDao;
import net.usemyskills.grasp.persistence.dao.TypeDao;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;

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
    private TagDao tagDao;
    private TypeDao typeDao;
    private RecordDao dataDao;
    private DataContainerDao dataContainerDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        tagDao = db.getTagDao();
        typeDao = db.getTypeDao();
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
        tagDao.getAll().observeForever(dataTags -> {
            assertThat(dataTags.size(), equalTo(1));
            Tag singleTag = dataTags.get(0);
            assertThat(singleTag.getName(), equalTo("test"));
            assertThat(singleTag.getId(), equalTo(3));
            assertThat(singleTag.getDescription(), equalTo("testdescription"));
            signal.countDown();
        });
        tagDao.insert(new Tag(3,"test", "testdescription"));
        signal.await();
    }

    @Test
    public void writeDataTagWithoutIdAndReadAll() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        tagDao.getAll().observeForever(dataTags -> {
            assertThat(dataTags.size(), equalTo(1));
            Tag singleTag = dataTags.get(0);
            assertThat(singleTag.getName(), equalTo("test"));
            assertThat(singleTag.getId(), equalTo(1));
            assertThat(singleTag.getDescription(), equalTo("testdescription"));
            signal.countDown();
        });
        tagDao.insert(new Tag("test", "testdescription"));
        signal.await();
    }

    @Test
    public void writeDataTypeTagAndReadAll() throws Exception {
        typeDao.getAll().observeForever(dataTypeTags -> {
            assertThat(dataTypeTags.size(), equalTo(1));
            Type singleType = dataTypeTags.get(0);
            assertThat(singleType.getName(), equalTo("test"));
            assertThat(singleType.getId(), equalTo(7));
            assertThat(singleType.getDescription(), equalTo("testdescription"));
            assertThat(singleType.getUnit(), equalTo("min"));
        });
        typeDao.insert(new Type(7,"test", "testdescription", "min"));
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
        typeDao.insert(new Type(2,"Aphrodite", "", "min"));
        tagDao.insert(new Tag(1,"High", ""));
        dataDao.insert(new Data(1,2,1, new GregorianCalendar(2020-1900,11-1,22).getTime(), 90));

    }
}

