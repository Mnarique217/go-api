package cr.una.timetrackingapp.backend.service;

import cr.una.timetrackingapp.backend.dao.IUserDao;
import cr.una.timetrackingapp.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * User Service
 *
 * The Service for Task
 */
@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private IUserDao dao;


    /**
     * Method to find the entity by id
     *
     * @param id the id of the entity to find
     * @return the corresponding task
     */
    @Override
    public User findById(long id) {
        return dao.findById(id);
    }

    /**
     * Method to find all entities
     *
     * @return the list of entities of Task
     */
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    /**
     * Method to create a new entity
     *
     * @param user the entity to create in the database
     * @return the task created with the corresponding id
     */
    @Override
    public User create(User user) {
        return dao.create(user);
    }

    /**
     * Method to update the entity in the database
     *
     * @param user the entity with new information to update
     * @return the updated task
     */
    @Override
    public User update(User user) {
        return dao.update(user);
    }

    /**
     * Method to delete a entity in the database
     *
     * @param user the entity to deleted
     */
    @Override
    public void delete(User user) {
        dao.delete(user);
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
