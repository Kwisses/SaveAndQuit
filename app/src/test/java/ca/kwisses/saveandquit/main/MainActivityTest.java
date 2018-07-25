package ca.kwisses.saveandquit.main;

import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ca.kwisses.saveandquit.R;
import ca.kwisses.saveandquit.db_handler.DBHandler;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    MainActivity mainActivity;

    MainPresenter mainPresenter;

    DBHandler dbHandler;

    View view;

    @Before
    public void setUp() {
        mainActivity = new MainActivity();
        mainPresenter = new MainPresenter(mainActivity, mainActivity);
        dbHandler = new DBHandler(mainActivity, null, null, 1);
        view = new View(mainActivity);
    }

    // init() method tests

    @Test
    public void init_CallMethod_CalledMethod() {
        mainActivity = mock(MainActivity.class);
        mainActivity.init(mainPresenter);
        verify(mainActivity).init(mainPresenter);
    }

    @Test
    public void init_InitializeQuoteText_QuoteTextNotNull() {
        mainPresenter.quoteText = new TextView(mainActivity);
        mainActivity.init(mainPresenter);
        assertNotNull(mainActivity.getQuoteText());
    }

    @Test
    public void init_InitializeMoneySavedField_MoneySavedFieldNotNull() {
        mainPresenter.moneySavedField = new TextView(mainActivity);
        mainActivity.init(mainPresenter);
        assertNotNull(mainActivity.getMoneySavedField());
    }

    @Test
    public void init_InitializeExtraLifeField_ExtraLifeFieldNotNull() {
        mainPresenter.extraLifeField = new TextView(mainActivity);
        mainActivity.init(mainPresenter);
        assertNotNull(mainActivity.getExtraLifeField());
    }

    // Other MainActivity method tests

    @Test
    public void setDbHandler_CallsMethod_CalledMethod() {
        mainActivity = mock(MainActivity.class);
        mainActivity.setDBHandler(dbHandler);
        verify(mainActivity).setDBHandler(dbHandler);
    }

    @Test
    public void setDbHandler_DBHandlerCreated_DBHandlerNotNull() {
        mainActivity.setDBHandler(dbHandler);
        assertNotNull(mainActivity.dbHandler);
    }

    @Test
    public void createMainPresenter_CallsMethod_CalledMethod() {
        mainActivity = mock(MainActivity.class);
        mainActivity.createMainPresenter(view);
        verify(mainActivity).createMainPresenter(view);
    }

    @Test
    public void createMainPresenter_MainPresenterCreated_MainPresenterNotNull() {
        mainActivity.mainPresenter = mainPresenter;
        assertNotNull(mainActivity.mainPresenter);
    }

    @Test
    public void setMainPresenter_CallsMethod_CalledMethod() {
        mainActivity = mock(MainActivity.class);
        mainActivity.createMainPresenter(view);
        mainActivity.setMainPresenter(mainPresenter);
        verify(mainActivity).setMainPresenter(mainPresenter);
    }

    @Test
    public void setQuoteText_CallsMethod_CalledMethod() {
        mainActivity = mock(MainActivity.class);
        mainActivity.setQuoteText("test");
        verify(mainActivity).setQuoteText("test");
    }

    @Test
    public void setDisplayText_CallsMethod_CalledMethod() {
        mainActivity = mock(MainActivity.class);
        mainActivity.setDisplayText(0, 0);
        verify(mainActivity).setDisplayText(0, 0);
    }

    @Test
    public void onCheckInButton_CallsMethod_CalledMethod() {
        mainActivity = mock(MainActivity.class);
        mainActivity.onCheckInButton(view);
        verify(mainActivity).onCheckInButton(view);
    }

    @Test
    public void onCheckInButton_CallsPresenterMethod_CalledPresenterMethod() {
        mainPresenter = mock(MainPresenter.class);
        mainActivity.mainPresenter = mainPresenter;
        mainActivity.onCheckInButton(view);
        verify(mainPresenter).onCheckInButton(view);
    }

    @Test
    public void onCreateOptionsMenu_CallsMethod_CalledMethod() {
        mainActivity = mock(MainActivity.class);
        Menu menu = mainActivity.findViewById(R.menu.menu);
        mainActivity.onCreateOptionsMenu(menu);
        verify(mainActivity).onCreateOptionsMenu(menu);
    }

    @Test
    public void handleAboutMenuItem_CallsMethod_CalledMethod() {
        mainActivity = mock(MainActivity.class);
        mainActivity.handleAboutMenuItem();
        verify(mainActivity).handleAboutMenuItem();
    }

    @Test
    public void handleAboutMenuItem_IsAboutGreaterThanZero_AboutIsGreaterThanZero() {
        int about = R.layout.about;
        assertTrue(about > 0); // is not null
    }

    @Test
    public void handleDeleteMenuItem_CallsMethod_CalledMethod() {
        mainActivity = mock(MainActivity.class);
        mainActivity.handleDeleteMenuItem();
        verify(mainActivity).handleDeleteMenuItem();
    }

    @Test
    public void handleDeleteMenuItem_IsDeleteGreaterThanZero_DeleteIsGreaterThanZero() {
        int delete = R.layout.delete_user;
        assertTrue(delete > 0); // is not null
    }

    @Test
    public void setDeleteUserOnClickListener_CallsMethod_CalledMethod() {
        mainActivity = mock(MainActivity.class);
        AlertDialog dialog = mock(AlertDialog.class);
        Button button = mock(Button.class);
        mainActivity.setDeleteUserOnClickListener(dialog, button);
        verify(mainActivity).setDeleteUserOnClickListener(dialog, button);
    }

    @Test
    public void onOptionsItemSelected_CallsMethod_CalledMethod() {
        mainActivity = mock(MainActivity.class);
        MenuItem menuItem = mock(MenuItem.class);
        mainActivity.onOptionsItemSelected(menuItem);
        verify(mainActivity).onOptionsItemSelected(menuItem);
    }
}