package ca.kwisses.saveandquit.db_handler;

import android.content.ContentValues;

import org.junit.Test;

import ca.kwisses.saveandquit.user.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DBHandlerTest {

    @Test
    public void onCreate_CallsMethod_CalledMethod() {
        DBHandler testDBHandler1 = mock(DBHandler.class);
        testDBHandler1.onCreate(null);
        verify(testDBHandler1).onCreate(null);
    }

    @Test
    public void onUpgrade_CallsMethod_CalledMethod() {
        DBHandler testDBHandler2 = mock(DBHandler.class);
        testDBHandler2.onUpgrade(null, 0, 1);
        verify(testDBHandler2).onUpgrade(null, 0, 1);
    }

    @Test
    public void executeQuery_CallsMethod_CalledMethod() {
        DBHandler testDBHandler3 = mock(DBHandler.class);
        testDBHandler3.executeQuery(null, "");
        verify(testDBHandler3).executeQuery(null, "");
    }

    @Test
    public void executeQuery_PassEmptyQuery_ExecutedIsFalse() {
        DBHandler testDBHandler4 = new DBHandler(null, null, null, 1);
        boolean executed = testDBHandler4.executeQuery(null, "");
        assertFalse(executed);
    }

    @Test(expected = NullPointerException.class)
    public void executeQuery_PassNullDatabaseAndTestQuery_ThrowsNullPointerException() {
        DBHandler testDBHandler5 = new DBHandler(null, null, null, 1);
        testDBHandler5.executeQuery(null, "test query");
    }

    @Test
    public void getContentValues_CallsMethod_CalledMethod() {
        DBHandler testDBHandler6 = mock(DBHandler.class);
        User testUser = new User(1, 0, 0, 0, 0);
        testDBHandler6.getContentValues(testUser);
        verify(testDBHandler6).getContentValues(testUser);
    }

    @Test
    public void getContentValues_ReturnContentValues_TestValuesNotNull() {
        DBHandler testDBHandler7 = new DBHandler(null, null, null, 1);
        User testUser1 = new User(1, 0.0, 0, 0, 0);
        ContentValues testValues = testDBHandler7.getContentValues(testUser1);
        assertNotNull(testValues);
    }

    @Test
    public void addUser_CallsMethod_CalledMethod() {
        DBHandler testDBHandler8 = mock(DBHandler.class);
        User testUser2 = new User(1, 0, 0, 0, 0);
        testDBHandler8.addUser(testUser2);
        verify(testDBHandler8).addUser(testUser2);
    }

    @Test (expected = NullPointerException.class) // getWritableDatabase() is null
    public void addUser_CallMethodWithNewUser_ThrowNullPointerException() {
        DBHandler testDBHandler9 = new DBHandler(null, null, null, 1);
        User testUser3 = new User(1, 0, 0, 0, 0);
        testDBHandler9.addUser(testUser3);
    }

    @Test
    public void deleteUser_CallsMethod_CalledMethod() {
        DBHandler testDBHandler10 = mock(DBHandler.class);
        User testUser4 = new User(1, 0, 0, 0, 0);
        testDBHandler10.deleteUser(testUser4);
        verify(testDBHandler10).deleteUser(testUser4);
    }

    @Test (expected = NullPointerException.class) // getWritableDatabase() is null
    public void deleteUser_CallMethodWithNewUser_ThrowNullPointerException() {
        DBHandler testDBHandler11 = new DBHandler(null, null, null, 1);
        User testUser5 = new User(1, 0, 0, 0, 0);
        testDBHandler11.deleteUser(testUser5);
    }

    @Test
    public void getUserDataFromDatabase_CallsMethod_CalledMethod() {
        DBHandler testDBHandler12 = mock(DBHandler.class);
        testDBHandler12.getUserDataFromDatabase();
        verify(testDBHandler12).getUserDataFromDatabase();
    }

    @Test (expected = NullPointerException.class) // getWritableDatabase() is null
    public void getUserDataFromDatabase_CallMethod_ThrowNullPointerException() {
        DBHandler testDBHandler13 = new DBHandler(null, null, null, 1);
        testDBHandler13.getUserDataFromDatabase();
    }
}