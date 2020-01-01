package com.gimbernat.UntitledSocialGame.DataSources;

import androidx.annotation.NonNull;

import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.Helpers.CreateEventCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

interface IEventDataSource {
    void createEvent(String name,
                     String description,
                     String latitude,
                     String longitude,
                     CreateEventCallback callback);
}

public class EventDataSource implements IEventDataSource {

    public static EventDataSource shared = new EventDataSource();

    public void createEvent(
            final String name,
            final String description,
            final String latitude,
            final String longitude,
            final CreateEventCallback callback
    ) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("events").child(name);

        mDatabase.child("description").push().setValue(description);
        mDatabase.child("latitude").push().setValue(latitude);
        mDatabase.child("longitude").push().setValue(longitude).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess();
                } else {
                    callback.onError("can't write user into database");
                }
            }
        });
    }
}
