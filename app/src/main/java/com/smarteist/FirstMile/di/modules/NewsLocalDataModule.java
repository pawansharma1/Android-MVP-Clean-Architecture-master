package com.smarteist.FirstMile.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.smarteist.FirstMile.data.source.local.NewsDao;
import com.smarteist.FirstMile.data.source.local.NewsDatabase;
import com.smarteist.FirstMile.di.scopes.ApplicationScoped;
import com.smarteist.FirstMile.util.Constants;
import com.smarteist.FirstMile.util.ExecutorUtils.AppExecutors;
import com.smarteist.FirstMile.util.ExecutorUtils.DiskIOThreadExecutor;

import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsLocalDataModule {
    private static final int THREAD_COUNT = 3;

    @ApplicationScoped
    @Provides
    NewsDatabase provideDb(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                NewsDatabase.class, Constants.NEWS_ROOM_DB_STRING)
                .build();
    }

    @ApplicationScoped
    @Provides
    NewsDao provideNewsDao(NewsDatabase db) {
        return db.newsDao();
    }

    @ApplicationScoped
    @Provides
    AppExecutors provideAppExecutors() {
        return new AppExecutors(new DiskIOThreadExecutor(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new AppExecutors.MainThreadExecutor());
    }
}
