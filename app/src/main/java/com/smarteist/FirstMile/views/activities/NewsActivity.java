package com.smarteist.FirstMile.views.activities;

import android.arch.persistence.room.Dao;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.smarteist.FirstMile.BaseActivity;
import com.smarteist.FirstMile.BaseContracts;
import com.smarteist.FirstMile.R;
import com.smarteist.FirstMile.data.models.News;
import com.smarteist.FirstMile.data.source.local.NewsDatabase;
import com.smarteist.FirstMile.util.ActivityUtils;
import com.smarteist.FirstMile.util.Constants;
import com.smarteist.FirstMile.views.NewsAdapter;
import com.smarteist.FirstMile.views.fragments.NewsFragment;
import com.smarteist.FirstMile.views.fragments.NewsPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Lightweight Activity in which NewsFragment emerges from
 * In this case we don't need presenter and contracts for this
 * activity.
 */

public class NewsActivity extends BaseActivity {

    @Inject
    NewsPresenter mNewsPresenter;

    @Inject
    NewsFragment injectedFragment;

    private DrawerLayout mDrawerLayout;

     Dao dao;

    private NewsFragment.NewsItemListener mItemListener;
    NewsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);




        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setUpActionBar(toolbar);

        // Set up the navigation drawer.
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        // Set up fragment
        NewsFragment fragment = (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = injectedFragment;
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setCheckedItem(R.id.top_navigation_menu_item);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                        switch (menuItem.getItemId()) {
                            case R.id.top_navigation_menu_item:
                                mNewsPresenter.loadNews(Constants.NEWS_CATEGORY_LATEST);
                                break;
                            case R.id.tech_navigation_menu_item:
                                mNewsPresenter.loadNews(Constants.NEWS_CATEGORY_TECHNOLOGY);
                                break;
                            case R.id.business_navigation_menu_item:
                                mNewsPresenter.loadNews(Constants.NEWS_CATEGORY_BUSINESS);
                                break;
                            case R.id.saved_navigation_menu_item:
                                mNewsPresenter.loadSavedNews();
                                break;

                            default:
                                break;
                        }

                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

//                if(mNews.contains(query)){
//                    adapter.filter(query);
//                }else{
//                    Toast.makeText(NewsActivity.this, "No Match found", Toast.LENGTH_LONG).show();
//                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }



    private void setUpActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initViews(View parentRoot) {
        //todo
    }

    @Override
    public BaseContracts.Presenter getPresenter() {
        return null;
    }
}
