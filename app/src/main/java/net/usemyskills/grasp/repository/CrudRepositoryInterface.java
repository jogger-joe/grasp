package net.usemyskills.grasp.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface CrudRepositoryInterface<T> {
    LiveData<List<T>>  getAll();
    long insert(T element);
    void update(T element);
    void delete(T element);
}
