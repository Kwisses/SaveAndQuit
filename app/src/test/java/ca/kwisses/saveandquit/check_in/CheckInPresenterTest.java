package ca.kwisses.saveandquit.check_in;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CheckInPresenterTest {

    @Test
    public void init_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter1 = mock(CheckInPresenter.class);
        mockCheckInPresenter1.init(null);
        verify(mockCheckInPresenter1).init(null);
    }

    @Test
    public void isStringFull_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter2 = mock(CheckInPresenter.class);
        mockCheckInPresenter2.isStringFull(null);
        verify(mockCheckInPresenter2).isStringFull(null);
    }

    @Test
    public void isStringFull_PassFullStringLiteral_IsFullIsTrue() {
        CheckInPresenter testCheckInPresenter1 = new CheckInPresenter(null);
        boolean isFull = testCheckInPresenter1.isStringFull("full string");
        assertTrue(isFull);
    }

    @Test
    public void isStringFull_PassEmptyStringLiteral_IsFullIsFalse() {
        CheckInPresenter testCheckInPresenter2 = new CheckInPresenter(null);
        boolean isFull = testCheckInPresenter2.isStringFull("");
        assertFalse(isFull);
    }

    @Test
    public void allFieldsFull_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter3 = mock(CheckInPresenter.class);
        mockCheckInPresenter3.allFieldsFull();
        verify(mockCheckInPresenter3).allFieldsFull();
    }

    @Test
    public void isStringInRange_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter4 = mock(CheckInPresenter.class);
        mockCheckInPresenter4.isStringInRange(null);
        verify(mockCheckInPresenter4).isStringInRange(null);
    }

    @Test
    public void isStringInRange_PassStringLiteralInRange_InRangeIsTrue() {
        CheckInPresenter testCheckInPresenter3 = new CheckInPresenter(null);
        boolean inRange = testCheckInPresenter3.isStringInRange("55");
        assertTrue(inRange);
    }

    @Test
    public void isStringInRange_PassStringLiteralBelowRange_InRangeIsFalse() {
        CheckInPresenter testCheckInPresenter4 = new CheckInPresenter(null);
        boolean inRange = testCheckInPresenter4.isStringInRange("-1");
        assertFalse(inRange);
    }

    @Test
    public void isStringInRange_PassStringLiteralAboveRange_InRangeIsFalse() {
        CheckInPresenter testCheckInPresenter5 = new CheckInPresenter(null);
        boolean inRange = testCheckInPresenter5.isStringInRange("156");
        assertFalse(inRange);
    }

    @Test
    public void allFieldsInRange_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter5 = mock(CheckInPresenter.class);
        mockCheckInPresenter5.allFieldsInRange();
        verify(mockCheckInPresenter5).allFieldsInRange();
    }

    @Test
    public void isStringInputValid_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter6 = mock(CheckInPresenter.class);
        mockCheckInPresenter6.isStringInputValid(null);
        verify(mockCheckInPresenter6).isStringInputValid(null);
    }

    @Test
    public void isStringInputValid_PassValidStringLiteral_IsValidIsTrue() {
        CheckInPresenter testCheckInPresenter6 = new CheckInPresenter(null);
        boolean isValid = testCheckInPresenter6.isStringInputValid("14.50");
        assertTrue(isValid);
    }

    @Test
    public void isStringInputValid_PassSpaceStringLiteral_IsValidIsFalse() {
        CheckInPresenter testCheckInPresenter7 = new CheckInPresenter(null);
        boolean isValid = testCheckInPresenter7.isStringInputValid(" ");
        assertFalse(isValid);
    }

    @Test
    public void isStringInputValid_PassPeriodStringLiteral_IsValidIsFalse() {
        CheckInPresenter testCheckInPresenter8 = new CheckInPresenter(null);
        boolean isValid = testCheckInPresenter8.isStringInputValid(".");
        assertFalse(isValid);
    }

    @Test
    public void isStringInputValid_PassCommaStringLiteral_IsValidIsFalse() {
        CheckInPresenter testCheckInPresenter9 = new CheckInPresenter(null);
        boolean isValid = testCheckInPresenter9.isStringInputValid(",");
        assertFalse(isValid);
    }

    @Test
    public void isStringInputValid_PassDashStringLiteral_IsValidIsFalse() {
        CheckInPresenter testCheckInPresenter10 = new CheckInPresenter(null);
        boolean isValid = testCheckInPresenter10.isStringInputValid("-");
        assertFalse(isValid);
    }

    @Test
    public void isStringInputValid_PassUnderscoreStringLiteral_IsValidIsFalse() {
        CheckInPresenter testCheckInPresenter11 = new CheckInPresenter(null);
        boolean isValid = testCheckInPresenter11.isStringInputValid("_");
        assertFalse(isValid);
    }

    @Test
    public void allFieldInputsValid_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter7 = mock(CheckInPresenter.class);
        mockCheckInPresenter7.allFieldInputsValid();
        verify(mockCheckInPresenter7).allFieldInputsValid();

    }

    @Test
    public void setUserData_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter8 = mock(CheckInPresenter.class);
        mockCheckInPresenter8.setUserData();
        verify(mockCheckInPresenter8).setUserData();
    }

    @Test (expected = NullPointerException.class)
    public void setUserData_CallMethodWithoutSetup_ThrowsNullPointerException() {
        CheckInPresenter testCheckInPresenter12 = new CheckInPresenter(null);
        testCheckInPresenter12.setUserData(); // userData is null
    }

    @Test
    public void updateDBHander_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter9 = mock(CheckInPresenter.class);
        mockCheckInPresenter9.updateDBHander();
        verify(mockCheckInPresenter9).updateDBHander();
    }

    @Test (expected = NullPointerException.class)
    public void updateDBHandler_CallMethodWithoutInitDbHandler_ThrowsNullPointerException() {
        CheckInPresenter testCheckInPresenter13 = new CheckInPresenter(null);
        testCheckInPresenter13.updateDBHander(); // dbHandler is null
    }
}