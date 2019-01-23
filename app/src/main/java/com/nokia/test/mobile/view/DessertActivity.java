package com.nokia.test.mobile.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.nokia.test.mobile.R;

import com.nokia.test.mobile.dependencies.DaggerDependency;
import com.nokia.test.mobile.model.DessertResponse;
import com.nokia.test.mobile.networking.NetworkClient;
import com.nokia.test.mobile.networking.NetworkModule;
import com.nokia.test.mobile.presenter.DessertPresenter;
import com.nokia.test.mobile.presenter.ManageView;

import java.util.List;

import javax.inject.Inject;

public class DessertActivity extends AppCompatActivity implements ManageView.DessertView {
    private RecyclerView list;
    private FloatingActionButton addBtn, filterBtn;
    @Inject
    public NetworkClient networkClient;
    ProgressBar progressBar;
    DessertPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDependency.builder().networkModule(new NetworkModule()).build().inject(this);
        manageViewElements();

        presenter = new DessertPresenter(networkClient, this);
        presenter.getDessertList();
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
    }

    @Override
    public void getDessertListSuccess(List<DessertResponse> dessertListResponse) {

        DessertAdapter adapter = new DessertAdapter(getApplicationContext(), dessertListResponse,
                new DessertAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(DessertResponse Item) {
//                        presenter.goToCreate(getApplicationContext());
                        Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        list.setAdapter(adapter);
    }

    @Override
    public void showFilterDialog(String[] types) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.dialog_filter, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(DessertActivity.this);
        alertDialogBuilderUserInput.setView(view);
        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();
        ArrayAdapter<String> adaptader =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, types);
        ListView lstTypes = (ListView) view.findViewById(R.id.listType);
        lstTypes.setAdapter(adaptader);
        lstTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                presenter.filterDessertByType(types[position]);
                alertDialog.dismiss();
            }
        });
        final Button cancelBtn = view.findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public Context getViewContext() {
        return getApplicationContext();
    }

    public void clickMenuButton(View v) {
        presenter.clickMenuButton(v);
    }

    private void manageViewElements() {
        setContentView(R.layout.activity_dessert);
        list = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress);
        list.setLayoutManager(new LinearLayoutManager(this));
    }
}
