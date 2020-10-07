package cr.una.timetrackingapp.backend.service;

import cr.una.timetrackingapp.backend.model.TimeRecord;
import cr.una.timetrackingapp.backend.exception.TimeRecordNotFoundException;

import java.util.List;

/**
 * TimeRecordService
 *
 * The Service for TimeRecord
 */
public interface ITimeRecordService {
    /**
     * Find timeRecord by id
     *
     * @param id identification of the timeRecord
     * @return the timeRecord found
     */
    TimeRecord findById(final long id) throws TimeRecordNotFoundException;

    /**
     * Method to find all entities
     *
     * @return the list of entities of TimeRecord
     */
    List<TimeRecord> findAll();

    /**
     * Method to create a new entity
     *
     * @param timeRecord the entity to create in the database
     * @return the timeRecord created with the corresponding id
     */
    TimeRecord create(final TimeRecord timeRecord);

    /**
     * Method to update the entity in the database
     *
     * @param timeRecord the entity with new information to update
     * @return the updated timeRecord
     */
    TimeRecord update(final TimeRecord timeRecord);

    /**
     * Method to delete a entity in the database
     *
     * @param timeRecord the entity to deleted
     */
    void delete(final TimeRecord timeRecord);

    /**
     * Method to delete a entity in the database by id
     *
     * @param id the id of the entity to delete
     */
    void deleteById(final long id) throws TimeRecordNotFoundException;
}
