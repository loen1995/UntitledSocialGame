package com.gimbernat.UntitledSocialGame.scenes.events;

import com.gimbernat.UntitledSocialGame.Helpers.Callback;
import com.gimbernat.UntitledSocialGame.Helpers.CreateEventCallback;
import com.gimbernat.UntitledSocialGame.scenes.events.interfaces.ICreateEventActivity;
import com.gimbernat.UntitledSocialGame.scenes.events.interfaces.ICreateEventInteractor;
import com.gimbernat.UntitledSocialGame.scenes.events.interfaces.ICreateEventPresenter;

public class CreateEventPresenter implements ICreateEventPresenter {

    ICreateEventActivity view;
    ICreateEventInteractor interactor;

    public CreateEventPresenter(ICreateEventActivity view) {
        this.view = view;
        this.interactor = new CreateEventInteractor();
    }


    @Override
    public void onCreateEvent() {
        this.view.showLoading();

        String nameEvent = this.view.getTextNameEvent();
        String descEvent = this.view.getTextDescriptionEvent();
        double altEvent  = this.view.getLatEvent();
        double longEvent = this.view.getLongEvent();

        this.interactor.createEvent(nameEvent, descEvent, altEvent, longEvent, new CreateEventCallback() {
            @Override
            public void onSuccess() {
                CreateEventPresenter.this.view.navigateToPrivate();
            }

            @Override
            public void onError(String error) {
                CreateEventPresenter.this.view.onError(error);
            }
        });

    }
}
