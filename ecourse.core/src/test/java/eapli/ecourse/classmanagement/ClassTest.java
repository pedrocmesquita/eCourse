package eapli.ecourse.classmanagement;

import eapli.ecourse.classmanagement.domain.SchClass;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
public class ClassTest {

    @Test
    public void validSucess() {
        // Arrange
        String course = "Engenharia de Aplicações";
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.HOUR_OF_DAY, 2);

        SchClass schClass = new SchClass(course, startTime, endTime);

        assertNotNull(schClass);
        assertEquals(course, schClass.getCourse());
        assertEquals(startTime, schClass.getStartTime());
        assertEquals(endTime, schClass.getEndTime());
    }

    @Test
    public void NullCourseException() {
        String course = null;
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.HOUR_OF_DAY, 2);

        assertThrows(IllegalArgumentException.class, () -> new SchClass(course, startTime, endTime));
    }

    @Test
    public void NullStartException() {
        String course = "Bases de Dados";
        Calendar startTime = null;
        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.HOUR_OF_DAY, 2);

        assertThrows(IllegalArgumentException.class, () -> new SchClass(course, startTime, endTime));
    }

    @Test
    public void NullEndException() {
        String course = "Matemática Discreta";
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = null;

        assertThrows(IllegalArgumentException.class, () -> new SchClass(course, startTime, endTime));
    }

    @Test
    public void sameAs() {
        // Arrange
        String course = "Física";
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.HOUR_OF_DAY, 1);

        SchClass schClass1 = new SchClass(course, startTime, endTime);
        SchClass schClass2 = new SchClass(course, startTime, endTime);

        boolean result = schClass1.sameAs(schClass2);

        assertTrue(result);
    }

}

