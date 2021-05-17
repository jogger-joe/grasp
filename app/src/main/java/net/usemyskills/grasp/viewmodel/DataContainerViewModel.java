package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.entity.DataContainer;
import net.usemyskills.grasp.persistence.repository.DataContainerRepository;

import java.util.List;

public class DataContainerViewModel extends AndroidViewModel {

    private final DataContainerRepository dataContainerRepository;

    private final LiveData<List<DataContainer>> dataContainers;

    public DataContainerViewModel(Application application) {
        super(application);
        this.dataContainerRepository = new DataContainerRepository(application);
        this.dataContainers = dataContainerRepository.getAll();
    }

    LiveData<List<DataContainer>> getAll() { return dataContainers; }

    public void insert(DataContainer dataContainer) { dataContainerRepository.insert(dataContainer); }
}

