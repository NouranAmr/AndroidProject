package iti.jets.mad.tripplanner.screens.loginscreen;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface LoginContract {
    interface LoginView {
        void showToast(String message);
    }

    interface LoginPresenter {
        void login(String username,String password);
        void toHomeActivity();
        void updateMessage(String message);

    }
}
