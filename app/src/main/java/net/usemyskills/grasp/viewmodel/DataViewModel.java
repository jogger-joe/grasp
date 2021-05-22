package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.entity.Data;
import net.usemyskills.grasp.persistence.entity.DataContainer;
import net.usemyskills.grasp.persistence.repository.DataContainerRepository;
import net.usemyskills.grasp.persistence.repository.DataRepository;

import java.util.List;


public class DataViewModel extends AndroidViewModel {

    private final DataRepository dataRepository;
    private final LiveData<List<DataContainer>> dataContainers;

    public DataViewModel(Application application) {
        super(application);
        this.dataRepository = new DataRepository(application);
        DataContainerRepository dataContainerRepository = new DataContainerRepository(application);
        this.dataContainers = dataContainerRepository.getAll();
    }

    public void insert(Data data) { dataRepository.insert(data); }
    public LiveData<List<DataContainer>> getAll() { return dataContainers; }
}

