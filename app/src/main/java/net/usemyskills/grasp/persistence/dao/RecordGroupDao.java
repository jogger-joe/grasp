package net.usemyskills.grasp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import net.usemyskills.grasp.persistence.entity.RecordGroup;

import java.util.List;

@Dao
public interface RecordGroupDao extends BaseDao<RecordGroup> {
    @Query("SELECT * FROM RecordGroup")
    LiveData<List<RecordGroup>> getAll();

    @Query("SELECT * FROM RecordGroup where tagId=:id")
    RecordGroup findById(long id);
}
