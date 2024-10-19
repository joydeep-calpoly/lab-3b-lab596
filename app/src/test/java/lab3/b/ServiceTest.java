package lab3.b;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ServiceTest {

    static Database db;
    static Service service;

    @BeforeAll
    public static void setup(){
        db = Mockito.mock(Database.class);
        service = new Service(db);
    }
    
    @Test void testGetDatabaseID() {

        when(db.getUniqueId()).thenReturn(5);
        String response = service.getDatabaseID();

        String expected = "Using database with id: 5";
        assertEquals(expected, response);

        when(db.getUniqueId()).thenReturn(27);
        response = service.getDatabaseID();
        expected = "Using database with id: 27";
        assertEquals(expected, response);
    }

    @Test void testQuery() {

        when(db.isAvailable()).thenReturn(true);
        Boolean response = service.query("Test");
        
        assertTrue(response);

        when(db.isAvailable()).thenReturn(false);
        response = service.query("Test");
        
        assertFalse(response);
    }
}
