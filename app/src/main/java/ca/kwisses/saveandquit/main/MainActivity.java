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

import ca.kwisses.saveandquit.R;
import ca.kwisses.saveandquit.db_handler.DBHandler;
import ca.kwisses.saveandquit.user.User;

public class MainActivity extends AppCompatActivity implements MainContract.MvpView {

    public static MainPresenter mainPresenter;

    public DBHandler dbHandler;

    // UI Elements
    private TextView quoteText;
    private TextView moneySavedField;
    private TextView extraLifeField;
    private Context context;
    public View view;
    public Menu menu;

    // Constants
    private final int[] intArray = {1, 0, 0, 0, 0};

    // App Objects
    public static User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        view = findViewById(android.R.id.content);

        // Set presenter calls
        createDBHandler();
        createMainPresenter(view);
        setMainPresenter(mainPresenter);

        // View method calls
        init(mainPresenter);
        setQuoteText(mainPresenter.getQuoteTextFromFile());
        setDisplayText(mainPresenter.moneySaved, mainPresenter.extraLife);
    }

    @Override
    public void init(MainPresenter mainPresenter) {
        quoteText = mainPresenter.quoteText;
        moneySavedField = mainPresenter.moneySavedField;
        extraLifeField = mainPresenter.extraLifeField;
    }

    @Override
    public void createDBHandler() {
        dbHandler = new DBHandler(context, null, null, 1);
    }

    @Override
    public void setDBHandler(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Override
    public void createMainPresenter(View view) {
        mainPresenter = new MainPresenter(this, this);
        mainPresenter.setDBHandler(dbHandler);
        mainPresenter.init(view);
    }

    @Override
    public void setMainPresenter(MainPresenter mainPresenter) {
        mainPresenter.loadUser();
        mainPresenter.getUserData();
    }

    @Override
    public void setQuoteText(String text) {
        quoteText.setText(text);
    }

    @Override
    public void setDisplayText(double moneySaved, double extraLife) {
        moneySavedField.setText("$" + String.format("%.2f", moneySaved));
        extraLifeField.setText(String.format("%.1f", extraLife) + " Hrs");
    }

    @Override
    public void onCheckInButton(View view) {
        mainPresenter.onCheckInButton(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void handleAboutMenuItem() {
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

    @Override
    public void handleDeleteMenuItem() {
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

        setDeleteUserOnClickListener(dialog, confirmButton);
    }

    @Override
    public void setDeleteUserOnClickListener(AlertDialog dialog, Button button) {
        final AlertDialog dialog1 = dialog;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, R.string.deleted_user, Toast.LENGTH_LONG).show();

                mainPresenter.getDBHandler().deleteUser(user);
                user = new User(intArray);
                mainPresenter.getDBHandler().addUser(user);

                mainPresenter.resetAllData();
                setDisplayText(mainPresenter.moneySaved, mainPresenter.extraLife);

                mainPresenter.checkInButton.setEnabled(true);
                dialog1.dismiss();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.about) {
            handleAboutMenuItem();
        }
        if(item.getItemId() == R.id.delete_user) {
            handleDeleteMenuItem();
        }
        return super.onOptionsItemSelected(item);
    }

    // Getters and Setters

    public TextView getQuoteText() {
        return quoteText;
    }

    public TextView getMoneySavedField() {
        return moneySavedField;
    }

    public TextView getExtraLifeField() {
        return extraLifeField;
    }
}
