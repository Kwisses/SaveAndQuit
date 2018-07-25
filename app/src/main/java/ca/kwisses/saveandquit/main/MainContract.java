package ca.kwisses.saveandquit.main;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ca.kwisses.saveandquit.db_handler.DBHandler;

public interface MainContract {

    // View interface
    interface MvpView {
        void init(MainPresenter mainPresenter);

        void createDBHandler();

        void setDBHandler(DBHandler dbHandler);

        void createMainPresenter(View view);

        void setMainPresenter(MainPresenter mainPresenter);

        void setQuoteText(String text);

        void setDisplayText(double moneySaved, double extraLife);

        void handleAboutMenuItem();

        void handleDeleteMenuItem();

        void setDeleteUserOnClickListener(AlertDialog dialog, Button button);

        void onCheckInButton(View view);
    }

    // Presenter (handler) interface
    interface Presenter {
        void init(View view);

        String getQuoteTextFromFile();

        void loadUser(DBHandler dbHandler);

        void createNewUser(DBHandler dbHandler);

        String[] getUserData();

        void parseUserData(String[] userData);

        void setUser();

        void setCalculations();

        void resetAllData();

        void resetUserData();

        void resetCalculations();

        void updateUser();

        void setPresenterData();

        void onCheckInButton(View view);

        DBHandler getDBHandler();

        void setDBHandler(DBHandler dbHandler);

        void setUserData(String[] userData);

        void setQuoteText(TextView textView);

        void setMoneySavedField(TextView textView);

        void setExtraLifeField(TextView textView);

        void setCheckInButton(Button button);

        void setCigPackCost(double cigPackCost);

        void setCigsInPack(int cigsInPack);

        void setCigsPerDay(int cigsPerDay);

        void setDays(int days);
    }
}
