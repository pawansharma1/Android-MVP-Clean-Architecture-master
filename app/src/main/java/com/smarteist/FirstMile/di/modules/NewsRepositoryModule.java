package com.smarteist.FirstMile.di.modules;

import com.smarteist.FirstMile.data.source.NewsDataSource;
import com.smarteist.FirstMile.data.source.local.NewsDao;
import com.smarteist.FirstMile.data.source.local.NewsLocalDataSource;
import com.smarteist.FirstMile.data.source.remote.NewsRemoteDataSource;
import com.smarteist.FirstMile.data.source.remote.NewsService;
import com.smarteist.FirstMile.data.source.scopes.Local;
import com.smarteist.FirstMile.data.source.scopes.Remote;
import com.smarteist.FirstMile.di.scopes.ApplicationScoped;
import com.smarteist.FirstMile.util.ExecutorUtils.AppExecutors;

import dagger.Module;
import dagger.Provides;

/**
 * NewsRepositoryModule contains both Local And Remote Data Sources modules
 */

@Module(includes = {NewsRemoteDataModule.class, NewsLocalDataModule.class})
public class NewsRepositoryModule {

    @Provides
    @Local
    @ApplicationScoped
    NewsDataSource provideNewsLocalDataSource(AppExecutors appExecutors, NewsDao newsDao) {
        return new NewsLocalDataSource(appExecutors, newsDao);
    }

    @Provides
    @Remote
    @ApplicationScoped
    NewsDataSource provideNewsRemoteDataSource(NewsService newsService) {
        return new NewsRemoteDataSource(newsService);
    }
}
