package iti.jets.mad.tripplanner.screens.loginscreen;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import iti.jets.mad.tripplanner.screens.registerscreen.RegisterActivity;
import iti.jets.mad.tripplanner.screens.registerscreen.RegisterContract;

public class LoginPresenterImpl implements LoginContract.LoginPresenter {

    private FirebaseAuth firebaseAuth;
    private Context context;
    private LoginContract.LoginView loginView;

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
        Intent intent= new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void updateMessage(String message) {
        loginView.showToast(message);
    }
}
