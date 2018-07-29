package ca.kwisses.saveandquit.db_handler;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import ca.kwisses.saveandquit.user.User;

public interface DBHandlerContract {

    interface Presenter {

        boolean executeQuery(SQLiteDatabase db, String query);

        ContentValues getContentValues(User user);

        void addUser(User user);

        void deleteUser(User user);

        String[] getUserData();

        String[] getUserDataFromDatabase();

        void setUserData(String[] userData);
    }

}
