package net.usemyskills.grasp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import net.usemyskills.grasp.persistence.entity.Tag;
import net.usemyskills.grasp.persistence.entity.Type;

import java.util.List;

@Dao
public interface TagDao extends BaseDao<Tag> {
    @Query("SELECT * FROM Tag")
    LiveData<List<Tag>> getAll();

    @Query("SELECT * FROM Tag where name=:name")
    Type findByName(String name);
}
