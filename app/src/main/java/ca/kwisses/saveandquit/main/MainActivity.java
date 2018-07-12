package ca.kwisses.saveandquit.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import ca.kwisses.saveandquit.R;
import ca.kwisses.saveandquit.user.User;

public class MainActivity extends AppCompatActivity implements MainContract.MvpView {

    public static MainPresenter mainPresenter;

    // UI Elements
    private static TextView quoteText;
    private static TextView moneySavedField;
    private static TextView extraLifeField;
    private Context context;
    public Menu menu;

    // Constants
    private final int[] intArray = {0, 0, 0, 0, 0};

    // App Objects
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        // Presenter method calls
        mainPresenter = new MainPresenter(this, this);
        mainPresenter.setDBHandler();
        mainPresenter.init(findViewById(android.R.id.content));
        mainPresenter.loadUser();
        mainPresenter.getUserData();

        // View method calls
        init();
        setQuoteText();
        setDisplayText(mainPresenter.moneySaved, mainPresenter.extraLife);
    }

    @Override
    public void init() {
        quoteText = mainPresenter.quoteText;
        moneySavedField = mainPresenter.moneySavedField;
        extraLifeField = mainPresenter.extraLifeField;
    }

    public void setQuoteText() {
        String[] array = this.getResources().getStringArray(R.array.quotes);
        int n = new Random().nextInt(array.length);
        quoteText.setText(array[n]);
    }

    public void setDisplayText(double moneySaved, double extraLife) {
        moneySavedField.setText("$" + String.format("%.2f", moneySaved));
        extraLifeField.setText(String.format("%.1f", extraLife) + " Hrs");
    }

    public void onCheckInButton(View view) {
        mainPresenter.onCheckInButton(view);
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
            Button cancelButton = view.findViewById(R.id.cancelButton);
            Button confirmButton = view.findViewById(R.id.confirmButton);

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

                    mainPresenter.getDBHandler().deleteUser(user);
                    user = new User(intArray);
                    mainPresenter.getDBHandler().addUser(user);

                    mainPresenter.resetData();
                    setDisplayText(mainPresenter.moneySaved, mainPresenter.extraLife);

                    mainPresenter.checkInButton.setEnabled(true);
                    dialog.dismiss();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}
