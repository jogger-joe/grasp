package net.usemyskills.grasp;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import net.usemyskills.grasp.persistence.AppDatabase;
import net.usemyskills.grasp.persistence.dao.RecordDao;
import net.usemyskills.grasp.persistence.dao.RecordGroupDao;
import net.usemyskills.grasp.persistence.dao.RecordTagsReferenceDao;
import net.usemyskills.grasp.persistence.dao.TagDao;
import net.usemyskills.grasp.persistence.dao.TypeDao;
import net.usemyskills.grasp.persistence.entity.Record;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.persistence.entity.RecordTagsReference;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(AndroidJUnit4.class)
public class EntityReadWriteTest {
    private TagDao tagDao;
    private TypeDao typeDao;
    private RecordDao recordDao;
    private RecordGroupDao recordGroupDao;
    private RecordTagsReferenceDao recordTagsReferenceDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        tagDao = db.getTagDao();
        typeDao = db.getTypeDao();
        recordDao = db.getRecordDao();
        recordGroupDao = db.getRecordGroupDao();
        recordTagsReferenceDao = db.getRecordTagsReferenceDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void tagWriteReadTest() {
        this.tagDao.insert(new Tag( 1, "testTag1", "", 1));
        this.tagDao.insert(new Tag( 2, "testTag2", "", 2));
        this.tagDao.insert(new Tag( 3, "testTag3", "", 2));
        List<Tag> allTags = this.tagDao.getAll();
        assertThat(allTags.size(), equalTo(3));
        List<Tag> allTagsInGroup2 = this.tagDao.getByGroupId(2);
        assertThat(allTagsInGroup2.size(), equalTo(2));
        Tag tagWithId1 = this.tagDao.getById(1);
        assertThat(tagWithId1.tagId, equalTo((long)1));
        assertThat(tagWithId1.name, equalTo("testTag1"));
    }

    @Test
    public void typeWriteReadTest() {
        this.typeDao.insert(new Type( 1, "testType1", "", 1, "", ""));
        this.typeDao.insert(new Type( 2, "testType2", "", 2, "", ""));
        this.typeDao.insert(new Type( 3, "testType3", "", 2, "", ""));
        List<Type> allTypes = this.typeDao.getAll();
        assertThat(allTypes.size(), equalTo(3));
        List<Type> allTypesInGroup2 = this.typeDao.getByGroupId(2);
        assertThat(allTypesInGroup2.size(), equalTo(2));
        Type typeWithId1 = this.typeDao.getById(1);
        assertThat(typeWithId1.tagId, equalTo((long)1));
        assertThat(typeWithId1.name, equalTo("testType1"));
    }

    @Test
    public void recordGroupWriteReadTest() {
        this.recordGroupDao.insert(new RecordGroup( 1, "testRecordGroup1", "", 0, 0));
        this.recordGroupDao.insert(new RecordGroup( 2, "testRecordGroup2", "", 0, 0));
        this.recordGroupDao.insert(new RecordGroup( 3, "testRecordGroup3", "", 0, 0));
        List<RecordGroup> allRecordGroups = this.recordGroupDao.getAll();
        assertThat(allRecordGroups.size(), equalTo(3));
        RecordGroup recordGroupWithId1 = this.recordGroupDao.getById(1);
        assertThat(recordGroupWithId1.tagId, equalTo((long)1));
        assertThat(recordGroupWithId1.name, equalTo("testRecordGroup1"));
    }

    @Test
    public void recordWriteReadTest() {
        this.recordGroupDao.insert(new RecordGroup( 1, "testRecordGroup1", "", 0, 0));
        this.recordGroupDao.insert(new RecordGroup( 1, "testRecordGroup2", "", 0, 0));
        this.recordGroupDao.insert(new RecordGroup( 1, "testRecordGroup3", "", 0, 0));

        this.tagDao.insert(new Tag( 1, "testTag1", "", 1));
        this.tagDao.insert(new Tag( 2, "testTag2", "", 2));
        this.tagDao.insert(new Tag( 3, "testTag3", "", 2));

        this.typeDao.insert(new Type( 1, "testType1", "", 1, "", ""));
        this.typeDao.insert(new Type( 2, "testType2", "", 2, "", ""));
        this.typeDao.insert(new Type( 3, "testType3", "", 2, "", ""));

        recordDao.insert(new Record(1,1, 1, new Date(), 10));
        recordTagsReferenceDao.insert(new RecordTagsReference(1, 1));

        recordDao.insert(new Record(2,2, 1, new Date(), 3));
        recordTagsReferenceDao.insert(new RecordTagsReference(2, 1));

        recordDao.insert(new Record(3,2, 1, new Date(), 81));
        recordTagsReferenceDao.insert(new RecordTagsReference(3, 2));

        recordDao.insert(new Record(4,3, 2, new Date(), 90.7));
        recordTagsReferenceDao.insert(new RecordTagsReference(4, 2));
        recordTagsReferenceDao.insert(new RecordTagsReference(4, 3));

        recordDao.insert(new Record(5,3, 3, new Date(), 109));
        recordTagsReferenceDao.insert(new RecordTagsReference(5, 1));

        List<RecordWithTypeAndTags> allRecords = this.recordDao.getAll();
        assertThat(allRecords.size(), equalTo(5));

        RecordWithTypeAndTags recordWithId4 = this.recordDao.getById(4);
        assertThat(recordWithId4.record.recordId, equalTo((long)4));
        assertThat(recordWithId4.tags.size(), equalTo(2));
        assertThat(recordWithId4.type.tagId, equalTo((long)3));
        assertThat(recordWithId4.type.name, equalTo("testType3"));
    }

}

