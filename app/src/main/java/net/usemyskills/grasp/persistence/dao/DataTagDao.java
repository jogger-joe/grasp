package net.usemyskills.grasp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import net.usemyskills.grasp.persistence.entity.DataTag;

import java.util.List;

@Dao
public interface DataTagDao extends BaseDao<DataTag> {
    @Query("SELECT * FROM DataTag")
    LiveData<List<DataTag>> getAll();

}
