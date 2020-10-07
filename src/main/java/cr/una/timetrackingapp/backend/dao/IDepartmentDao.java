package cr.una.timetrackingapp.backend.dao;

import cr.una.timetrackingapp.backend.model.Department;

import java.util.List;

/**
 * DAO Department
 */
public interface IDepartmentDao {
    public Department findById(final long id);

    public List<Department> findAll();

    public Department create(final Department department);

    public Department update(final Department department);

    public void delete(final Department department);

    public void deleteById(final long id);
}
