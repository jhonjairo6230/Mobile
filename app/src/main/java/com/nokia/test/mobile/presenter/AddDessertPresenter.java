package com.nokia.test.mobile.presenter;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.nokia.test.mobile.model.Batters;
import com.nokia.test.mobile.model.DessertResponse;
import com.nokia.test.mobile.model.Topping;
import com.nokia.test.mobile.model.control.DessertResponseControl;
import com.nokia.test.mobile.view.BatterToppingActivity;

public class AddDessertPresenter {
    private ManageView.AddDessertView view;

    public AddDessertPresenter(ManageView.AddDessertView view) {
        this.view = view;
    }

    public void addDessert(String name,String type, Double ppu, int id,Context c){
        DessertResponseControl dc = new DessertResponseControl(c);
        DessertResponse d = new DessertResponse(id, type, name, ppu, null, null);
        dc.create(d);
        view.addDessert();
    }

    public void showBatters(Context c){
        Intent batterIntent = new Intent(c,BatterToppingActivity.class);
        batterIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Gson gson = new Gson();
//        String myJson = gson.toJson(dessertSelected);
//        DessertDetail.putExtra("dessertSelected", myJson);
        c.startActivity(batterIntent);
    }
    public void showToppings(Context c){
        Intent toppingsIntent = new Intent(c,BatterToppingActivity.class);
        toppingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Gson gson = new Gson();
//        String myJson = gson.toJson(dessertSelected);
//        DessertDetail.putExtra("dessertSelected", myJson);
        c.startActivity(toppingsIntent);
    }
}
