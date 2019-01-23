package com.nokia.test.mobile.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nokia.test.mobile.R;
import com.nokia.test.mobile.model.Batter;
import com.nokia.test.mobile.model.DessertResponse;
import com.nokia.test.mobile.model.Topping;
import com.nokia.test.mobile.presenter.DessertDetailPresenter;
import com.nokia.test.mobile.presenter.ManageView;

import java.util.List;

public class DessertDetailActivity extends AppCompatActivity implements ManageView.DessertDetailView {
    private TextView textView0, textView1, textView2;
    private ListView lstBatters, lstToppings;
    private DessertDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DessertDetailPresenter(this);
        manageViewElements();
        init();
    }


    public void init() {
        Gson gson = new Gson();
        DessertResponse dessertSelected = gson.fromJson(getIntent().getStringExtra("dessertSelected"), DessertResponse.class);
        textView0.setText(dessertSelected.getName());
        textView1.setText(dessertSelected.getType());
        textView2.setText(String.valueOf(dessertSelected.getPpu()));
        List<Topping> toppings = dessertSelected.getTopping();
        String[] t = new String[toppings.size()];
        for (int i = 0; i < toppings.size(); i++) {
            t[i] = toppings.get(i).getType();
        }

        List<Batter> batters = dessertSelected.getBatters().getBatter();
        String[] b = new String[batters.size()];
        for (int i = 0; i < batters.size(); i++) {
            b[i] = batters.get(i).getType();
        }
        ArrayAdapter<String> adapterB =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, b);
        lstBatters.setAdapter(adapterB);
        ArrayAdapter<String> adapterT =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, t);
        lstToppings.setAdapter(adapterT);
    }

    private void manageViewElements() {
        setContentView(R.layout.activity_dessert_detail);
        textView0 = (TextView) findViewById(R.id.textView0);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        lstToppings = (ListView) findViewById(R.id.listTopping);
        lstBatters = (ListView) findViewById(R.id.listBatter);

    }
}
