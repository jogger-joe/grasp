package net.usemyskills.grasp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import net.usemyskills.grasp.persistence.entity.Type;

import java.util.List;

@Dao
public interface TypeDao extends BaseDao<Type> {
    @Query("SELECT * FROM Type")
    LiveData<List<Type>> getAll();

    @Query("SELECT * FROM Type where name=:name")
    Type findByName(String name);
}
