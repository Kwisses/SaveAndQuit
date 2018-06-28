package ca.kwisses.saveandquit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CheckIn extends AppCompatActivity {

    private EditText checkInField1;
    private EditText checkInField2;
    private EditText checkInField3;

    private double cigPackCost;
    private int cigsInPack;
    private int cigsPerDay;
    private int days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
    }

    public void onSubmitButton(View view) {
        checkInField1 = (EditText) findViewById(R.id.checkInField1);
        checkInField2 = (EditText) findViewById(R.id.checkInField2);
        checkInField3 = (EditText) findViewById(R.id.checkInField3);

        if(checkInField1.getText().length() == 0 ||
                checkInField2.getText().length() == 0 ||
                checkInField3.getText().length() == 0) {
            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this, "Created a new user account!", Toast.LENGTH_LONG).show();

        cigPackCost = Double.parseDouble(checkInField1.getText().toString());
        cigsInPack = Integer.parseInt(checkInField2.getText().toString());
        cigsPerDay = Integer.parseInt(checkInField3.getText().toString());
        days = MainActivity.user.getDays();

        DBHandler dbHandler = MainActivity.dbHandler;
        dbHandler.deleteUser(MainActivity.user); // delete user
        MainActivity.user = new User(0, cigPackCost, cigsInPack, cigsPerDay, days);
        dbHandler.addUser(MainActivity.user);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
