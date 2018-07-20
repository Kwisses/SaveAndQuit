package ca.kwisses.saveandquit.main;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

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

        void setDBHandler(DBHandler dbHandler);

        DBHandler getDBHandler();

        void getUserData();

        void setMainData();

        void resetData();

        void onCheckInButton(View view);
    }
}
