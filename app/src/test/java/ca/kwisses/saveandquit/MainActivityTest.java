package ca.kwisses.saveandquit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ca.kwisses.saveandquit.main.MainActivity;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    MainActivity mainActivity;

    @Before
    public void setUp() {
        mainActivity = new MainActivity();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void onCreate() {
        assertNotNull(mainActivity);
    }

    @Test
    public void onCreateOptionsMenu() {
        System.out.println(mainActivity.findViewById(R.menu.menu));
    }

    @Test
    public void onOptionsItemSelected() {

    }
}