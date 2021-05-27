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
import net.usemyskills.grasp.persistence.dao.TagDao;
import net.usemyskills.grasp.persistence.dao.TypeDao;
import net.usemyskills.grasp.persistence.entity.RecordGroup;
import net.usemyskills.grasp.persistence.entity.Record;
import net.usemyskills.grasp.persistence.entity.RecordTags;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RecordGroup.class, Record.class, RecordTags.class, Tag.class, Type.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TagDao getTagDao();
    public abstract TypeDao getTypeDao();
    public abstract RecordDao getRecordDao();
    public abstract RecordGroupDao getRecordGroupDao();

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

                recordGroupDaoDao.insert(new RecordGroup(1,"Sport", "", 0, R.drawable.ic_run));
                recordGroupDaoDao.insert(new RecordGroup(2,"Weight", "", 0, R.drawable.ic_person));
                recordGroupDaoDao.insert(new RecordGroup(3,"Health", "", 0, R.drawable.ic_heart));
                recordGroupDaoDao.insert(new RecordGroup(4,"Misc", "", 0, R.drawable.ic_default));

                Type[] types = {
                    new Type(1, "Burpee Max", "Maximum Burpees in 5min",1,"", "x"),
                    new Type(2, "Squat Max", "Maximum Squats in 5min",1,"", "x"),
                    new Type(3, "Aphrodite", "Multiple Exercises 1", 1,"","Min"),
                    new Type(4, "Hades", "Multiple Exercises 2", 1,"","Min"),
                    new Type(5, "12 Min Run", "Maximum Distance in 12 Minutes",1,"", "Km")
                };
                for (Type type : types) {
                    typeDao.insert(type);
                }

                Tag[] tags = {
                    new Tag(1, "Easy", "", 1),
                    new Tag(2, "Hard", "", 1)
                };

                for (Tag tag : tags) {
                    tagDao.insert(tag);
                }

                int amountOfGeneratedData = 20;
                int iteration = 0;
                Random rnd = new Random();
                while (iteration < amountOfGeneratedData) {
                    iteration++;
                    int rndTypeId = rnd.nextInt(types.length);
                    int rndTagId = rnd.nextInt(tags.length);
                    int rndValue = rnd.nextInt(200);
                    Date rndDate = new GregorianCalendar(rnd.nextInt(20)+2000, rnd.nextInt(12), rnd.nextInt(29)).getTime();
                    recordDao.insert(new Record(rndTypeId+1, 1, rndDate, rndValue));
                }

            });
        }
    };

}
