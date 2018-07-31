package ca.kwisses.saveandquit.splash_screen;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SplashScreenPresenterTest {

    @Test
    public void run_CallsMethod_CalledMethod() {
        SplashScreenPresenter mockSplashScreenPresenter = mock(SplashScreenPresenter.class);
        mockSplashScreenPresenter.run(null);
        verify(mockSplashScreenPresenter).run(null);
        Thread thread = new Thread();
    }

    @Test
    public void run_CallMethod_ThreadNotNull() {
        SplashScreenPresenter testSplashScreenPresenter = new SplashScreenPresenter();
        testSplashScreenPresenter.run(null);
        assertNotNull(testSplashScreenPresenter.getThread());
    }
}