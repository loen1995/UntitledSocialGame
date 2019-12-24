package com.gimbernat.UntitledSocialGame.DataSources;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.Helpers.RegisterCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SessionDataSource {

    public static SessionDataSource shared = new SessionDataSource();

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public void getCurrentUser(final Callback callback) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        callback.onSuccess(currentUser);
    }

    public  Boolean isUserLogedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public void SignIn(final Callback callback) {
        FirebaseAuth.getInstance().signOut();
        /*FirebaseAuth.getInstance().signInWithEmailAndPassword("email","pass")*/
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess(FirebaseAuth.getInstance().getCurrentUser());
                }else {
                    callback.onError();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onError();
            }
        });

    }


    public void signInEmailAndPassword(final String mail, final String pass, final Callback callback) {
        FirebaseAuth.getInstance().signOut();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    /*ir a bbdd y crear*/
                    callback.onSuccess(FirebaseAuth.getInstance().getCurrentUser());
                }else {
                    callback.onError();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onError();
            }
        });

    }

    public void resetWithEmail(final String email, final Callback callback)
    {
        callback.onSuccess(null);
    }

    public void RegisterUser (final String email, final String password, final RegisterCallback callback)
    {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess(FirebaseAuth.getInstance().getCurrentUser());
                        } else {
                            callback.onError(task.getException().getLocalizedMessage());
                        }
                    }
                });

    }

    //funcion que dice si la session esta iniciada o no(falta por hacer)
    public void isSessionActive()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }
    }
}
