package com.nokia.test.mobile.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nokia.test.mobile.R;
import com.nokia.test.mobile.model.Element;
import com.nokia.test.mobile.presenter.BatterToppingPresenter;
import com.nokia.test.mobile.presenter.ManageView;

import java.util.List;

public class BatterToppingActivity extends AppCompatActivity implements ManageView.BatterToppingView {
    private EditText edt0, edt1;
    private ListView list;
    private String[] types;
    private BatterToppingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BatterToppingPresenter(this);
        manageView();
    }

    private void manageView() {
        setContentView(R.layout.activity_batter_topping);
        edt0 = (EditText) findViewById(R.id.edt0);
        edt1 = (EditText) findViewById(R.id.edt1);
        list = (ListView) findViewById(R.id.listBT);
    }

    public void clickButton(View v) {
        String id = edt0.getText().toString();
        String type = edt1.getText().toString();
        if(!id.isEmpty() && !type.isEmpty()) {
            presenter.addElement(Integer.valueOf(id),type);
        }else{
            Toast.makeText(this,"No dejes campos vacios",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void populateList(List<Element> elements) {
        BatterToppingAdapter adaptader = new BatterToppingAdapter(this,  elements);

        list.setAdapter(adaptader);
    }
}
