package com.nokia.test.mobile.networking;

import com.nokia.test.mobile.model.DessertResponse;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class NetworkClient {
    private final NetworkService networkService;

    public NetworkClient(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getDessertList(final GetDessertListCallback callback) {

        return networkService.getDessertList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<DessertResponse>>>() {
                    @Override
                    public Observable<? extends List<DessertResponse>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<List<DessertResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(List<DessertResponse> dessertListResponse) {
                        callback.onSuccess(dessertListResponse);

                    }
                });
    }

    public interface GetDessertListCallback{
        void onSuccess(List<DessertResponse> dessertListResponse);

        void onError(NetworkError networkError);
    }
}
