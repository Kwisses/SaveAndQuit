package ca.kwisses.saveandquit.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {

    MainActivity mainActivity;

    MainPresenter mainPresenter;

    @Before
    public void setup() {
        mainActivity = mock(MainActivity.class);
        mainPresenter = mock(MainPresenter.class);
    }

    @Test
    public void getQuoteTextFromFile_CallsMethod_CalledMethod() {
        mainPresenter.getQuoteTextFromFile();
        verify(mainPresenter).getQuoteTextFromFile();
    }

    @Test
    public void getQuoteTextFromFile_TestForEquality_MethodReturnEqualsStringLiteral() {
        when(mainPresenter.getQuoteTextFromFile()).thenReturn("test");
        assertEquals("test", mainPresenter.getQuoteTextFromFile());
    }

}