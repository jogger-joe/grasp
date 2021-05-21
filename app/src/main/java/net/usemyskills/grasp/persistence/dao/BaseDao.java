package net.usemyskills.grasp.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import net.usemyskills.grasp.persistence.entity.BaseEntity;

@Dao
public interface BaseDao<T extends BaseEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T entity);

    @Delete
    void delete(T entity);

    @Update
    void update(T entity);
}
