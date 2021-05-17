package net.usemyskills.grasp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import net.usemyskills.grasp.persistence.entity.DataContainer;

import java.util.List;

@Dao
public interface DataContainerDao {
    @Transaction
    @Query("SELECT * FROM Data ORDER BY date DESC")
    LiveData<List<DataContainer>> getAll();
}
