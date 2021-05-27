package net.usemyskills.grasp.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import net.usemyskills.grasp.persistence.entity.RecordTagsReference;

@Dao
public interface RecordTagsReferenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(RecordTagsReference entity);

    @Delete
    void delete(RecordTagsReference entity);
}
