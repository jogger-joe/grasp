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
import net.usemyskills.grasp.persistence.dao.DataTypeDao;
import net.usemyskills.grasp.persistence.entity.Data;
import net.usemyskills.grasp.persistence.entity.DataTag;
import net.usemyskills.grasp.persistence.entity.DataType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Data.class, DataTag.class, DataType.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataContainerDao getDataContainerDao();
    public abstract DataTagDao getDataTagDao();
    public abstract DataTypeDao getDataTypeDao();
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
                DataTypeDao dataTypeDao = INSTANCE.getDataTypeDao();

                DataType[] dataTypes = {
                    new DataType(1, "Burpee Max", "Maximum Burpees in 5min", "x"),
                    new DataType(2, "Aphrodite", "Multiple Exercises 1", "Min"),
                    new DataType(3, "Hades", "Multiple Exercises 2", "Min"),
                    new DataType(4, "12 Min Run", "Maximum Distance in 12 Minutes", "Km")
                };
                for (DataType dataType: dataTypes) {
                    dataTypeDao.insert(dataType);
                }

                DataTag[] dataTags = {
                    new DataTag(1, "Easy", ""),
                    new DataTag(2, "Hard", "")
                };

                for (DataTag dataTag: dataTags) {
                    dataTagDao.insert(dataTag);
                }



            });
        }
    };

}
