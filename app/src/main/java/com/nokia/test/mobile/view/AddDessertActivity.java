package com.nokia.test.mobile.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nokia.test.mobile.R;
import com.nokia.test.mobile.model.DessertResponse;
import com.nokia.test.mobile.presenter.AddDessertPresenter;
import com.nokia.test.mobile.presenter.ManageView;

public class AddDessertActivity extends AppCompatActivity implements ManageView.AddDessertView {
    private AddDessertPresenter presenter;
    private EditText edt0, edt1, edt2, edt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AddDessertPresenter(this);
        manageGElements();
    }

    @Override
    public void addDessert() {
        finish();
    }

    public void clickButton(View v) {
        switch (v.getId()) {
            case R.id.batterBtn:
                presenter.showBatters(getApplicationContext());
                break;
            case R.id.toppingBtn:
                presenter.showToppings(getApplicationContext());
                break;
            case R.id.addBtn:
                String name = edt0.getText().toString();
                String type = edt1.getText().toString();
                String ppu = edt2.getText().toString();
                String id = edt3.getText().toString();
                if (!name.isEmpty() && !type.isEmpty() && !ppu.isEmpty() && !id.isEmpty()) {
                    presenter.addDessert(edt0.getText().toString(), edt1.getText().toString(),
                            Double.parseDouble(edt2.getText().toString()),
                            Integer.parseInt(edt3.getText().toString()), getApplicationContext());
                }else{
                    Toast.makeText(this,"No dejes campos vacios",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void manageGElements() {
        setContentView(R.layout.activity_add_dessert);
        edt0 = (EditText) findViewById(R.id.edt0);
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        edt3 = (EditText) findViewById(R.id.edt3);
    }
}
