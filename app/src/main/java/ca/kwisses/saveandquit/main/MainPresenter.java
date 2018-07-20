package ca.kwisses.saveandquit.main;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import java.util.Arrays;
import java.util.Random;

import ca.kwisses.saveandquit.check_in.CheckInActivity;
import ca.kwisses.saveandquit.db_handler.DBHandler;
import ca.kwisses.saveandquit.R;
import ca.kwisses.saveandquit.user.User;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.MvpView mvpView;

    private Context context;

    private DBHandler dbHandler;

    // Initial variables
    TextView quoteText;
    TextView moneySavedField;
    TextView extraLifeField;
    Button checkInButton;

    // User input data
    private String[] userData;
    double cigPackCost;
    int cigsInPack;
    int cigsPerDay;
    int days;

    // Calculated user data
    double costPerDay;
    double moneySaved;
    double extraLife;

    // Time (in minutes) it takes to smoke 1 cigarette
    static final int SMOKING_TIME = 6;

    MainPresenter(MainContract.MvpView view, Context context) {
        mvpView = view;
        this.context = context;
    }

    @Override
    public void init(View view) {
        quoteText = (TextView) view.findViewById(R.id.quoteText);
        moneySavedField = (TextView) view.findViewById(R.id.moneySavedField);
        extraLifeField = (TextView) view.findViewById(R.id.extraLifeField);
        checkInButton = (Button) view.findViewById(R.id.checkInButton);
    }

    @Override
    public String getQuoteTextFromFile() {
        String[] array = context.getResources().getStringArray(R.array.quotes);
        int n = new Random().nextInt(array.length);
        return array[n];
    }

    @Override
    public void loadUser(DBHandler dbHandler) {
        String[] nullArray = {null, null, null, null, null};
        int[] intArray = {0, 0, 0, 0, 0};

        if(Arrays.equals(dbHandler.getUserData(), nullArray)) {
            MainActivity.user = new User(intArray);
            dbHandler.addUser(MainActivity.user);
        } else {
            getUserData();
            MainActivity.user = new User(0, cigPackCost, cigsInPack, cigsPerDay, days);
            setMainData();
        }
    }

    @Override
    public void setDBHandler(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Override
    public DBHandler getDBHandler() {
        return dbHandler;
    }

    @Override
    public void getUserData() {
        userData = dbHandler.getUserData();
        cigPackCost = Double.parseDouble(userData[1]);
        cigsInPack = Integer.parseInt(userData[2]);
        cigsPerDay = Integer.parseInt(userData[3]);
        days = Integer.parseInt(userData[4]);
    }

    @Override
    public void setMainData() {
        try {
            costPerDay = cigsPerDay / (float) cigsInPack * cigPackCost;
            if(Double.isNaN(costPerDay)) {
                costPerDay = 0.0;
            }
            moneySaved = costPerDay * days;
            extraLife = (cigsPerDay * (SMOKING_TIME / 60.0f) * days);
        } catch (ArithmeticException e) {
            costPerDay = 0;
            moneySaved = 0;
            extraLife = 0;
        }
    }

    @Override
    public void resetData() {
        cigPackCost = 0;
        cigsInPack = 0;
        cigsPerDay = 0;
        days = 0;
        costPerDay = 0;
        moneySaved = 0;
        extraLife = 0;
    }

    @Override
    public void onCheckInButton(View view) {
        String[] strArray = {"0", "0.0", "0", "0", "0"};

        if(!Arrays.equals(dbHandler.getUserData(), strArray)) {
            Toast.makeText(context, "Successfully checked in!", Toast.LENGTH_LONG).show();

            dbHandler.deleteUser(MainActivity.user);
            MainActivity.user = new User(0, cigPackCost, cigsInPack, cigsPerDay, days + 1);
            dbHandler.addUser(MainActivity.user);

            getUserData();
            setMainData();
            mvpView.setDisplayText(moneySaved, extraLife);

            checkInButton.setEnabled(false);
        } else {
            Intent i = new Intent(context, CheckInActivity.class);
            context.startActivity(i);
        }
    }
}
