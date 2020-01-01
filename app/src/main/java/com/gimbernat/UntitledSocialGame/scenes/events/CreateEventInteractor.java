package com.gimbernat.UntitledSocialGame.scenes.events;

import com.gimbernat.UntitledSocialGame.DataSources.EventDataSource;
import com.gimbernat.UntitledSocialGame.Helpers.CreateEventCallback;
import com.gimbernat.UntitledSocialGame.scenes.events.interfaces.ICreateEventInteractor;

public class CreateEventInteractor implements ICreateEventInteractor {

    @Override
    public void createEvent(
            String name,
            String description,
            String latitude,
            String longitude,
            CreateEventCallback callback
    ) {
        EventDataSource.shared.createEvent(name,description, latitude, longitude, callback);
    }
}
