package ca.kwisses.saveandquit.check_in;

import android.widget.EditText;

import org.junit.Test;

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
    public void isFieldFull_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter2 = mock(CheckInPresenter.class);
        EditText mockEditText1 = mock(EditText.class);
        mockCheckInPresenter2.isFieldFull(mockEditText1);
        verify(mockCheckInPresenter2).isFieldFull(mockEditText1);
    }

    @Test
    public void allFieldsFull_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter3 = mock(CheckInPresenter.class);
        mockCheckInPresenter3.allFieldsFull();
        verify(mockCheckInPresenter3).allFieldsFull();
    }

    @Test
    public void setUserData_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter4 = mock(CheckInPresenter.class);
        mockCheckInPresenter4.setUserData();
        verify(mockCheckInPresenter4).setUserData();
    }

    @Test (expected = NullPointerException.class)
    public void setUserData_CallMethodWithoutSetup_ThrowsNullPointerException() {
        CheckInPresenter testCheckInPresenter1 = new CheckInPresenter(null);
        testCheckInPresenter1.setUserData(); // userData is null
    }

    @Test
    public void updateDBHander_CallsMethod_CalledMethod() {
        CheckInPresenter mockCheckInPresenter5 = mock(CheckInPresenter.class);
        mockCheckInPresenter5.updateDBHander();
        verify(mockCheckInPresenter5).updateDBHander();
    }

    @Test (expected = NullPointerException.class)
    public void updateDBHandler_CallMethodWithoutInitDbHandler_ThrowsNullPointerException() {
        CheckInPresenter testCheckInPresenter2 = new CheckInPresenter(null);
        testCheckInPresenter2.updateDBHander(); // dbHandler is null
    }
}