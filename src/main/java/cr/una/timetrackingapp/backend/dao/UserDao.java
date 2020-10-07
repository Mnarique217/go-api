package cr.una.timetrackingapp.backend.dao;

import com.google.common.base.Preconditions;
import cr.una.timetrackingapp.backend.model.TimeSheet;
import cr.una.timetrackingapp.backend.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
/**
 * DAO User
 */
@Repository
public class UserDao extends AbstractHibernateDao implements IUserDao{
    @Override
    public User findById(long id) {
        return getCurrentSession().get(User.class, id);
    }


    /**
     * Method to find the entity by email
     *
     * @param username the username of the entity to find
     * @return the corresponding UserDao
     */
    @Override
    public User findByUsername(String username) {
        User user = null;
        Query query = getCurrentSession().createQuery("from User u where u.username = :username");
        query.setParameter("username", username);
        if (query.getResultList().size()>0) {
            user = (User) query.getResultList().get(0);
        }

        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return getCurrentSession().createQuery("from User").getResultList();
    }

    @Override
    public User create(User user) {
        Preconditions.checkNotNull(user);
        getCurrentSession().saveOrUpdate(user);

        return user;
    }

    @Override
    public User update(User user) {
        Preconditions.checkNotNull(user);
        return (User) getCurrentSession().merge(user);
    }

    @Override
    public void delete(User user) {
        Preconditions.checkNotNull(user);
        getCurrentSession().delete(user);
    }

    @Override
    public void deleteById(long id) {
        final User user = findById(id);
        Preconditions.checkState(user != null);
        delete(user);
    }
}
