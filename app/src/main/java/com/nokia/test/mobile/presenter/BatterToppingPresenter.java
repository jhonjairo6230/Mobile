package com.nokia.test.mobile.presenter;

import com.nokia.test.mobile.model.Batter;
import com.nokia.test.mobile.model.Batters;

import java.util.ArrayList;
import java.util.List;

public class BatterToppingPresenter {
    private ManageView.BatterToppingView view;

    public BatterToppingPresenter(ManageView.BatterToppingView view) {
        this.view = view;
    }

    public void addElement(int id, String type) {
        List<Batter> b = new ArrayList<>();
        b.add(new Batter(id, type));
        Batters bs = new Batters(b);
    }

}
