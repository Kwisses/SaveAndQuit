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

        boolean isStringFull(String string);

        boolean allFieldsFull();

        boolean isStringInRange(String string);

        boolean allFieldsInRange();

        boolean isStringInputValid(String string);

        boolean allFieldInputsValid();

        void setUserData();

        void updateDBHander();

        void setCheckInField1(EditText editText);

        void setCheckInField2(EditText editText);

        void setCheckInField3(EditText editText);
    }

}
