package com.nokia.test.mobile.networking;

import com.nokia.test.mobile.model.DessertResponse;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;


public interface NetworkService {

    @GET("5c424e4832000077037327b0")
    Observable<List<DessertResponse>> getDessertList();


}
