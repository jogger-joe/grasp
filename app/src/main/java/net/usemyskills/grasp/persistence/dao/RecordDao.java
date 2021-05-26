package net.usemyskills.grasp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import net.usemyskills.grasp.persistence.entity.FullRecord;
import net.usemyskills.grasp.persistence.entity.Record;

import java.util.List;

@Dao
public interface RecordDao extends BaseDao<Record> {
    @Transaction
    @Query("SELECT * FROM Record ORDER BY date DESC")
    LiveData<List<FullRecord>> getAllFull();

    @Transaction
    @Query("SELECT * FROM Record ORDER BY date DESC")
    LiveData<List<Record>> getAll();
}
