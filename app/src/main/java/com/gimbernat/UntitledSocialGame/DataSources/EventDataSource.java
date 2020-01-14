package com.gimbernat.UntitledSocialGame.DataSources;

import androidx.annotation.NonNull;

import com.gimbernat.UntitledSocialGame.Entities.EventEntity;
import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.Helpers.CreateEventCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
    private ArrayList<EventEntity> eventList = new ArrayList<EventEntity>();

    public void fetchAll(final Callback callback) {
        this.fetch(true, callback);
    }


    private void fetch(final  Boolean subscribeCallback, final Callback callback){

        final DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference().child("events");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<EventEntity> assetsList = new ArrayList<EventEntity>();

                for (DataSnapshot item_snapshot : dataSnapshot.getChildren()) {

                    assetsList.add(snapshotToAssetModel(item_snapshot));
                }

                EventDataSource.this.eventList = assetsList;

                if(!subscribeCallback){
                    databaseReference.removeEventListener(this);
                }

                callback.onSuccess(assetsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError();
            }
        };

        databaseReference.addValueEventListener(eventListener);
    }


    private EventEntity snapshotToAssetModel(DataSnapshot item_snapshot) {

        String name = item_snapshot.child("name").exists() ? item_snapshot.child("name").getValue().toString() : "";
        String description = item_snapshot.child("description").exists() ? item_snapshot.child("description").getValue().toString() : "";
        double latitude = item_snapshot.child("latitude").exists() ? Double.parseDouble(item_snapshot.child("latitude").getValue().toString()) : 42.256014;
        double longitude = item_snapshot.child("longitude").exists() ? Double.parseDouble(item_snapshot.child("longitude").getValue().toString()) : 2.256014;

        return new EventEntity(name, description, latitude, longitude);

    }

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
