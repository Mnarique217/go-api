package cr.una.timetrackingapp.backend.dao;

import cr.una.timetrackingapp.backend.model.User;

import java.util.List;

public interface IUserDao {
    public User findById (final long id);
    public User findByUsername (final String username);
    public List<User> findAll();
    public User create (final User user);
    public User update (final User user);
    public void delete (final User user);
    public void deleteById (final long id);
}