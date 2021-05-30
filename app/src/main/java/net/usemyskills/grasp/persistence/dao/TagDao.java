package net.usemyskills.grasp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.List;

@Dao
public interface TagDao extends BaseDao<Tag> {
    @Query("SELECT * FROM Tag")
    LiveData<List<Tag>> getAll();

    @Query("SELECT * FROM Tag where groupId=:id")
    Tag findByGroup(int id);

    @Query("SELECT * FROM Tag where tagId=:id")
    Tag findById(long id);
}
