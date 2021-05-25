package net.usemyskills.grasp.persistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import net.usemyskills.grasp.persistence.converter.DateConverter;
import net.usemyskills.grasp.persistence.dao.DataContainerDao;
import net.usemyskills.grasp.persistence.dao.DataDao;
import net.usemyskills.grasp.persistence.dao.TagDao;
import net.usemyskills.grasp.persistence.dao.TypeDao;
import net.usemyskills.grasp.persistence.entity.Data;
import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Data.class, Tag.class, Type.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataContainerDao getDataContainerDao();
    public abstract TagDao getTagDao();
    public abstract TypeDao getTypeDao();
    public abstract DataDao getDataDao();

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
                DataDao dataDao = INSTANCE.getDataDao();

                Type[] types = {
                    new Type(1, "Burpee Max", "Maximum Burpees in 5min", "x"),
                    new Type(2, "Aphrodite", "Multiple Exercises 1", "Min"),
                    new Type(3, "Hades", "Multiple Exercises 2", "Min"),
                    new Type(4, "12 Min Run", "Maximum Distance in 12 Minutes", "Km")
                };
                for (Type type : types) {
                    typeDao.insert(type);
                }

                Tag[] tags = {
                    new Tag(1, "Easy", ""),
                    new Tag(2, "Hard", "")
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
                    dataDao.insert(new Data(rndTypeId+1, rndTagId+1, rndDate, rndValue));
                }

            });
        }
    };

}
