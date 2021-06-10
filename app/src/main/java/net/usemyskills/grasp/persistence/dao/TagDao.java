package net.usemyskills.grasp.persistence.dao;

import androidx.room.Dao;
import androidx.room.Query;

import net.usemyskills.grasp.persistence.entity.Tag;

import java.util.List;

@Dao
public interface TagDao extends BaseDao<Tag> {
    @Query("SELECT * FROM Tag")
    List<Tag> getAll();

    @Query("SELECT * FROM Tag where groupId=:id")
    List<Tag> getByGroupId(long id);

    @Query("SELECT * FROM Tag where tagId=:id")
    Tag getById(long id);
}
