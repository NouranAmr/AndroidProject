package iti.jets.mad.tripplanner.screens.registerscreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import iti.jets.mad.tripplanner.R;
import iti.jets.mad.tripplanner.screens.homescreen.HomeActivity;
import iti.jets.mad.tripplanner.screens.loginscreen.LoginActivity;

public class RegisterPresenterImpl implements RegisterContract.RegisterPresenter{

    private static final int RC_SIGN_IN =9001 ;
    private RegisterContract.RegisterView registerView;
    private static final String TAG = "RegisterActivity";
    private FirebaseAuth firebaseAuth;
    private Context context;
    private static final String SETTING_INFOS = "User_Info";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "PASSWORD";
    private SharedPreferences settings;

    private GoogleSignInClient mGoogleSignInClient;

    public RegisterPresenterImpl(RegisterContract.RegisterView registerView) {
        this.registerView = registerView;
        firebaseAuth=FirebaseAuth.getInstance();
        context= (Context) registerView;
    }

    @Override
    public void register( String password, String email) {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                           @Override
                                           public void onComplete(@NonNull Task<AuthResult> task) {
                                               if(task.isSuccessful())
                                               {
                                                   updateMessage("Registerd Suceesfully");
                                                   toHomeActivity();
                                                   sharedPreferences(email,password,false);
                                                   Log.i(TAG,task.getResult().toString());
                                               }
                                               else
                                               {
                                                   updateMessage("Registerd failed");
                                                   Log.i(TAG,task.getResult().toString());
                                               }

                                           }
                                       }
                );

    }

    @Override
    public void toLoginActivity() {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void toHomeActivity() {

        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public Intent googleTooken() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
        firebaseAuth = FirebaseAuth.getInstance();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        return signInIntent;
    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) registerView, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            //Intent intent= new Intent(context, NoteList.class);
                           // context.startActivity(intent);
                            updateMessage("Login with google Sucessfully");
                            Intent intent = new Intent(context, HomeActivity.class);
                            context.startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            updateMessage("Login with google Failes");
                        }

                    }
                });
    }

    @Override
    public void updateMessage(String message) {
        registerView.showToast(message);
    }

    @Override
    public void activityResult(int requestCode, Intent data) {


        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    @Override
    public void sharedPreferences(String email, String password,boolean flag) {
        settings = context.getSharedPreferences(SETTING_INFOS, 0);
        if(!flag) {
            settings.edit()
                    .putString(EMAIL, email)
                    .putString(PASSWORD, password)
                    .commit();
        }
        else
        {
            settings.edit()
                    .putString(EMAIL, null)
                    .putString(PASSWORD, null)
                    .commit();
        }
    }

    @Override
    public void getSharedPreferences() {
        settings = context.getSharedPreferences(SETTING_INFOS, 0);
        if(settings.contains(EMAIL))
        {
            String name = settings.getString(EMAIL, "");
            String Password = settings.getString(PASSWORD, "");
            context.startActivity(new Intent(context,HomeActivity.class));
        }
    }

    @Override
    public boolean validateEmail(String email) {
        if(email.isEmpty())
        {
            updateErrorEmail("Field cannot be empty");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            updateErrorEmail("Please enter a valid email");
            return false;
        }
        else
        {
            updateErrorEmail(null);
            return true;
        }
    }

    @Override
    public void updateErrorEmail(String message) {
        registerView.errorEmail(message);
    }

    @Override
    public boolean validatePassword(String password) {
        if (password.isEmpty()) {
            updateErrorPassword("Field can't be empty");
            return false;
        } else {
            updateErrorPassword(null);
            return true;
        }
    }

    @Override
    public boolean validateUsername(String userName) {
        if (userName.isEmpty()) {
            updateErrorUsername("Field can't be empty");
            return false;
        } else if (userName.length() > 15) {
            updateErrorUsername("Username too long");
            return false;
        } else {
            updateErrorUsername(null);
            return true;
        }
    }

    @Override
    public void updateErrorUsername(String userName) {
        registerView.errorUserName(userName);
    }
    @Override
    public void updateErrorPassword(String message) {
        registerView.errorPassword(message);
    }

}
