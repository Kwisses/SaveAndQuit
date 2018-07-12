package ca.kwisses.saveandquit.check_in;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import ca.kwisses.saveandquit.R;
import ca.kwisses.saveandquit.main.MainActivity;

public class CheckInActivity extends AppCompatActivity implements CheckInContract.MvpView {

    private CheckInPresenter checkInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        checkInPresenter = new CheckInPresenter(this, this);
    }

    public void onSubmitButton(View view) {
        checkInPresenter.init(findViewById(android.R.id.content));

        if(checkInPresenter.allFieldsFull()) {
            Toast.makeText(this, "Created a new user account!", Toast.LENGTH_LONG).show();
            checkInPresenter.setUserData();
            checkInPresenter.updateDBHander();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_LONG).show();
        }
    }
}
