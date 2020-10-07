package cr.una.timetrackingapp.backend.dao;

import cr.una.timetrackingapp.backend.model.TimeRecord;
import cr.una.timetrackingapp.backend.exception.TimeRecordNotFoundException;

import java.util.List;

/**
 * DAO TimeSheet
 */
public interface ITimeRecordDao {
    public TimeRecord findById(final long id) throws TimeRecordNotFoundException;
    public List<TimeRecord> findAll();
    public TimeRecord create(final TimeRecord timeRecord);
    public TimeRecord update(final TimeRecord timeRecord);
    public void delete(final TimeRecord timeRecord);
    public void deleteById(final long id) throws TimeRecordNotFoundException;
}
