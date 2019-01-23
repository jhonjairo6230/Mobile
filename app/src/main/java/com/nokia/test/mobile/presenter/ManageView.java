package com.nokia.test.mobile.presenter;

import android.content.Context;

import com.nokia.test.mobile.model.DessertResponse;

import java.util.List;

public interface ManageView {
    interface DessertView {
        void showWait();

        void removeWait();

        void onFailure(String appErrorMessage);

        void getDessertListSuccess(List<DessertResponse> dessertListResponse);

        void showFilterDialog(String[] types);

        Context getViewContext();
    }

    interface CreateDessertView {
        void addDessert();
    }

    interface UpdateDessertView {

    }
}
