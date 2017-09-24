package com.zhuinden.realmmanagerexample;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Zhuinden on 2017.09.24..
 */

public class CustomApplication
        extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .initialData(realm -> {
                    Cat cat = new Cat();
                    for(CatNames catName : CatNames.values()) {
                        cat.setName(catName.getName());
                        realm.insert(cat);
                    }
                })
                .build());
        SingletonComponent singletonComponent = DaggerSingletonComponent.create();
        Injector.setComponent(singletonComponent);
    }
}