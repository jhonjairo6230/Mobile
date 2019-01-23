package com.nokia.test.mobile.presenter;

import android.content.Context;
import android.content.Intent;

import com.nokia.test.mobile.model.DessertResponse;
import com.nokia.test.mobile.networking.NetworkClient;
import com.nokia.test.mobile.networking.NetworkError;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class DessertPresenter {
    private final NetworkClient networkClient;
    private final ManageView.DessertView view;
    private CompositeSubscription subscriptions;

    public DessertPresenter(NetworkClient networkClient, ManageView.DessertView view) {
        this.networkClient = networkClient;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getDessertList() {
        view.showWait();

        Subscription subscription = networkClient.getDessertList(new NetworkClient.GetDessertListCallback() {
            @Override
            public void onSuccess(List<DessertResponse> dessertListResponse) {
                view.removeWait();
                view.getDessertListSuccess(dessertListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
