package cr.una.timetrackingapp.backend.service;

import cr.una.timetrackingapp.backend.dao.ITimeSheetDao;
import cr.una.timetrackingapp.backend.model.TimeSheet;
import cr.una.timetrackingapp.backend.exception.TimeSheetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TimeSheetService
 * <p>
 * The Service for TimeSheet
 */
@Service
@Transactional
public class TimeSheetService implements ITimeSheetService {
    @Autowired
    private ITimeSheetDao dao;

    /**
     * Find timeSheet by id
     *
     * @param id identification of the timeSheet
     * @return the timeSheet found
     */
    @Override
    public TimeSheet findById(long id) throws TimeSheetNotFoundException{
        return dao.findById(id);
    }

    /**
     * Method to find all entities
     *
     * @return the list of entities of TimeSheet
     */
    @Override
    public List<TimeSheet> findAll() {
        return dao.findAll();
    }

    /**
     * Method to create a new entity
     *
     * @param timeSheet the entity to create in the database
     * @return the timeSheet created with the corresponding id
     */
    @Override
    public TimeSheet create(TimeSheet timeSheet) {
        return dao.create(timeSheet);
    }

    /**
     * Method to update the entity in the database
     *
     * @param timeSheet the entity with new information to update
     * @return the updated timeSheet
     */
    @Override
    public TimeSheet update(TimeSheet timeSheet) {
        return dao.update(timeSheet);
    }

    /**
     * Method to delete a entity in the database
     *
     * @param timeSheet the entity to deleted
     */
    @Override
    public void delete(TimeSheet timeSheet) {
        dao.delete(timeSheet);
    }

    /**
     * Method to delete a entity in the database by id
     *
     * @param id the id of the entity to delete
     */
    @Override
    public void deleteById(long id)throws TimeSheetNotFoundException {
        dao.deleteById(id);
    }
}
