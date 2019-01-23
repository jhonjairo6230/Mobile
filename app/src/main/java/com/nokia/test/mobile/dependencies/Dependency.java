package com.nokia.test.mobile.dependencies;

import com.nokia.test.mobile.networking.NetworkModule;
import com.nokia.test.mobile.view.DessertActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Dependency {
    void inject(DessertActivity dessertActivity);
}
