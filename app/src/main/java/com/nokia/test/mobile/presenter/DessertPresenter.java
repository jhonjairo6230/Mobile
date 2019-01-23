package com.nokia.test.mobile.presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.nokia.test.mobile.R;
import com.nokia.test.mobile.model.DessertResponse;
import com.nokia.test.mobile.model.control.DessertResponseControl;
import com.nokia.test.mobile.networking.NetworkClient;
import com.nokia.test.mobile.networking.NetworkError;
import com.nokia.test.mobile.view.AddDessertActivity;
import com.nokia.test.mobile.view.DessertDetailActivity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class DessertPresenter {
    private final NetworkClient networkClient;
    private final ManageView.DessertView view;
    private CompositeSubscription subscriptions;
    private List<String> typesL = new ArrayList<>();
    private DessertResponseControl dc;
    private List<DessertResponse> dessertsList;

    public DessertPresenter(NetworkClient networkClient, ManageView.DessertView view) {
        this.networkClient = networkClient;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
        dc = new DessertResponseControl(view.getViewContext());
    }

    public void getDessertList() {
        view.showWait();

        Subscription subscription = networkClient.getDessertList(new NetworkClient.GetDessertListCallback() {
            @Override
            public void onSuccess(List<DessertResponse> dessertListResponse) {
                dessertsList = dc.getAll();
                if (dessertsList.size() <= 0) {
                    dc.createAll(dessertListResponse);
                    dessertsList = dc.getAll();
                } else {
                    //dessertsList = dessertListResponse;
                }
                typesL.add("Todos");
                for (DessertResponse d : dessertsList) {
                    typesL.add(d.getType());
                }
                typesL = new ArrayList(new LinkedHashSet(typesL));

                view.removeWait();
                view.getDessertListSuccess(dessertsList);
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
            case R.id.getallFBtn:
                dc.removeAllDessert();
                getDessertList();
                break;
            default:
                break;
        }
    }

    public void filterDessertByType(String type) {
        if (type.equalsIgnoreCase("todos")) {
            view.getDessertListSuccess(dc.getAll());
        } else {
            view.getDessertListSuccess(dc.getFiltered(type));
        }
    }

    public void showDessertSelected(DessertResponse dessertSelected) {
        Intent DessertDetail = new Intent(view.getViewContext(), DessertDetailActivity.class);
        DessertDetail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Gson gson = new Gson();
        String myJson = gson.toJson(dessertSelected);
        DessertDetail.putExtra("dessertSelected", myJson);
        view.getViewContext().startActivity(DessertDetail);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

}