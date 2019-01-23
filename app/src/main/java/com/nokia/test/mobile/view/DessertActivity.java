package com.nokia.test.mobile.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nokia.test.mobile.R;

import com.nokia.test.mobile.dependencies.DaggerDependency;
import com.nokia.test.mobile.model.DessertResponse;
import com.nokia.test.mobile.networking.NetworkClient;
import com.nokia.test.mobile.networking.NetworkModule;
import com.nokia.test.mobile.presenter.DessertPresenter;
import com.nokia.test.mobile.presenter.ManageView;

import java.util.List;

import javax.inject.Inject;

public class DessertActivity extends AppCompatActivity implements ManageView.DessertView{
    private RecyclerView list;
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

   private void manageViewElements(){
       setContentView(R.layout.activity_dessert);
       list = findViewById(R.id.list);
       progressBar = findViewById(R.id.progress);
       list.setLayoutManager(new LinearLayoutManager(this));
   }
}
