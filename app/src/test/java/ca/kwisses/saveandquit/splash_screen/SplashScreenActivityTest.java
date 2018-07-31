package ca.kwisses.saveandquit.splash_screen;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SplashScreenActivityTest {

    @Test
    public void onCreate_CallsMethod_CalledMethod() {
        SplashScreenActivity mockSplashScreenActivity = mock(SplashScreenActivity.class);
        mockSplashScreenActivity.onCreate(null);
        verify(mockSplashScreenActivity).onCreate(null);
    }
}