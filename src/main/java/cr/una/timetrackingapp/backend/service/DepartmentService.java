package cr.una.timetrackingapp.backend.service;

import cr.una.timetrackingapp.backend.dao.IDepartmentDao;
import cr.una.timetrackingapp.backend.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DepartmentService
 * <p>
 * The Service for Department
 */
@Service
@Transactional
public class DepartmentService implements IDepartmentService {
    @Autowired
    private IDepartmentDao dao;

    /**
     * Find department by id
     *
     * @param id identification of the department
     * @return the department found
     */
    @Override
    public Department findById(long id) {
        return dao.findById(id);
    }

    /**
     * Method to find all entities
     *
     * @return the list of entities of Department
     */
    @Override
    public List<Department> findAll() {
        return dao.findAll();
    }

    /**
     * Method to create a new entity
     *
     * @param department the entity to create in the database
     * @return the department created with the corresponding id
     */
    @Override
    public Department create(Department department) {
        return dao.create(department);
    }

    /**
     * Method to update the entity in the database
     *
     * @param department the entity with new information to update
     * @return the updated department
     */
    @Override
    public Department update(Department department) {
        return dao.update(department);
    }

    /**
     * Method to delete a entity in the database
     *
     * @param department the entity to deleted
     */
    @Override
    public void delete(Department department) {
        dao.delete(department);
    }

    /**
     * Method to delete a entity in the database by id
     *
     * @param id the id of the entity to delete
     */
    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }
}
