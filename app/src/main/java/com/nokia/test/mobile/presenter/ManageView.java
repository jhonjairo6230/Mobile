package com.nokia.test.mobile.presenter;

import com.nokia.test.mobile.model.DessertResponse;

import java.util.List;

public interface ManageView {
    interface DessertView {
        void showWait();

        void removeWait();

        void onFailure(String appErrorMessage);

        void getCityListSuccess(List<DessertResponse> cityListResponse);
    }

    interface CreateDessertView{
        void addDessert();
    }

    interface UpdateDessertView{

    }
}
