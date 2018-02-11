package com.example.longyuan.hubtraffic.datastore;



import com.example.longyuan.hubtraffic.datastore.local.LocalDataStore;
import com.example.longyuan.hubtraffic.datastore.remote.RemoteDataStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by loxu on 07/08/2017.
 */

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    RemoteDataStore providesRemoteDataStore() {
        return new RemoteDataStore();
    }

    @Provides
    @Singleton
    LocalDataStore providesLocalDataStore() {
        return new LocalDataStore();
    }

    @Provides
    @Singleton
    VideoRepository providesVideoRepository(RemoteDataStore remoteDataStore, LocalDataStore localDataStore) {
        return new VideoRepository(remoteDataStore,localDataStore);
    }


}
