package ca.kwisses.saveandquit.check_in;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import ca.kwisses.saveandquit.R;
import ca.kwisses.saveandquit.db_handler.DBHandler;
import ca.kwisses.saveandquit.main.MainActivity;
import ca.kwisses.saveandquit.user.User;

public class CheckInPresenter implements CheckInContract.Presenter {

    private EditText checkInField1;
    private EditText checkInField2;
    private EditText checkInField3;

    private double cigPackCost;
    private int cigsInPack;
    private int cigsPerDay;
    private int days;

    Context context;

    CheckInPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void init(View view) {
        setCheckInField1((EditText) view.findViewById(R.id.checkInField1));
        setCheckInField2((EditText) view.findViewById(R.id.checkInField2));
        setCheckInField3((EditText) view.findViewById(R.id.checkInField3));
    }

    @Override
    public boolean isStringFull(String string) {
        if(string.length() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean allFieldsFull() {
        if(!isStringFull(checkInField1.getText().toString()) ||
                !isStringFull(checkInField2.getText().toString()) ||
                !isStringFull(checkInField3.getText().toString())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isStringInRange(String string) {
        double fieldValue = Double.parseDouble(string);
        if(fieldValue >= 1 && fieldValue <= 100) { // hard-coded range
            return true;
        }
        return false;
    }

    @Override
    public boolean allFieldsInRange() {
        if(!isStringInRange(checkInField1.getText().toString()) ||
                !isStringInRange(checkInField2.getText().toString()) ||
                !isStringInRange(checkInField3.getText().toString())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isStringInputValid(String string) {
        // If user inputs a space.
        if(string.length() > 0 && string.indexOf(' ') != -1) {
            return false;
        }

        // If user inputs only a period. (".")
        if(string.length() == 1 && string.indexOf('.') == 0) {
            return false;
        }

        // If user inputs a unusable character.
        if(string.indexOf(',') != -1 ||
                string.indexOf('-') != -1 ||
                string.indexOf('_') != -1) {
            return false;
        }

        return true;
    }

    @Override
    public boolean allFieldInputsValid() {
        if(!isStringInputValid(checkInField1.getText().toString()) ||
                !isStringInputValid(checkInField2.getText().toString()) ||
                !isStringInputValid(checkInField3.getText().toString())) {
            return false;
        }
        return true;
    }

    @Override
    public void setUserData() {
        try {
            cigPackCost = Double.parseDouble(checkInField1.getText().toString());
            cigsInPack = Integer.parseInt(checkInField2.getText().toString());
            cigsPerDay = Integer.parseInt(checkInField3.getText().toString());
            days = MainActivity.user.getDays();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    @Override
    public void updateDBHander() {
        DBHandler dbHandler;

        try {
            dbHandler = MainActivity.mainPresenter.getDBHandler();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }

        dbHandler.deleteUser(MainActivity.user); // delete user
        MainActivity.user = new User(1, cigPackCost, cigsInPack, cigsPerDay, days);
        dbHandler.addUser(MainActivity.user);
    }

    @Override
    public void setCheckInField1(EditText editText) {
        this.checkInField1 = editText;
    }

    @Override
    public void setCheckInField2(EditText editText) {
        this.checkInField2 = editText;
    }

    @Override
    public void setCheckInField3(EditText editText) {
        this.checkInField3 = editText;
    }
}
