package net.usemyskills.grasp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import net.usemyskills.grasp.persistence.entity.DataTypeTag;

import java.util.List;

@Dao
public interface DataTypeTagDao {
    @Insert
    void insertAll(DataTypeTag... dataTags);

    @Delete
    void delete(DataTypeTag dataTag);

    @Query("DELETE FROM DataTypeTag")
    void deleteAll();

    @Query("SELECT * FROM DataTypeTag")
    List<DataTypeTag> getAll();

}
