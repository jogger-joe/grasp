package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.usemyskills.grasp.persistence.entity.DataTag;
import net.usemyskills.grasp.persistence.entity.DataTypeTag;
import net.usemyskills.grasp.persistence.repository.DataTagRepository;

import java.util.List;

public class DataTagViewModel extends AndroidViewModel {

    private final DataTagRepository dataTagRepository;

    private final List<DataTag> dataTags;
    private final List<DataTypeTag> dataTypeTags;

    public DataTagViewModel(Application application) {
        super(application);
        this.dataTagRepository = new DataTagRepository(application);
        this.dataTags = this.dataTagRepository.getAllDataTags();
        this.dataTypeTags = this.dataTagRepository.getAllDataTypeTags();
    }

    public List<DataTag> getAllDataTags() { return this.dataTags; }
    public List<DataTypeTag> getAllDataTypeTags() { return this.dataTypeTags; }
}

