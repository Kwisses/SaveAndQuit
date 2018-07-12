package ca.kwisses.saveandquit.splash_screen;

import android.content.Context;

public interface SplashScreenContract {

    interface MvpView {
        // nothing here atm
    }

    interface Presenter {
        void run(Context context) throws InterruptedException;
    }

}
