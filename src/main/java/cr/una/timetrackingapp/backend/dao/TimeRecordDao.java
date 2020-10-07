package cr.una.timetrackingapp.backend.dao;

import com.google.common.base.Preconditions;
import cr.una.timetrackingapp.backend.model.TimeRecord;
import cr.una.timetrackingapp.backend.exception.TimeRecordNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO TimeRecord
 */
@Repository
public class TimeRecordDao extends AbstractHibernateDao implements ITimeRecordDao {

    @Override
    public TimeRecord findById(final long id) throws TimeRecordNotFoundException {
        TimeRecord timeRecord = getCurrentSession().get(TimeRecord.class, id);
        if (timeRecord == null) {
            throw new TimeRecordNotFoundException("TimeRecord Not Found");
        } else {
            return timeRecord;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TimeRecord> findAll() {
        return getCurrentSession().createQuery("from TimeRecord").getResultList();
    }

    @Override
    public TimeRecord create(TimeRecord timeRecord) {
        Preconditions.checkNotNull(timeRecord);
        getCurrentSession().saveOrUpdate(timeRecord);

        return timeRecord;
    }

    @Override
    public TimeRecord update(TimeRecord timeRecord) {
        Preconditions.checkNotNull(timeRecord);
        return (TimeRecord) getCurrentSession().merge(timeRecord);
    }

    @Override
    public void delete(TimeRecord timeRecord) {
        Preconditions.checkNotNull(timeRecord);
        getCurrentSession().delete(timeRecord);
    }

    @Override
    public void deleteById(long id) throws TimeRecordNotFoundException {
        final TimeRecord timeRecord = findById(id);
        Preconditions.checkState(timeRecord != null);
        delete(timeRecord);
    }

}
