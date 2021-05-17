package net.usemyskills.grasp.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import net.usemyskills.grasp.persistence.entity.DataTag;

import java.util.List;

@Dao
public interface DataTagDao {
    @Insert
    void insertAll(DataTag... dataTags);

    @Delete
    void delete(DataTag dataTag);

    @Query("DELETE FROM DataTag")
    void deleteAll();

    @Query("SELECT * FROM DataTag")
    List<DataTag> getAll();

}
