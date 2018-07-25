package ca.kwisses.saveandquit.main;

import android.view.View;
import android.widget.Button;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.kwisses.saveandquit.db_handler.DBHandler;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainActivity mainActivity;

    @Mock
    MainPresenter mainPresenter;

    @Before
    public void setUp() {
        mainActivity = mock(MainActivity.class);
        mainPresenter = mock(MainPresenter.class);
    }

    @Test
    public void init_CallsMethod_CalledMethod() {
        View view = mock(View.class);
        mainPresenter.init(view);
        verify(mainPresenter).init(view);
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

    @Test
    public void loadUser_CallsMethod_CalledMethod() {
        DBHandler dbHandler = mock(DBHandler.class);
        mainPresenter.loadUser(dbHandler);
        verify(mainPresenter).loadUser(dbHandler);
    }

    @Test(expected=NullPointerException.class)
    public void loadUser_dbHandlerIsNull_ThrowsNullPointerException() {
        MainPresenter mainPresenter1 = new MainPresenter(null, null);
        DBHandler dbHandler = null;
        mainPresenter1.loadUser(dbHandler);
    }

    @Test
    public void createNewUser_CallsMethod_CalledMethod() {
        DBHandler dbHandler = mock(DBHandler.class);
        mainPresenter.createNewUser(dbHandler);
        verify(mainPresenter).createNewUser(dbHandler);
    }

    @Test
    public void createNewUser_CheckMainActivityUser_MainActivityUserNotNull() {
        mainPresenter = new MainPresenter(null, null);
        DBHandler dbHandler = mock(DBHandler.class);
        mainPresenter.createNewUser(dbHandler);
        assertNotNull(MainActivity.user);
    }

    @Test(expected = NullPointerException.class)
    public void createNewUser_DBHandlerIsNull_ThrowsNullPointerException() {
        mainPresenter = new MainPresenter(null, null);
        DBHandler dbHandler = null;
        mainPresenter.createNewUser(dbHandler);
    }

    @Test
    public void getUserData_CallsMethod_CalledMethod() {
        mainPresenter.getUserData();
        verify(mainPresenter).getUserData();
    }

    @Test(expected = NullPointerException.class)
    public void getUserData_NoDBHandlerInitialized_ThrowNullPointerException() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.getUserData();
    }

    @Test
    public void parseUserData_CallsMethod_CalledMethod() {
        String[] fakeData = {"0", "1", "2", "3", "4"};
        mainPresenter.parseUserData(fakeData);
        verify(mainPresenter).parseUserData(fakeData);
    }

    @Test
    public void setUser_CallsMethod_CalledMethod() {
        mainPresenter.setUser();
        verify(mainPresenter).setUser();
    }

    @Test
    public void setUser_SetMainActivityUser_MainActivityUserNotNull() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.setUser();
        assertNotNull(MainActivity.user);
    }

    @Test
    public void setCalculations_CallsMethod_CalledMethod() {
        mainPresenter.setCalculations();
        verify(mainPresenter).setCalculations();
    }

    @Test
    public void setCalculations_CallWithCommonDataInput_CostPerDayEqualsExpected() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.cigsPerDay = 10;
        mainPresenter.cigsInPack = 20;
        mainPresenter.cigPackCost = 13.32;
        mainPresenter.setCalculations();
        Assert.assertEquals(6.66, mainPresenter.costPerDay, 0.001);
    }

    @Test
    public void setCalculations_CallWithCommonDataInput_MoneySavedEqualsExpected() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.cigsPerDay = 50;
        mainPresenter.cigsInPack = 25;
        mainPresenter.cigPackCost = 13.32;
        mainPresenter.days = 7;
        mainPresenter.setCalculations();
        Assert.assertEquals(186.48, mainPresenter.moneySaved, 0.001);
    }

    @Test
    public void setCalculations_CallWithCommonDataInput_ExtraLifeDayEqualsExpected() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.cigsPerDay = 5;
        mainPresenter.days = 7;
        mainPresenter.setCalculations();
        Assert.assertEquals(3.5, mainPresenter.extraLife, 0.01);
    }

    @Test
    public void setCalculations_PassInZeroDenominator_CatchArithmeticException() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.cigsPerDay = 25;
        mainPresenter.cigsInPack = 0;
        mainPresenter.cigPackCost = 0;
        mainPresenter.setCalculations();
        Assert.assertEquals(0, mainPresenter.costPerDay, 0.001);
    }

    @Test
    public void resetAllData_CallsMethod_CalledMethod() {
        mainPresenter.resetAllData();
        verify(mainPresenter).resetAllData();
    }

    @Test
    public void resetAllData_SetDaysThenCallMethod_DaysIsZero() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.days = 5;
        mainPresenter.resetAllData();
        Assert.assertEquals(0, mainPresenter.days, 0);
    }

    @Test
    public void resetAllData_SetExtraLifeThenCallMethod_ExtraLifeIsZero() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.extraLife = 120;
        mainPresenter.resetAllData();
        Assert.assertEquals(0, mainPresenter.extraLife, 0);
    }

    @Test
    public void resetUserData_CallsMethod_CalledMethod() {
        mainPresenter.resetUserData();
        verify(mainPresenter).resetUserData();
    }

    @Test
    public void resetUserData_SetCigPackCostThenCallMethod_CigPackCostIsZero() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.cigPackCost = 14.98;
        mainPresenter.resetUserData();
        Assert.assertEquals(0, mainPresenter.cigPackCost, 0);
    }

    @Test
    public void resetUserData_SetCigsInPackThenCallMethod_CigsInPackIsZero() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.cigsInPack = 20;
        mainPresenter.resetUserData();
        Assert.assertEquals(0, mainPresenter.cigsInPack, 0);
    }

    @Test
    public void resetUserData_SetCigsPerDayThenCallMethod_CigsPerDayIsZero() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.cigsPerDay = 30;
        mainPresenter.resetUserData();
        Assert.assertEquals(0, mainPresenter.cigsPerDay, 0);
    }

    @Test
    public void resetUserData_SetDaysThenCallMethod_DaysIsZero() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.days = 345;
        mainPresenter.resetUserData();
        Assert.assertEquals(0, mainPresenter.days, 0);
    }

    @Test
    public void resetCalculations_CallsMethod_CalledMethod() {
        mainPresenter.resetCalculations();
        verify(mainPresenter).resetCalculations();
    }

    @Test
    public void resetCalculations_SetCostPerDayThenCallMethod_CostPerDayIsZero() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.costPerDay = 41.25;
        mainPresenter.resetCalculations();
        Assert.assertEquals(0, mainPresenter.costPerDay, 0);
    }

    @Test
    public void resetCalculations_SetMoneySavedThenCallMethod_MoneySavedIsZero() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.moneySaved = 145.98;
        mainPresenter.resetCalculations();
        Assert.assertEquals(0, mainPresenter.moneySaved, 0);
    }

    @Test
    public void resetCalculations_SetExtraLifeThenCallMethod_ExtraLifeIsZero() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.extraLife = 74.5;
        mainPresenter.resetCalculations();
        Assert.assertEquals(0, mainPresenter.extraLife, 0);
    }

    @Test
    public void updateUser_CallsMethod_CalledMethod() {
        mainPresenter.updateUser();
        verify(mainPresenter).updateUser();
    }

    @Test(expected = NullPointerException.class)
    public void updateUser_DBHandlerIsNull_ThrowsNullPointerException() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.updateUser();
    }

    @Test(expected = NullPointerException.class)
    public void updateUser_SQLDatabaseIsNull_ThrowsNullPointerException() {
        mainPresenter = new MainPresenter(null, null);
        DBHandler dbHandler = new DBHandler(null,null, null, 1);
        mainPresenter.setDBHandler(dbHandler);
        mainPresenter.updateUser();
    }

    @Test
    public void setPresenterData_CallsMethod_CalledMethod() {
        mainPresenter.setPresenterData();
        verify(mainPresenter).setPresenterData();
    }

    @Test(expected = NullPointerException.class)
    public void setPresenterData_DBHandlerIsNull_ThrowsNullPointerException() {
        mainPresenter = new MainPresenter(null, null);
        mainPresenter.setPresenterData();
        assertNotNull(mainPresenter.getUserData());
    }

    @Test
    public void onCheckInButton_CallsMethod_CalledMethod() {
        View view = new View(mainActivity);
        mainPresenter.onCheckInButton(view);
        verify(mainPresenter).onCheckInButton(view);
    }

    @Test(expected = NullPointerException.class)
    public void onCheckInButton_DBHandlerIsNull_ThrowsNullPointerException() {
        mainPresenter = new MainPresenter(null, null);
        View view = new View(mainActivity);
        mainPresenter.onCheckInButton(view);
    }

    @Test(expected = NullPointerException.class)
    public void onCheckInButton_SQLDatabaseIsNull_ThrowsNullPointerException() {
        mainPresenter = new MainPresenter(null, null);
        DBHandler dbHandler = new DBHandler(null, null, null, 1);
        mainPresenter.setDBHandler(dbHandler);
        View view = new View(mainActivity);
        mainPresenter.onCheckInButton(view);
    }

    @Test
    public void onCheckInButton_CallsMethodWithRealObjects_CheckInButtonIsNotEnabled() {
        mainPresenter = new MainPresenter(null, null);
        View view = new View(mainActivity);
        Button button = new Button(null);
        mainPresenter.setCheckInButton(button);

        try {
            mainPresenter.onCheckInButton(view);
        } catch (NullPointerException e) {

        }

        Assert.assertFalse(mainPresenter.checkInButton.isEnabled());
    }

    @Test(expected = NullPointerException.class)
    public void onCheckInButton_ContextIsNull_ThrowsNullPointerException() {
        mainPresenter = new MainPresenter(null, null);
        DBHandler dbHandler = new DBHandler(null, null, null, 1);
        View view = new View(mainActivity);

        String[] userData = new String[]{"0", "0.0", "0", "0", "0"};
        dbHandler.setUserData(userData);

        mainPresenter.setDBHandler(dbHandler);
        mainPresenter.onCheckInButton(view);
    }

}