package cr.una.timetrackingapp.backend.dao;

import cr.una.timetrackingapp.backend.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@Sql({
        "/import-department.sql"
})
class DepartmentDaoTest {

    @Autowired
    private IDepartmentDao departmentDaoUnderTest;

    @Test
    void testFindById() {
        // Setup

        // Run the test
        final Department result = departmentDaoUnderTest.findById(1L);

        // Verify the results
        assertEquals(result.getNombre(), "Department1");
    }

    @Test
    void testFindAll() {
        // Setup

        // Run the test
        final List<Department> result = departmentDaoUnderTest.findAll();

        // Verify the results
        assertEquals(3, result.size());
    }

    @Test
    void testCreate() {
        // Setup
        final Department department = new Department();
        department.setId(4L);
        department.setNombre("Department4");

        // Run the test
        final Department result = departmentDaoUnderTest.create(department);

        // Verify the results
        assertEquals(result.getNombre().toString(), "Department4");
    }

    @Test
    void testUpdate() {
        // Setup
        final Department department = new Department();
        department.setId(3L);
        department.setNombre("Updating Department Name");

        // Run the test
        final Department result = departmentDaoUnderTest.update(department);

        // Verify the results
        assertEquals(result.getNombre().toString(), "Updating Department Name");
    }

    @Test
    void testDelete() {
        // Setup
        final Department department = new Department();
        department.setId(3L);

        // Run the test
        departmentDaoUnderTest.delete(department);

        // Verify the results
        assertNull(departmentDaoUnderTest.findById(3L));
    }

    @Test
    void testDeleteById() {
        // Setup

        // Run the test
        departmentDaoUnderTest.deleteById(2L);

        // Verify the results
        assertNull(departmentDaoUnderTest.findById(2L));
    }
}
