package ca.kwisses.saveandquit.check_in;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import ca.kwisses.saveandquit.R;
import ca.kwisses.saveandquit.db_handler.DBHandler;
import ca.kwisses.saveandquit.main.MainActivity;
import ca.kwisses.saveandquit.user.User;

public class CheckInPresenter implements CheckInContract.Presenter {

    private CheckInContract.MvpView mvpView;

    private EditText checkInField1;
    private EditText checkInField2;
    private EditText checkInField3;

    private double cigPackCost;
    private int cigsInPack;
    private int cigsPerDay;
    private int days;

    Context context;

    CheckInPresenter(CheckInContract.MvpView view, Context context) {
        mvpView = view;
        this.context = context;
    }

    @Override
    public void init(View view) {
        checkInField1 = (EditText) view.findViewById(R.id.checkInField1);
        checkInField2 = (EditText) view.findViewById(R.id.checkInField2);
        checkInField3 = (EditText) view.findViewById(R.id.checkInField3);
    }

    @Override
    public boolean allFieldsFull() {
        if(checkInField1.getText().length() == 0 ||
                checkInField2.getText().length() == 0 ||
                checkInField3.getText().length() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public void setUserData() {
        cigPackCost = Double.parseDouble(checkInField1.getText().toString());
        cigsInPack = Integer.parseInt(checkInField2.getText().toString());
        cigsPerDay = Integer.parseInt(checkInField3.getText().toString());
        days = MainActivity.user.getDays();
    }

    @Override
    public void updateDBHander() {
        DBHandler dbHandler = MainActivity.mainPresenter.getDBHandler();
        dbHandler.deleteUser(MainActivity.user); // delete user
        MainActivity.user = new User(1, cigPackCost, cigsInPack, cigsPerDay, days);
        dbHandler.addUser(MainActivity.user);
    }
}
