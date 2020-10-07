package cr.una.timetrackingapp.backend.dao;

import com.google.common.base.Preconditions;
import cr.una.timetrackingapp.backend.model.TimeSheet;
import cr.una.timetrackingapp.backend.exception.TimeSheetNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO TimeSheet
 */
@Repository
public class TimeSheetDao extends AbstractHibernateDao implements ITimeSheetDao {

    @Override
    public TimeSheet findById(final long id) throws TimeSheetNotFoundException{
        TimeSheet timesheet = getCurrentSession().get(TimeSheet.class, id);
        if (timesheet == null) {
            throw new TimeSheetNotFoundException("TimeSheet Not Found");
        } else {
            return timesheet;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TimeSheet> findAll() {
        return getCurrentSession().createQuery("from TimeSheet").getResultList();
    }

    @Override
    public TimeSheet create(TimeSheet timeSheet) {
        Preconditions.checkNotNull(timeSheet);
        getCurrentSession().saveOrUpdate(timeSheet);

        return timeSheet;
    }

    @Override
    public TimeSheet update(TimeSheet timeSheet) {
        Preconditions.checkNotNull(timeSheet);
        return (TimeSheet) getCurrentSession().merge(timeSheet);
    }

    @Override
    public void delete(TimeSheet timeSheet) {
        Preconditions.checkNotNull(timeSheet);
        getCurrentSession().delete(timeSheet);
    }

    @Override
    public void deleteById(long id) throws TimeSheetNotFoundException {
        final TimeSheet timeSheet = findById(id);
        Preconditions.checkState(timeSheet != null);
        delete(timeSheet);
    }

}
