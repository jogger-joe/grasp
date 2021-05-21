package net.usemyskills.grasp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import net.usemyskills.grasp.persistence.entity.DataType;

import java.util.List;

@Dao
public interface DataTypeDao extends BaseDao<DataType> {
    @Query("SELECT * FROM DataType")
    LiveData<List<DataType>> getAll();
}
