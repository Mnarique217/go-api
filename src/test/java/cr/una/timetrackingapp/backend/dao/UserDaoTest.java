package cr.una.timetrackingapp.backend.dao;

import cr.una.timetrackingapp.backend.model.Department;
import cr.una.timetrackingapp.backend.model.Role;
import cr.una.timetrackingapp.backend.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test Class
 * Task DAO
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@Sql({
        "/import-department.sql",
        "/import-roles.sql",
        "/import-privileges.sql",
        "/import-users.sql",
        "/import-user-role.sql",
})
class UserDaoTest {

    @Autowired
    private IUserDao userDao;

    @Test
    public void testFindById() {
        User user = userDao.findById(1L);
        assertEquals(user.getFirstName().toString(), "Nikol");
    }

    @Test
    public void testFindAll() {
        List<User> userList = userDao.findAll();
        assertEquals(3,userList.size());
    }

    @Test
    public void testUpdate() {

        User user = userDao.findById(1L);
        user.setFirstName("Test Update");
        User result = userDao.update(user);
        assertEquals(result.getFirstName().toString(), "Test Update");
    }

    @Test
    public void testCreate() {
        // given
        final Long id = 999l;
        User userExpected = new User();
        User user = new User();
        Role rol = new Role();
        Department department = new Department();

        department.setId(1L);
        rol.setIdRole(1L);
        user.setRoleList(Arrays.asList(rol));
        user.setDepartment(department);
        user.setFirstName("TestCreate");
        user.setLastName("TestCreate");
        user.setIdUser(id);

        User result =  userDao.create(user);
        assertEquals(result.getIdUser(), Long.valueOf(999L));
    }

    @Test
    public void testDelete() {
        User user = userDao.findById(2);
        userDao.delete(user);
        user = userDao.findById(2);
        assertNull(user);
    }
/*
    @Test
    public void testDeleteById() {
        userDao.deleteById(2L);

        User user = userDao.findById(2);
        assertTrue(user == null);
    }*/
}
