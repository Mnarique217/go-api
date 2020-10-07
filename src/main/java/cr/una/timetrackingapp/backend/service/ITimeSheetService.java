package cr.una.timetrackingapp.backend.service;

import cr.una.timetrackingapp.backend.model.TimeSheet;
import cr.una.timetrackingapp.backend.exception.TimeSheetNotFoundException;

import java.util.List;

/**
 * TimeSheetService
 *
 * The Service for TimeSheet
 */
public interface ITimeSheetService {
    /**
     * Find timeSheet by id
     *
     * @param id identification of the timeSheet
     * @return the timeSheet found
     */
    TimeSheet findById(final long id) throws TimeSheetNotFoundException;

    /**
     * Method to find all entities
     *
     * @return the list of entities of TimeSheet
     */
    List<TimeSheet> findAll();

    /**
     * Method to create a new entity
     *
     * @param timeSheet the entity to create in the database
     * @return the timeSheet created with the corresponding id
     */
    TimeSheet create(final TimeSheet timeSheet);

    /**
     * Method to update the entity in the database
     *
     * @param timeSheet the entity with new information to update
     * @return the updated timeSheet
     */
    TimeSheet update(final TimeSheet timeSheet);

    /**
     * Method to delete a entity in the database
     *
     * @param timeSheet the entity to deleted
     */
    void delete(final TimeSheet timeSheet);

    /**
     * Method to delete a entity in the database by id
     *
     * @param id the id of the entity to delete
     */
    void deleteById(final long id) throws TimeSheetNotFoundException;
}
