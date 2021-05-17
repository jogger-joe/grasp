package net.usemyskills.grasp.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import net.usemyskills.grasp.persistence.entity.Data;
import net.usemyskills.grasp.persistence.entity.DataTag;

import java.util.List;

@Dao
public interface DataDao {
    @Insert
    void insertAll(Data... dataTags);

    @Delete
    void delete(Data dataTag);

    @Query("DELETE FROM Data")
    void deleteAll();

    @Query("SELECT * FROM Data")
    List<Data> getAll();

}
