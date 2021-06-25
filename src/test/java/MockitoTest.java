import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockitoTest {
    Student local;
    Student remote;

    StudentDAO studentDAO;

    @Mock
    StudentDAOI studentDAOI;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();//apjungiamas mockio ir jUnit

    @Before
    public void setUp() {
        local = new Student("Antanelis11", "IT 18/1 T", 2.5, "KTU", 1, "2021.06.25");
        studentDAO = new StudentDAO(studentDAOI);
    }

    @Test
    public void searchByIdTest() {
       remote = new Student("Antanelis11", "IT 18/1 T", 2.5, "KTU", 1, "2021.06.25");
        when(studentDAO.searchById(1)).thenReturn(remote); //kai kreipsimes i imitacini objekta (db klases viduje kreipsimes)
        compareStudents(local, studentDAO.searchById(1));
        verify(studentDAOI).searchById(1);//ar buvo kreiptasi i imituojanti objekta
    }

    private void compareStudents(Student local, Student remote) {
        Assert.assertEquals(local.getVardas(), remote.getVardas());
        Assert.assertEquals(local.getId(), remote.getId());
    }
}
