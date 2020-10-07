package cr.una.timetrackingapp.backend.dao;

import cr.una.timetrackingapp.backend.model.Department;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO Department
 */
@Repository
public class DepartmentDao extends AbstractHibernateDao implements IDepartmentDao {
    @Override
    public Department findById(long id) {
        return getCurrentSession().get(Department.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List findAll() {
        return getCurrentSession().createQuery("from Department").getResultList();
    }

    @Override
    public Department create(Department department) {
        Preconditions.checkNotNull(department);
        getCurrentSession().saveOrUpdate(department);
        return department;
    }

    @Override
    public Department update(Department department) {
        Preconditions.checkNotNull(department);
        return (Department) getCurrentSession().merge(department);
    }

    @Override
    public void delete(Department department) {
        Preconditions.checkNotNull(department);
        getCurrentSession().delete(department);
    }

    @Override
    public void deleteById(long id) {
        final Department department = findById(id);
        Preconditions.checkState(department != null);
        delete(department);
    }
}
