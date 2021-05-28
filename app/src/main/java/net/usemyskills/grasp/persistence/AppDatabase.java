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
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RecordGroup.class, Record.class, RecordTagsReference.class, Tag.class, Type.class}, version = 1)
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

            databaseWriteExecutor.execute(() -> {
                TagDao tagDao = INSTANCE.getTagDao();
                TypeDao typeDao = INSTANCE.getTypeDao();
                RecordDao recordDao = INSTANCE.getRecordDao();
                RecordGroupDao recordGroupDaoDao = INSTANCE.getRecordGroupDao();
                RecordTagsReferenceDao recordTagsReferenceDao = INSTANCE.getRecordTagsReferenceDao();

                recordGroupDaoDao.insert(new RecordGroup(1,"Sport", "", 0, R.drawable.ic_run));
                recordGroupDaoDao.insert(new RecordGroup(2,"Body", "", 0, R.drawable.ic_person));
                recordGroupDaoDao.insert(new RecordGroup(3,"Misc", "", 0, R.drawable.ic_default));

                Type[] sportTypes = {
                    new Type(1, "Burpee Max", "Maximum Burpees in 5min",1,"", "x"),
                    new Type(2, "Squat Max", "Maximum Squats in 5min",1,"", "x"),
                    new Type(3, "Aphrodite", "Multiple Exercises 1", 1,"","Min"),
                    new Type(4, "Hades", "Multiple Exercises 2", 1,"","Min"),
                    new Type(5, "12 Min Run", "Maximum Distance in 12 Minutes",1,"", "Km"),
                };
                Type[] bodyTypes = {
                    new Type(6, "Weight", "",2,"", "Kg"),
                    new Type(7, "Waist", "",2,"", "cm"),
                    new Type(8, "Pulse", "",2,"", "bpm"),
                };
                for (Type type : sportTypes) {
                    typeDao.insert(type);
                }
                for (Type type : bodyTypes) {
                    typeDao.insert(type);
                }

                Tag[] sportTags = {
                    new Tag(1, "Easy", "", 1),
                    new Tag(2, "Hard", "", 1),
                };

                Tag[] bodyTags = {
                    new Tag(3, "Pre Food", "", 2),
                    new Tag(4, "Post Food", "", 2),
                    new Tag(5, "Morning", "", 2),
                    new Tag(6, "Chill", "", 2),
                    new Tag(7, "Walk", "", 2),
                };

                for (Tag tag : sportTags) {
                    tagDao.insert(tag);
                }
                for (Tag tag : bodyTags) {
                    tagDao.insert(tag);
                }

                int amountOfGeneratedData = 20;
                int sportIteration = 0;
                Random rnd = new Random();
                while (sportIteration < amountOfGeneratedData) {
                    sportIteration++;
                    int rndTypeId = rnd.nextInt(sportTypes.length);
                    int rndValue = rnd.nextInt(200);
                    Date rndDate = new GregorianCalendar(rnd.nextInt(20)+2000, rnd.nextInt(12), rnd.nextInt(29)).getTime();
                    recordDao.insert(new Record(rndTypeId, 1, rndDate, rndValue));
                }

                int bodyIteration = 0;
                while (bodyIteration < amountOfGeneratedData) {
                    bodyIteration++;
                    int rndTypeId = rnd.nextInt(sportTypes.length);
                    int rndValue = rnd.nextInt(200);
                    Date rndDate = new GregorianCalendar(rnd.nextInt(20)+2000, rnd.nextInt(12), rnd.nextInt(29)).getTime();
                    recordDao.insert(new Record(rndTypeId, 2, rndDate, rndValue));
                }

                int healthIteration = 0;
                while (healthIteration < amountOfGeneratedData) {
                    healthIteration++;
                    int rndValue = rnd.nextInt(200);
                    Date rndDate = new GregorianCalendar(rnd.nextInt(20)+2000, rnd.nextInt(12), rnd.nextInt(29)).getTime();
                    recordDao.insert(new Record(8, 3, rndDate, rndValue));
                }

            });
        }
    };

}
