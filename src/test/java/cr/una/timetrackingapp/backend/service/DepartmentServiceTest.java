package cr.una.timetrackingapp.backend.service;

import cr.una.timetrackingapp.backend.dao.IDepartmentDao;
import cr.una.timetrackingapp.backend.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
class DepartmentServiceTest {

    @Mock
    private IDepartmentDao departmentDao;

    @InjectMocks
    private DepartmentService departmentServiceUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testFindById() {
        // given
        final Long id = 1l;
        Department departmentExpected = new Department();
        Department department = new Department();
        department.setNombre("Department1");

        given(departmentDao.findById(id)).willReturn(department);

        // when
        departmentExpected = departmentServiceUnderTest.findById(id);

        // then
        assertThat(departmentExpected).isNotNull();
    }

    @Test
    void testFindAll() {
        // given
        final Long id = 1l;
        Department department = new Department();
        List<Department> departmentList = Arrays.asList(department);

        given(departmentDao.findAll()).willReturn(departmentList);

        // when
        List<Department> departmentListExpected = departmentServiceUnderTest.findAll();

        // then
        assertThat(departmentList).isEqualTo(departmentListExpected);
    }

    @Test
    void testCreate() {
        // given
        final Long id = 1L;
        Department departmentCreated = new Department();
        Department department = new Department();
        department.setId(id);
        department.setNombre("Department1");

        given(departmentDao.create(department)).willReturn(department);

        // when
        departmentCreated = departmentServiceUnderTest.create(department);

        // then
        assertThat(departmentCreated).isNotNull();
    }

    @Test
    void testUpdate() {
        // given
        final Long id = 1L;
        Department departmentUpdated = new Department();
        Department department = new Department();
        department.setId(id);
        department.setNombre("DepartmentUpdated");

        given(departmentDao.update(department)).willReturn(department);

        // when
        departmentUpdated = departmentServiceUnderTest.update(department);

        // then
        assertThat(departmentUpdated).isNotNull();
    }

}
