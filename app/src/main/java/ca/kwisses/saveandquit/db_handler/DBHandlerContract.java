package ca.kwisses.saveandquit.db_handler;

import ca.kwisses.saveandquit.user.User;

public interface DBHandlerContract {

    interface Presenter {
        void addUser(User user);

        void deleteUser(User user);

        String[] getUserData();
    }

}
