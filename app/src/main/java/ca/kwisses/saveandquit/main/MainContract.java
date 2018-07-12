package ca.kwisses.saveandquit.main;

import android.view.View;

import ca.kwisses.saveandquit.db_handler.DBHandler;

public interface MainContract {

    // View interface
    interface MvpView {
        void init();

        void setQuoteText();

        void setDisplayText(double moneySaved, double extraLife);
    }

    // Presenter (handler) interface
    interface Presenter {
        void init(View view);

        void loadUser();

        void setDBHandler();

        DBHandler getDBHandler();

        void getUserData();

        void setMainData();

        void resetData();

        void onCheckInButton(View view);
    }
}
