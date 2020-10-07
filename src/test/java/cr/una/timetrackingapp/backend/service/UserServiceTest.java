package cr.una.timetrackingapp.backend.service;

import cr.una.timetrackingapp.backend.dao.IUserDao;
import cr.una.timetrackingapp.backend.model.Department;
import cr.una.timetrackingapp.backend.model.Role;
import cr.una.timetrackingapp.backend.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserDao userDao;

    @Test
    public void testFindById() {
       // given
       final Long id = 1l;
        User userExpected = new User();
        User user = new User();
        Role rol = new Role();
        Department department = new Department();

        user.setRoleList(Arrays.asList(rol));
        user.setDepartment(department);
        user.setFirstName("Test");
        user.setLastName("Test");

        given(userDao.findById(id)).willReturn(user);

        userExpected = userService.findById(id);
        assertThat(userExpected).isNotNull();
    }

    @Test
    public void testFindAll() {
        // given
        final Long id = 1l;
        User user = new User();
        List<User> userList = Arrays.asList(user);

        given(userDao.findAll()).willReturn(userList);

        // when
        List<User> userListExpected = userService.findAll();

        // then
        assertThat(userList).isEqualTo(userListExpected);
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

        given(userDao.create(user)).willReturn(user);
        // when
        User userCreated = userService.create(user);
        // then
        assertThat(userCreated).isNotNull();
    }

    @Test
    public void testUpdate() {
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
        given(userDao.update(user)).willReturn(user);
        // when
        User userUpdated = userService.update(user);
        // then
        assertThat(userUpdated).isNotNull();
    }
}
