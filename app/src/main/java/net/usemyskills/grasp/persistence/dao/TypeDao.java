package net.usemyskills.grasp.persistence.dao;

import androidx.room.Dao;
import androidx.room.Query;

import net.usemyskills.grasp.persistence.entity.Type;

import java.util.List;

@Dao
public interface TypeDao extends BaseDao<Type> {
    @Query("SELECT * FROM Type")
    List<Type> getAll();

    @Query("SELECT * FROM Type where groupId=:id")
    List<Type> getByGroupId(long id);

    @Query("SELECT * FROM Type where tagId=:id")
    Type getById(long id);
}
