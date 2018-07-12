package ca.kwisses.saveandquit.splash_screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ca.kwisses.saveandquit.R;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenContract.MvpView {

    private SplashScreenPresenter splashScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashScreenPresenter = new SplashScreenPresenter();

        try {
            splashScreenPresenter.run(this);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
