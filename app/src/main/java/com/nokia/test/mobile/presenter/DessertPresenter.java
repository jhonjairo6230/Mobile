package com.nokia.test.mobile.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.nokia.test.mobile.R;
import com.nokia.test.mobile.model.DessertResponse;
import com.nokia.test.mobile.networking.NetworkClient;
import com.nokia.test.mobile.networking.NetworkError;
import com.nokia.test.mobile.view.AddDessertActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class DessertPresenter {
    private final NetworkClient networkClient;
    private final ManageView.DessertView view;
    private CompositeSubscription subscriptions;
    // private String[] types;
    private List<String> typesL = new ArrayList<>();

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
                for (DessertResponse d : dessertListResponse) {
                    typesL.add(d.getType());
                }
                typesL = new ArrayList(new LinkedHashSet(typesL));
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

    public void clickMenuButton(View v) {
        switch (v.getId()) {
            case R.id.addFBtn:
                Intent addDessert = new Intent(view.getViewContext(), AddDessertActivity.class);
                addDessert.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getViewContext().startActivity(addDessert);
                break;
            case R.id.filterFBtn:
                String[] types = typesL.toArray(new String[0]);
                view.showFilterDialog(types);
                break;
            default:
                break;
        }
    }

    public void filterDessertByType(String type){
        Log.d("P","pendiente filtro "+type);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

}