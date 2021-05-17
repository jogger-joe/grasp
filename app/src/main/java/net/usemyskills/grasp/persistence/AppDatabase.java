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
import net.usemyskills.grasp.persistence.dao.DataTagDao;
import net.usemyskills.grasp.persistence.dao.DataTypeTagDao;
import net.usemyskills.grasp.persistence.entity.Data;
import net.usemyskills.grasp.persistence.entity.DataTag;
import net.usemyskills.grasp.persistence.entity.DataTypeTag;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Data.class, DataTag.class, DataTypeTag.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataContainerDao getDataContainerDao();
    public abstract DataTagDao getDataTagDao();
    public abstract DataTypeTagDao getDataTypeTagDao();
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
                DataTagDao dataTagDao = INSTANCE.getDataTagDao();
                DataTypeTagDao dataTypeTagDao = INSTANCE.getDataTypeTagDao();
                DataDao dataDao = INSTANCE.getDataDao();

                // clear prev data
                dataTagDao.deleteAll();
                dataTypeTagDao.deleteAll();
                dataDao.deleteAll();

                // insert typeTags
                dataTypeTagDao.insertAll(
                    new DataTypeTag(1, "Burpee Max", "Maximum Burpees in 5min", 1, "x"),
                    new DataTypeTag(2, "Aphrodite", "Multiple Exercises 1", 60, "Min"),
                    new DataTypeTag(3, "Hades", "Multiple Exercises 2", 60, "Min"),
                    new DataTypeTag(4, "12 Min Run", "Maximum Distance in 12 Minutes", 1000, "Km")
                );
                // insert other tags
                dataTagDao.insertAll(
                    new DataTag(1, "Easy"),
                    new DataTag(2, "Hard")
                );

                // create data
                dataDao.insertAll(
                    new Data(1, 1,1, new Date(2020-1900, 11-1, 22), 56),
                    new Data(2, 2,1, new Date(2020-1900, 11-1, 25), 200),
                    new Data(3, 1,1, new Date(2020-1900, 11-1, 27), 56),
                    new Data(4, 3,2, new Date(2020-1900, 11-1, 29), 56),
                    new Data(5, 4,1, new Date(2020-1900, 11-1, 23), 56),
                    new Data(6, 1,2, new Date(2020-1900, 11-1, 24), 56),
                    new Data(7, 3,1, new Date(2020-1900, 11-1, 11), 56),
                    new Data(8, 2,1, new Date(2020-1900, 11-1, 20), 56),
                    new Data(9, 1,2, new Date(2020-1900, 11-1, 8), 56),
                    new Data(10, 1,1, new Date(2020-1900, 11-1, 9), 56));
            });
        }
    };

}
