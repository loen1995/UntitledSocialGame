package com.gimbernat.UntitledSocialGame.DataSources;

import androidx.annotation.NonNull;

import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.Helpers.CreateEventCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

interface IEventDataSource {
    void createEvent(String name,
                     String description,
                     double latitude,
                     double longitude,
                     CreateEventCallback callback);
}

public class EventDataSource implements IEventDataSource {

    public static EventDataSource shared = new EventDataSource();

    public void createEvent(
            final String name,
            final String description,
            final double latitude,
            final double longitude,
            final CreateEventCallback callback
    ) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("events").child(UUID.randomUUID().toString());
        Map<String, Object> eventUpdate = new HashMap<>();
        eventUpdate.put("name", name);
        eventUpdate.put("description", description);
        eventUpdate.put("latitude", latitude);
        eventUpdate.put("longitude", longitude);

        mDatabase.updateChildren(eventUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
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
