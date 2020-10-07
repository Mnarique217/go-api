package cr.una.timetrackingapp.backend.service;

import cr.una.timetrackingapp.backend.dao.ITimeRecordDao;
import cr.una.timetrackingapp.backend.model.TimeRecord;
import cr.una.timetrackingapp.backend.exception.TimeRecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TimeRecordService
 * <p>
 * The Service for TimeRecord
 */
@Service
@Transactional
public class TimeRecordService implements ITimeRecordService {
    @Autowired
    private ITimeRecordDao dao;

    /**
     * Find timeRecord by id
     *
     * @param id identification of the timeRecord
     * @return the timeRecord found
     */
    @Override
    public TimeRecord findById(long id) throws TimeRecordNotFoundException {
        return dao.findById(id);
    }

    /**
     * Method to find all entities
     *
     * @return the list of entities of TimeRecord
     */
    @Override
    public List<TimeRecord> findAll() {
        return dao.findAll();
    }

    /**
     * Method to create a new entity
     *
     * @param timeRecord the entity to create in the database
     * @return the timeRecord created with the corresponding id
     */
    @Override
    public TimeRecord create(TimeRecord timeRecord) {
        return dao.create(timeRecord);
    }

    /**
     * Method to update the entity in the database
     *
     * @param timeRecord the entity with new information to update
     * @return the updated timeRecord
     */
    @Override
    public TimeRecord update(TimeRecord timeRecord) {
        return dao.update(timeRecord);
    }

    /**
     * Method to delete a entity in the database
     *
     * @param timeRecord the entity to deleted
     */
    @Override
    public void delete(TimeRecord timeRecord) {
        dao.delete(timeRecord);
    }

    /**
     * Method to delete a entity in the database by id
     *
     * @param id the id of the entity to delete
     */
    @Override
    public void deleteById(long id) throws TimeRecordNotFoundException {
        dao.deleteById(id);
    }
}
