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
    public boolean isFieldFull(EditText editText) {
        if(editText.getText().toString().length() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean allFieldsFull() {
        if(!isFieldFull(checkInField1) ||
                !isFieldFull(checkInField2 ) ||
                !isFieldFull(checkInField3)) {
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
