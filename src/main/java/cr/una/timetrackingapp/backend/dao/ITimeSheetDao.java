package cr.una.timetrackingapp.backend.dao;

import cr.una.timetrackingapp.backend.model.TimeSheet;
import cr.una.timetrackingapp.backend.exception.TimeSheetNotFoundException;

import java.util.List;

/**
 * DAO TimeSheet
 */
public interface ITimeSheetDao {
    public TimeSheet findById (final long id) throws TimeSheetNotFoundException;
    public List<TimeSheet> findAll();
    public TimeSheet create (final TimeSheet timeSheet);
    public TimeSheet update (final TimeSheet timeSheet);
    public void delete (final TimeSheet timeSheet);
    public void deleteById (final long id) throws TimeSheetNotFoundException;
}
