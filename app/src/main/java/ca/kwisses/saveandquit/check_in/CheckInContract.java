package ca.kwisses.saveandquit.check_in;

import android.view.View;
import android.widget.EditText;

public interface CheckInContract {

    interface MvpView {
        CheckInPresenter getCheckInPresenter();

        void setCheckInPresenter(CheckInPresenter checkInPresenter);

        void onSubmitButton(View view);
    }

    interface Presenter {
        void init(View view);

        boolean isFieldFull(EditText editText);

        boolean allFieldsFull();

        void setUserData();

        void updateDBHander();

        void setCheckInField1(EditText editText);

        void setCheckInField2(EditText editText);

        void setCheckInField3(EditText editText);
    }

}
