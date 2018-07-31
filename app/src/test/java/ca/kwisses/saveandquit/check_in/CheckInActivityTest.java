package ca.kwisses.saveandquit.check_in;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CheckInActivityTest {

    @Test
    public void setCheckInPresenter_CallsMethod_CalledMethod() {
        CheckInActivity mockCheckInActivity1 = mock(CheckInActivity.class);
        CheckInPresenter mockCheckInPresenter1 = mock(CheckInPresenter.class);
        mockCheckInActivity1.setCheckInPresenter(mockCheckInPresenter1);
        verify(mockCheckInActivity1).setCheckInPresenter(mockCheckInPresenter1);
    }

    @Test
    public void setCheckInPresenter_SetNewCheckInPresenter_CheckInPresenterNotNull() {
        CheckInActivity testCheckInActivity1 = new CheckInActivity();
        CheckInPresenter testCheckInPresenter1 = new CheckInPresenter(null);
        testCheckInActivity1.setCheckInPresenter(testCheckInPresenter1);
        assertNotNull(testCheckInActivity1.getCheckInPresenter());
    }

    @Test
    public void onSubmitButton_CallsMethod_CalledMethod() {
        CheckInActivity mockCheckInActivity2 = mock(CheckInActivity.class);
        mockCheckInActivity2.onSubmitButton(null);
        verify(mockCheckInActivity2).onSubmitButton(null);
    }
}