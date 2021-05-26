package net.usemyskills.grasp.viewmodel;

import android.app.Application;

import net.usemyskills.grasp.persistence.entity.Record;
import net.usemyskills.grasp.repository.BaseRepository;
import net.usemyskills.grasp.repository.RecordRepository;

public class RecordViewModel extends BaseViewModel<Record> {
    public RecordViewModel(Application application) {
        super(application);
    }

    @Override
    protected BaseRepository<Record> getRepository(Application application) {
        return new RecordRepository(application);
    }
}

