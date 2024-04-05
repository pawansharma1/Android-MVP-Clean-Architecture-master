package com.smarteist.FirstMile.views.fragments;

import com.smarteist.FirstMile.BaseFragment;
import com.smarteist.FirstMile.BasePresenter;
import com.smarteist.FirstMile.data.models.News;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Contract that is responsible for News Screen operations
 */

public interface NewsContracts {

    abstract class View extends BaseFragment<NewsPresenter> {

        abstract void showNews(List<News> news);

        abstract void showNoNews();

        abstract void showSuccessfullyArchivedNews();

        abstract void getImageLoaderService(Picasso picasso);
    }

    abstract class Presenter extends BasePresenter<NewsFragment> {

        abstract void loadNews(String category);

        abstract void showNewsDetail(News newsItem);

        abstract void loadSavedNews();

        abstract void archiveNews(News newsItem);

    }
}
