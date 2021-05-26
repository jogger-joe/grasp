package net.usemyskills.grasp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import net.usemyskills.grasp.persistence.entity.BaseEntity;

import java.util.List;

@Dao
public interface BaseDao<T extends BaseEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T entity);

    @Delete
    void delete(T entity);

    @Update
    void update(T entity);

    LiveData<List<T>> getAll();
}
