package com.nokia.test.mobile.presenter;

import android.content.Context;

import com.nokia.test.mobile.model.DessertResponse;
import com.nokia.test.mobile.model.control.DessertResponseControl;

public class DessertDetailPresenter {
    private  ManageView.DessertDetailView view;
    private DessertResponseControl dc;

    public DessertDetailPresenter(
            ManageView.DessertDetailView view) {
        this.view = view;
    }

    public void showDialog(){
           view.showDialog();
    }

    public void deleteDessert(Context c,DessertResponse dessertSelected){
        dc = new DessertResponseControl(c.getApplicationContext());
        dc.removeDessert(dessertSelected.getId());
    }

}
