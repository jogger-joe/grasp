package net.usemyskills.grasp.persistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.persistence.converter.DateConverter;
import net.usemyskills.grasp.persistence.dao.RecordDao;
import net.usemyskills.grasp.persistence.dao.RecordGroupDao;
import net.usemyskills.grasp.persistence.dao.RecordTagsReferenceDao;
import net.usemyskills.grasp.persistence.dao.TagDao;
import net.usemyskills.grasp.persistence.dao.TypeDao;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.persistence.entity.Record;
import net.usemyskills.grasp.persistence.entity.RecordTagsReference;
import net.usemyskills.grasp.persistence.entity.RecordWithTypeAndTags;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RecordGroup.class, Record.class, RecordTagsReference.class, Tag.class, Type.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TagDao getTagDao();
    public abstract TypeDao getTypeDao();
    public abstract RecordDao getRecordDao();
    public abstract RecordGroupDao getRecordGroupDao();
    public abstract RecordTagsReferenceDao getRecordTagsReferenceDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // create dummy content
            databaseWriteExecutor.execute(() -> {
                TagDao tagDao = INSTANCE.getTagDao();
                TypeDao typeDao = INSTANCE.getTypeDao();
                RecordDao recordDao = INSTANCE.getRecordDao();
                RecordGroupDao recordGroupDaoDao = INSTANCE.getRecordGroupDao();
                RecordTagsReferenceDao recordTagsReferenceDao = INSTANCE.getRecordTagsReferenceDao();

                recordGroupDaoDao.insert(new RecordGroup(1,"Sport", "", 0, R.drawable.ic_run));
                recordGroupDaoDao.insert(new RecordGroup(2,"Körper", "", 0, R.drawable.ic_person));
                recordGroupDaoDao.insert(new RecordGroup(3,"Serien", "", 0, R.drawable.ic_play));
                recordGroupDaoDao.insert(new RecordGroup(4,"Schlafenszeiten", "", 0, R.drawable.ic_bedtime));
                recordGroupDaoDao.insert(new RecordGroup(5,"Temperatur", "", 0, R.drawable.ic_frost));
                recordGroupDaoDao.insert(new RecordGroup(6,"Einkäufe", "", 0, R.drawable.ic_card));

                Type[] sportTypes = {
                    new Type(1, "Crosstrainer", "",1,false, "min"),
                    new Type(2, "Joggen", "",1,false, "min"),
                    new Type(3, "Kniebeuge", "", 1,true,"x")
                };
                Type[] bodyTypes = {
                    new Type(4, "Gewicht", "",2,false, "Kg"),
                    new Type(5, "Bauchumfang", "",2,false, "cm"),
                    new Type(6, "Puls", "",2,true, "bpm"),
                };
                for (Type type : sportTypes) {
                    typeDao.insert(type);
                }
                for (Type type : bodyTypes) {
                    typeDao.insert(type);
                }

                Tag[] sportTags = {
                    new Tag(1, "5km", "", 1),
                    new Tag(2, "5min", "", 1),
                };

                Tag[] bodyTags = {
                    new Tag(3, "morgens", "", 2),
                    new Tag(4, "mittags", "", 2),
                    new Tag(5, "abends", "", 2),
                    new Tag(6, "vor dem Essen", "", 2),
                    new Tag(7, "nach dem Essen", "", 2),
                };

                for (Tag tag : sportTags) {
                    tagDao.insert(tag);
                }
                for (Tag tag : bodyTags) {
                    tagDao.insert(tag);
                }

                recordDao.insert(new Record(1,1, 1, new Date(), 10));
                recordTagsReferenceDao.insert(new RecordTagsReference(1, 1));

                recordDao.insert(new Record(2,2, 1, new Date(), 3));
                recordTagsReferenceDao.insert(new RecordTagsReference(2, 1));

                recordDao.insert(new Record(3,3, 1, new Date(), 81));
                recordTagsReferenceDao.insert(new RecordTagsReference(3, 2));

                recordDao.insert(new Record(4,4, 2, new Date(), 90.7));
                recordTagsReferenceDao.insert(new RecordTagsReference(4, 3));
                recordTagsReferenceDao.insert(new RecordTagsReference(4, 6));

                recordDao.insert(new Record(5,6, 2, new Date(), 109));
                recordTagsReferenceDao.insert(new RecordTagsReference(5, 5));

            });
        }
    };

}
