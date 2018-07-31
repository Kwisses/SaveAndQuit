package ca.kwisses.saveandquit.splash_screen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import ca.kwisses.saveandquit.main.MainActivity;

public class SplashScreenPresenter implements SplashScreenContract.Presenter {

    Thread thread;

    @Override
    public void run(final Context context) {
        thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    public Thread getThread() {
        return thread;
    }
}
