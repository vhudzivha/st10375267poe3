package st10375267poe3.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import st10375267poe3.UserManagement;

public class UserManagementTest {

    @Test
    public void testCheckUserName() {
        assertTrue(UserManagement.checkUserName("abc_d"));
        assertFalse(UserManagement.checkUserName("abcdef"));
        assertFalse(UserManagement.checkUserName("abcde"));
    }

    @Test
    public void testCheckPasswordComplexity() {
        assertTrue(UserManagement.checkPasswordComplexity("Password1@"));
        assertFalse(UserManagement.checkPasswordComplexity("password"));
        assertFalse(UserManagement.checkPasswordComplexity("Password"));
    }
}
