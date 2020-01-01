package com.gimbernat.UntitledSocialGame.scenes.events.interfaces;

import com.gimbernat.UntitledSocialGame.Helpers.CreateEventCallback;

public interface ICreateEventInteractor {
    void createEvent(
            String name,
            String description,
            String latitude,
            String longitude,
            CreateEventCallback callback
    );
}
