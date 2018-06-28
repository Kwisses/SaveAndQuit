package ca.kwisses.saveandquit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // UI Elements
    private TextView quoteText;
    private TextView moneySavedField;
    private TextView extraLifeField;
    private Button checkInButton;
    private Context context;

    // Constants
    private final String[] nullArray = {null, null, null, null, null};
    private final int[] intArray = {0, 0, 0, 0, 0};
    private final String[] strArray = {"0", "0.0", "0", "0", "0"};
    private final int SMOKING_TIME = 6; // in minutes

    // App Objects
    protected static User user;
    protected static DBHandler dbHandler;

    // User Data variables
    private String[] userData;
    private double cigPackCost;
    private int cigsInPack;
    private int cigsPerDay;

    private double costPerDay;
    private int days;

    private double moneySaved;
    private double extraLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Object references
        quoteText = (TextView) findViewById(R.id.quoteText);
        moneySavedField = (TextView) findViewById(R.id.moneySavedField);
        extraLifeField = (TextView) findViewById(R.id.extraLifeField);
        checkInButton = (Button) findViewById(R.id.checkInButton);
        context = this;

        // Database handler object
        dbHandler = new DBHandler(this, null, null, 1);

        // If user is not found, if it is.
        if(Arrays.equals(dbHandler.getUserData(), nullArray)) {
            user = new User(intArray);
            dbHandler.addUser(user);
        } else {
            getUserData();
            user = new User(0, cigPackCost, cigsInPack, cigsPerDay, days);
            setMainData();
        }

        setQuoteText();
        setDisplayText();
    }

    private void setQuoteText() {
        String[] array = context.getResources().getStringArray(R.array.quotes);
        int n = new Random().nextInt(10); // hard coded number of quotes.
        quoteText.setText(array[n]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.about) {
            // Get layout references
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            View view = getLayoutInflater().inflate(R.layout.about, null);

            // Set builder
            builder.setView(view);
            final AlertDialog dialog = builder.create();
            dialog.show();

            // Touch listener
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dialog.dismiss();
                    return true;
                }
            });
        }
        if(item.getItemId() == R.id.delete_user) {
            // Get layout references
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            View view = getLayoutInflater().inflate(R.layout.delete_user, null);
            Button cancelButton = (Button) view.findViewById(R.id.cancelButton);
            Button confirmButton = (Button) view.findViewById(R.id.confirmButton);

            // Set builder
            builder.setView(view);
            final AlertDialog dialog = builder.create();
            dialog.show();

            // Button listeners
            cancelButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            // Deletes user
            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Deleted user!", Toast.LENGTH_LONG).show();

                    dbHandler.deleteUser(user);
                    user = new User(intArray);
                    dbHandler.addUser(user);

                    resetData();
                    setDisplayText();

                    checkInButton.setEnabled(true);
                    dialog.dismiss();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCheckInButton(View view) {
        if(!Arrays.equals(dbHandler.getUserData(), strArray)) {
            Toast.makeText(this, "Successfully checked in!", Toast.LENGTH_LONG).show();

            dbHandler.deleteUser(user);
            user = new User(0, cigPackCost, cigsInPack, cigsPerDay, days + 1);
            dbHandler.addUser(user);

            getUserData();
            setMainData();
            setDisplayText();

            checkInButton.setEnabled(false);
        } else {
            Intent i = new Intent(this, CheckIn.class);
            startActivity(i);
        }
    }

    public void getUserData() {
        userData = dbHandler.getUserData();
        cigPackCost = Double.parseDouble(userData[1]);
        cigsInPack = Integer.parseInt(userData[2]);
        cigsPerDay = Integer.parseInt(userData[3]);
        days = Integer.parseInt(userData[4]);
    }

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

    public void resetData() {
        cigPackCost = 0;
        cigsInPack = 0;
        cigsPerDay = 0;
        days = 0;
        costPerDay = 0;
        moneySaved = 0;
        extraLife = 0;
    }

    public void setDisplayText() {
        moneySavedField.setText("$" + String.format("%.2f", moneySaved));
        extraLifeField.setText(String.format("%.1f", extraLife) + " Hrs");
    }
}
