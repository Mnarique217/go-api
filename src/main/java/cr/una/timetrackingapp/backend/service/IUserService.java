package cr.una.timetrackingapp.backend.service;

import cr.una.timetrackingapp.backend.model.User;

import java.util.List;

/**
 * DepartmentService
 *
 * The Service for Department
 */

public interface IUserService {
    /**
     * Find department by id
     *
     * @param id identification of an user
     * @return the department found
     */
    public User findById(final long id);

    /**
     * Method to find all entities
     *
     * @return the list of entities of User
     */
    public List<User> findAll();

    /**
     * Method to create a new entity
     *
     * @param user the entity to create in the database
     * @return the user created with the corresponding id
     */
    public User create(final User user);

    /**
     * Method to update the entity in the database
     *
     * @param user the entity with new information to update
     * @return the updated User
     */
    public User update(final User user);

    /**
     * Method to delete a entity in the database
     *
     * @param user the entity to be deleted
     */
    public void delete(final User user);

    /**
     * Method to delete a entity in the database by id
     *
     * @param id the id of the entity to delete
     */
    public void deleteById(final long id);
}
