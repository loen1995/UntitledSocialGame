package com.gimbernat.UntitledSocialGame.DataSources;

import androidx.annotation.NonNull;

import com.gimbernat.UntitledSocialGame.Entities.UserEntity;
import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.Helpers.GetUserCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

interface IUserDataSource {
    void createUserWith(UserEntity user, GetUserCallback callback);
    void getUserData(GetUserCallback callback);
    UserEntity snapshotToUserEntity(DataSnapshot item_snapshot);
}

public class UserDataSource implements IUserDataSource {

    public static UserDataSource shared = new UserDataSource();

    private UserEntity currentUser;

    @Override
    public void createUserWith(final UserEntity user, final GetUserCallback callback)
    {


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUserID());
        Map<String, Object> eventUpdate = new HashMap<>();
        eventUpdate.put("Nickname", user.getNickname());

        mDatabase.updateChildren(eventUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    UserDataSource.this.currentUser = user;
                    callback.onSuccess(user);
                }else{
                    callback.onError("can't write user into database");
                }
            }
        });


    }

    @Override
    public void getUserData(final GetUserCallback callback)
    {

        if(currentUser != null) {
            callback.onSuccess(currentUser);
        }else {
            //retrieve from database
            //hacer un onComplete()
//            UserDataSource.this.currentUser = user;
//            callback.onSuccess(user);

            SessionDataSource.shared.getCurrentUser(new Callback() {
                @Override
                public void onSuccess(Object responseObject) {
                    FirebaseUser currentUser = (FirebaseUser) responseObject;
                    String userID = currentUser.getUid();

                    final DatabaseReference databaseReference =
                            FirebaseDatabase.getInstance().getReference().child("users/" + userID);

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            databaseReference.removeEventListener(this);
                            //call snapshot
                            //success callback
                            UserDataSource.this.currentUser = snapshotToUserEntity(dataSnapshot);
                            callback.onSuccess(UserDataSource.this.currentUser);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            callback.onError("No user found");
                        }
                    });
                }

                @Override
                public void onError() {

                }
            });

        }
    }

    @Override
    public UserEntity snapshotToUserEntity(DataSnapshot item_snapshot)
    {
        String id = item_snapshot.getKey().toString();
        String nickname = item_snapshot.child("nickname").exists() ? item_snapshot.child("nickname").getValue().toString() : "";


        return new UserEntity(id, nickname);
    }
}
