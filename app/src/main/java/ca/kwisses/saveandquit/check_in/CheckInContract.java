package ca.kwisses.saveandquit.check_in;

import android.view.View;

public interface CheckInContract {

    interface MvpView {
        void onSubmitButton(View view);
    }

    interface Presenter {
        void init(View view);

        boolean allFieldsFull();

        void setUserData();

        void updateDBHander();
    }

}
