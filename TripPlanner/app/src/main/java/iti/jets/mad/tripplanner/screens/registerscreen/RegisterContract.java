package iti.jets.mad.tripplanner.screens.registerscreen;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface RegisterContract {
    interface RegisterView {
        void showToast(String message);
    }

    interface RegisterPresenter {
        void register(String password , String email);
        void toLoginActivity();
        void toHomeActivity();
        Intent googleTooken();
        void firebaseAuthWithGoogle(GoogleSignInAccount account);
        void updateMessage(String message);
        void activityResult(int requestCode,Intent data);
    }
}
