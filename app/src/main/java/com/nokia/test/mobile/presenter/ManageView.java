package com.nokia.test.mobile.presenter;

import android.content.Context;

import com.nokia.test.mobile.model.DessertResponse;
import com.nokia.test.mobile.model.Element;

import java.util.List;

public interface ManageView {
    interface DessertDetailView {
        void showDialog();
    }
    interface DessertView {
        void showWait();

        void removeWait();

        void onFailure(String appErrorMessage);

        void getDessertListSuccess(List<DessertResponse> dessertListResponse);

        void showFilterDialog(String[] types);

        Context getViewContext();
    }

    interface AddDessertView {
        void addDessert();
    }

    interface BatterToppingView{
        void populateList(List<Element> elements);
    }


}
