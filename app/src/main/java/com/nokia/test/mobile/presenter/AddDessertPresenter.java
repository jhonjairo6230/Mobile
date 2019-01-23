package com.nokia.test.mobile.presenter;

import android.content.Context;
import android.content.Intent;

import com.nokia.test.mobile.model.Batters;
import com.nokia.test.mobile.model.DessertResponse;
import com.nokia.test.mobile.model.Topping;
import com.nokia.test.mobile.model.control.DessertResponseControl;

import java.util.List;

public class AddDessertPresenter {
    private ManageView.AddDessertView view;

    public AddDessertPresenter(ManageView.AddDessertView view) {
        this.view = view;
    }

    public void addDessert(String name, String type, Double ppu, int id, Batters b, List<Topping> t, Context c){
        DessertResponseControl dc = new DessertResponseControl(c);
        DessertResponse d = new DessertResponse(id, type, name, ppu, b, t);
        dc.create(d);
        view.addDessert();
    }
}
