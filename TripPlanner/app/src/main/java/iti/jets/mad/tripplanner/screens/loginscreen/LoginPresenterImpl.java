package iti.jets.mad.tripplanner.screens.loginscreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import iti.jets.mad.tripplanner.screens.homescreen.HomeActivity;
import iti.jets.mad.tripplanner.screens.registerscreen.RegisterActivity;
import iti.jets.mad.tripplanner.screens.registerscreen.RegisterContract;

public class LoginPresenterImpl implements LoginContract.LoginPresenter {

    private FirebaseAuth firebaseAuth;
    private Context context;
    private LoginContract.LoginView loginView;
    private static final String SETTING_INFOS = "User_Info";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "PASSWORD";

    public LoginPresenterImpl(LoginContract.LoginView loginView) {

        this.loginView = loginView;
        firebaseAuth=FirebaseAuth.getInstance();
        context= (Context) loginView;

    }


    @Override
    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            updateMessage("sign in Successfully");
                            toHomeActivity();
                            sharedPreferences(email,password);
                            clearViewTxt();

                        }
                        else
                        {
                            updateMessage("sign in Failed");

                        }
                    }
                });
    }

    @Override
    public void toHomeActivity() {
        Intent intent= new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void updateMessage(String message) {
        loginView.showToast(message);
    }

    @Override
    public void clearViewTxt() {
        loginView.clearTxt();
    }
    @Override
    public void sharedPreferences(String email, String password) {
        SharedPreferences settings = context.getSharedPreferences(SETTING_INFOS, 0);
        settings.edit()
                .putString(EMAIL, email)
                .putString(PASSWORD,password)
                .commit();
    }

    @Override
    public void getSharedPreferences() {
        SharedPreferences settings = context.getSharedPreferences(SETTING_INFOS, 0);
        if(settings.contains(EMAIL))
        {
            String name = settings.getString(EMAIL, "");
            String Password = settings.getString(PASSWORD, "");
            context.startActivity(new Intent(context,HomeActivity.class));
        }
    }
}
